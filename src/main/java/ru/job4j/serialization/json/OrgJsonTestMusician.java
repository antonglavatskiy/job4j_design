package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class OrgJsonTestMusician {
    public static void main(String[] args) {
        Group group = new Group("DA", "Pop");
        String[] instrumentals = new String[]{"Harmonica", "Piano"};
        Musician musician = new Musician(true, "Mike", 20,
                group, instrumentals);
        List<String> list = Arrays.asList(instrumentals);
        JSONArray jsonArray = new JSONArray(list);
        JSONObject jsonGroup = new JSONObject("{\"name\":\"DA\", \"genre\":\"Pop\"}");
        JSONObject jsonMusician = new JSONObject();
        jsonMusician.put("singer", musician.isSinger());
        jsonMusician.put("name", musician.getName());
        jsonMusician.put("age", musician.getAge());
        jsonMusician.put("group", jsonGroup);
        jsonMusician.put("instrumentalSkills", jsonArray);
        System.out.println(jsonMusician);
        System.out.println(new JSONObject(musician));
        System.out.println(new JSONObject(group));
    }
}
