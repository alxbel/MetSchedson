package com.github.alxbel.metschedson;

import com.github.alxbel.metschedson.mapper.DirectionMapper;

public class App {
    public static void main(String[] args) {
        DirectionMapper mapper = new DirectionMapper();
        mapper.fromTxtToJson("directions_wd.txt", "wd");
    }
}
