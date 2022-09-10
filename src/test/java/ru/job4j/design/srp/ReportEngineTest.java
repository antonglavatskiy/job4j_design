package ru.job4j.design.srp;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static org.assertj.core.api.Assertions.*;
import static ru.job4j.design.srp.ReportEngine.DATE_FORMAT;

class ReportEngineTest {
    public static final SimpleDateFormat XML_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    public static final String TAB = "    ";
    public static final String ENTER = System.lineSeparator();
    public static final String DATE_ELEMENT = "{\"%s\":%d,\"%s\":%d,\"%s\":%d,\"%s\":%d,\"%s\":%d,\"%s\":%d},";

    @Test
    public void whenOldGenerated() {
        MemStore memStore = new MemStore();
        Calendar date = Calendar.getInstance();
        Employee employee = new Employee("Maxim", date, date, 500);
        memStore.add(employee);
        Report engine = new ReportEngine(memStore);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(ENTER)
                .append(employee.getName()).append(";")
                .append(DATE_FORMAT.format(employee.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(employee.getFired().getTime())).append(";")
                .append(employee.getSalary()).append(";")
                .append(ENTER);
        assertThat(engine.generate(emp -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenGeneratedHTMLReport() {
        MemStore memStore = new MemStore();
        Calendar date = Calendar.getInstance();
        Employee employee = new Employee("Maxim", date, date, 500);
        memStore.add(employee);
        Report engine = new HTMLReportEngine(memStore);
        StringBuilder expect = new StringBuilder()
                .append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" ")
                .append("\"http://www.w3.org/TR/html4/strict.dtd\">")
                .append("<html>")
                .append("<head>")
                .append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">")
                .append("<title>HTML Report</title>")
                .append("</head>")
                .append("<body>")
                .append("<h1>Report</h1>")
                .append("<p>Name; Hired; Fired; Salary;</p>")
                .append("<p>")
                .append(employee.getName()).append(";")
                .append(DATE_FORMAT.format(employee.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(employee.getFired().getTime())).append(";")
                .append(employee.getSalary()).append(";")
                .append("</p>")
                .append("</body>")
                .append("</html>");
        assertThat(engine.generate(emp -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenGeneratedAccountantReport() {
        MemStore memStore = new MemStore();
        Calendar date = Calendar.getInstance();
        Employee employee = new Employee("Maxim", date, date, 500);
        memStore.add(employee);
        Report engine = new AccountantReportEngine(memStore);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Extra payment;")
                .append(ENTER)
                .append(employee.getName()).append(";")
                .append(DATE_FORMAT.format(employee.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(employee.getFired().getTime())).append(";")
                .append(employee.getSalary() * AccountantReportEngine.PERCENT).append(";")
                .append(ENTER);
        assertThat(engine.generate(emp -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenGeneratedHRReport() {
        MemStore memStore = new MemStore();
        Calendar date = Calendar.getInstance();
        Employee employee1 = new Employee("Maxim", date, date, 700);
        Employee employee2 = new Employee("Ivan", date, date, 500);
        Employee employee3 = new Employee("Andrey", date, date, 900);
        memStore.add(employee1);
        memStore.add(employee2);
        memStore.add(employee3);
        Report engine = new HRReportEngine(memStore);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(ENTER)
                .append(employee3.getName()).append(";")
                .append(employee3.getSalary()).append(";")
                .append(ENTER)
                .append(employee1.getName()).append(";")
                .append(employee1.getSalary()).append(";")
                .append(ENTER)
                .append(employee2.getName()).append(";")
                .append(employee2.getSalary()).append(";")
                .append(ENTER);
        assertThat(engine.generate(emp -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenGeneratedXMLReport() {
        MemStore memStore = new MemStore();
        Calendar date = Calendar.getInstance();
        Employee employee = new Employee("Maxim", date, date, 700);
        memStore.add(employee);
        Report engine = new XMLReportEngine(memStore);
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                .append(ENTER)
                .append("<employees>").append(ENTER)
                .append(TAB).append("<employees>").append(ENTER)
                .append(TAB).append(TAB).append("<fired>")
                .append(XML_FORMAT.format(employee.getFired().getTime())).append("</fired>")
                .append(ENTER).append(TAB).append(TAB).append("<hired>")
                .append(XML_FORMAT.format(employee.getHired().getTime())).append("</hired>")
                .append(ENTER).append(TAB).append(TAB)
                .append("<name>").append(employee.getName()).append("</name>")
                .append(ENTER).append(TAB).append(TAB)
                .append("<salary>").append(employee.getSalary()).append("</salary>")
                .append(ENTER).append(TAB).append("</employees>")
                .append(ENTER).append("</employees>").append(ENTER);
        assertThat(engine.generate(emp -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenGeneratedJSONReport() {
        MemStore memStore = new MemStore();
        Calendar date = Calendar.getInstance();
        Employee employee = new Employee("Anna", date, date, 500);
        memStore.add(employee);
        Report engine = new JSONReportEngine(memStore);
        StringBuilder expect = new StringBuilder()
                .append("[{")
                .append("\"name\":").append(String.format("\"%s\",", employee.getName()))
                .append("\"hired\":")
                .append(String.format(DATE_ELEMENT,
                        "year", employee.getHired().get(Calendar.YEAR),
                        "month", employee.getHired().get(Calendar.MONTH),
                        "dayOfMonth", employee.getHired().get(Calendar.DAY_OF_MONTH),
                        "hourOfDay", employee.getHired().get(Calendar.HOUR_OF_DAY),
                        "minute", employee.getHired().get(Calendar.MINUTE),
                        "second", employee.getHired().get(Calendar.SECOND)))
                .append("\"fired\":")
                .append(String.format(DATE_ELEMENT,
                        "year", employee.getFired().get(Calendar.YEAR),
                        "month", employee.getFired().get(Calendar.MONTH),
                        "dayOfMonth", employee.getFired().get(Calendar.DAY_OF_MONTH),
                        "hourOfDay", employee.getFired().get(Calendar.HOUR_OF_DAY),
                        "minute", employee.getFired().get(Calendar.MINUTE),
                        "second", employee.getFired().get(Calendar.SECOND)))
                .append("\"salary\":").append(String.format(Locale.ROOT, "%.1f}]", employee.getSalary()));
        assertThat(engine.generate(emp -> true)).isEqualTo(expect.toString());
    }
}