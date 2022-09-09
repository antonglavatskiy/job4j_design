package ru.job4j.design.srp;

import java.text.SimpleDateFormat;
import java.util.function.Predicate;

public class HTMLReportEngine implements Report {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");
    private Store store;

    public HTMLReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">")
                .append("<html>")
                .append("<head>")
                .append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">")
                .append("<title>HTML Report</title>")
                .append("</head>")
                .append("<body>")
                .append("<h1>Report</h1>")
                .append("<p>Name; Hired; Fired; Salary;</p>");
        for (Employee employee : store.findBy(filter)) {
            html.append("<p>")
                    .append(employee.getName()).append(";")
                    .append(DATE_FORMAT.format(employee.getHired().getTime())).append(";")
                    .append(DATE_FORMAT.format(employee.getFired().getTime())).append(";")
                    .append(employee.getSalary()).append(";")
                    .append("</p>");
        }
        html.append("</body>")
                .append("</html>");
        return html.toString();
    }
}
