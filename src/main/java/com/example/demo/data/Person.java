package com.example.demo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String name;
    private List<String> starships = Collections.emptyList();

    public Optional<Integer> getFirstStarshipId() {
        String regex = "(.*)/starships/(\\d+)/";
        Pattern pattern = Pattern.compile(regex);
        for (String url : starships) {
            Matcher matcher = pattern.matcher(url);
            if (matcher.find()) {
                String id = matcher.group(2);
                return Optional.of(Integer.valueOf(id));
            }
        }

        return Optional.empty();
    }
}
