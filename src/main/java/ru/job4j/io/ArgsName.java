package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Key " + key + " is not exist");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String param : args) {
            int index = validate(param);
            values.put(param.substring(1, index), param.substring(index + 1));
        }
    }

    private static int validate(String param) {
        if (!param.startsWith("-") || !param.contains("=")) {
            throw new IllegalArgumentException("The argument is missing a sign = or -");
        }
        int index = param.indexOf("=");
        if (param.startsWith("-=")
                || (index == param.lastIndexOf('=')
                && index == param.length() - 1)) {
            throw new IllegalArgumentException("The argument is missing key or value");
        }
        return index;
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments is not found");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
