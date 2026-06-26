package com.doumo.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonResponse {
    private String id;
    private String name;
    private String lastName;
    private String email;
    private Integer age;
    private String regionName;
    private String communeName;
}