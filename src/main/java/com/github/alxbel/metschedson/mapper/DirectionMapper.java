package com.github.alxbel.metschedson.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.alxbel.metschedson.model.Direction;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class DirectionMapper {

    private ObjectMapper objectMapper = new ObjectMapper();

    public void fromTxtToJson(String fileName, String suffix) {
        fromTxt2Directions(fileName).forEach(direction -> {
            try {
                objectMapper.writeValue(new File(String.format("target/%s", direction.getJsonFullName(suffix))), direction);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private List<Direction> fromTxt2Directions(String fileName) {
        List<Direction> directions = new ArrayList<>();

        StringBuilder result = new StringBuilder();

        // Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> lines = Arrays.asList(result.toString().split("\n"));

        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).contains("Отправление")) {
                String head = lines.get(i++);
                Direction direction = new Direction();
                direction.setName(head.substring(0, head.indexOf("[")).trim());
                direction.setJsonName(head.substring(head.indexOf("[")+1, head.indexOf("]")));
                while (!lines.get(i).contains(".")) {
                    String line = lines.get(i).replaceAll(",|;", "");
                    if (line.contains(":")) {
                        String[] timings = line.split("\\s+");
                        final Integer hour = Integer.valueOf(timings[0].split(":")[0].trim());
                        TreeSet<Integer> timingsSet = new TreeSet<>();
                        for (int j = 0; j < timings.length; j++) {
                            timingsSet.add(Integer.valueOf(timings[j].split(":")[1].trim()));
                        }
                        direction.addTimings(hour, timingsSet);
                    }
                    ++i;
                }
                directions.add(direction);
            }
        }
        return directions;
    }
}
