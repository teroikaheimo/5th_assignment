package com.example.a5thassignment_http;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONException;
import org.json.JSONObject;

public class NameDate {
    private LocalDateTime timeStamp;
    private String name;
    // Is needed to parse custom time format to LocalDateTime object.
    private DateTimeFormatter dtf = DateTimeFormat.forPattern("dd.MM.yyyy HH:mm:ss");

    public NameDate(JSONObject jsonObj) {
        try {
            this.timeStamp = LocalDateTime.parse(jsonObj.getString("pvm"), dtf);
            this.name = jsonObj.getString("nimi");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getDateString() {
        return timeStamp.toString("dd.MM.YYYY");
    }

    public String getNameString() {
        return name;
    }
}
