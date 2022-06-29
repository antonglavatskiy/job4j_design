package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONTestMusician {
    public static void main(String[] args) {
        Group dogs = new Group("Dogs", "Pop");
        String[] instruments = new String[]{"Guitar", "Accordion"};
        final Musician ivan = new Musician(false, "Ivan",
                20, dogs, instruments);
        final Gson gson = new GsonBuilder().create();
        String ivanGson = gson.toJson(ivan);
        System.out.println(ivanGson);

        Musician returned = gson.fromJson(ivanGson, Musician.class);
        System.out.println(returned);
    }
}
