package com.github.alxbel.metschedson.model;

import java.util.*;

public class Direction {
    private String name;
    private String jsonName;
    private Map<Integer, TreeSet<Integer>> schedule = new TreeMap<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Direction direction = (Direction) o;
        return Objects.equals(name, direction.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "'" + name + '\'';
    }

    public String toResourceString() {
        return String.format("<string name=\"direction_%s\">%s</string>", jsonName, name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJsonName() {
        return jsonName;
    }

    public void setJsonName(String jsonName) {
        this.jsonName = jsonName;
    }

    public String getJsonFullName() {
        return String.format("direction_%s.json", jsonName);
    }

    public String getJsonFullName(String suffix) {
        return String.format("direction_%s_%s.json", suffix, jsonName);
    }

    public Map<Integer, TreeSet<Integer>> getSchedule() {
        return schedule;
    }

    public void setSchedule(Map<Integer, TreeSet<Integer>> schedule) {
        this.schedule = schedule;
    }

    public void addTimings(Integer hour, TreeSet<Integer> timings) {
        schedule.put(hour, timings);
    }
}
