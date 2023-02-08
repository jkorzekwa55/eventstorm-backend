package com.example.eventstormbackend.apiClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class TicketmasterClientAPI {
    private static final String URL = "https://app.ticketmaster.com/discovery/v2/events?apikey=";
    @Value("${ticketmaster.apiKey}")
    private String apiKey;

    public String getEvents() throws IOException {
        URL obj = new URL(URL + apiKey + "&locale=pl");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        }
        return null;
    }
}
