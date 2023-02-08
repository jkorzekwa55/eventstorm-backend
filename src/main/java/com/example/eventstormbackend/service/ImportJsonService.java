package com.example.eventstormbackend.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.AllArgsConstructor;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ImportJsonService {
    @Autowired
    private MongoTemplate mongoTemplate;

    private List<Document> generateMongoDocs(JsonArray events) {
        List<Document> docs = new ArrayList<>();
        events.forEach(jsonElement -> docs.add(Document.parse(String.valueOf(jsonElement))));
        return docs;
    }

    private JsonArray parseIntoJSON(String jString) {
        JsonObject jsonObject = JsonParser.parseString(jString).getAsJsonObject();
        JsonObject embedded = jsonObject.getAsJsonObject("_embedded");
        return embedded.getAsJsonArray("events");

    }

    public void insertIntoCollection(String response) {
        JsonArray events = parseIntoJSON(response);
        List<Document> mongoDocs = generateMongoDocs(events);
        mongoTemplate.dropCollection("events");
        if (!mongoTemplate.collectionExists("events")) {
            mongoTemplate.createCollection("events");
        }
        mongoTemplate.insert(mongoDocs, "events");
    }
}
