package com.doumo.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String id;
    private String name;
    private String lastName;
    private String email;
    private Integer age;
    private String regionId;
    private String communeId;
}