package com.example.eventstormbackend.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;

public class ImportJsonService {
    @Autowired
    private MongoTemplate mongoTemplate;

    private List<Document> generateMongoDocs(JsonArray events) {
        List<Document> docs = new ArrayList<>();
        events.forEach(jsonElement -> docs.add(Document.parse(jsonElement.getAsString())));
        return docs;
    }

    private JsonArray parseIntoJSON(String jString) {
        JsonObject jsonObject = new JsonParser().parse(jString).getAsJsonObject();
        JsonObject embedded = jsonObject.getAsJsonObject("_embedded");
        return embedded.getAsJsonArray("events");

    }

    public void insertIntoCollection(String response) {
        JsonArray events = parseIntoJSON(response);
        List<Document> mongoDocs = generateMongoDocs(events);
//        mongoTemplate.createCollection("events");
        mongoTemplate.insert(mongoDocs, "events");
    }
}
