package com.example.demo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Planet {
    private String name;
    private List<String> residents = Collections.emptyList();

    public List<Integer> getResidentIds() {
        String regex = "(.*)/people/(\\d+)/";
        Pattern pattern = Pattern.compile(regex);

        List<Integer> ids = new ArrayList<>();

        for (String url : residents) {
            Matcher matcher = pattern.matcher(url);
            if (matcher.find()) {
                String id = matcher.group(2);
                ids.add(Integer.valueOf(id));
            }
        }

        return ids;
    }
}
