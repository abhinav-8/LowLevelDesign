package org.example.lld.Calendar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class User {
    private String id;
    private String name;
    private String email;

//    private List<Event> events; -> Move these to repository for better readability
}
