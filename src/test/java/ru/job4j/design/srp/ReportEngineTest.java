package ru.job4j.design.srp;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;
import static ru.job4j.design.srp.ReportEngine.DATE_FORMAT;

class ReportEngineTest {
    @Test
    public void whenOldGenerated() {
        MemStore memStore = new MemStore();
        Calendar date = Calendar.getInstance();
        Employee employee = new Employee("Maxim", date, date, 500);
        memStore.add(employee);
        Report engine = new ReportEngine(memStore);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(employee.getName()).append(";")
                .append(DATE_FORMAT.format(employee.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(employee.getFired().getTime())).append(";")
                .append(employee.getSalary()).append(";")
                .append(System.lineSeparator());
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
                .append(System.lineSeparator())
                .append(employee.getName()).append(";")
                .append(DATE_FORMAT.format(employee.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(employee.getFired().getTime())).append(";")
                .append(employee.getSalary() * 0.1).append(";")
                .append(System.lineSeparator());
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
                .append(System.lineSeparator())
                .append(employee3.getName()).append(";")
                .append(employee3.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(employee1.getName()).append(";")
                .append(employee1.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(employee2.getName()).append(";")
                .append(employee2.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(emp -> true)).isEqualTo(expect.toString());
    }

}