package com.arjenvd.model;

public class Reading {
    private int id;
    private long readingTime;
    private int temperature;
    private int humidity;

    public Reading(int id, long readingTime, int temperature, int humidity) {
        this.id = id;
        this.readingTime = readingTime;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public Reading() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getReadingTime() {
        return readingTime;
    }

    public void setReadingTime(long readingTime) {
        this.readingTime = readingTime;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }


}
