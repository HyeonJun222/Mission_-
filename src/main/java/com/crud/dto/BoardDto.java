package com.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class BoardDto {
    private Long id;
    private String name;
    private String text;
    private String password;
}
