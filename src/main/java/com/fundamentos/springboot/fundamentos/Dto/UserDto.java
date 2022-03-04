package com.fundamentos.springboot.fundamentos.Dto;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter @ToString
public class UserDto {
    private Long id;
    private String name;
    private LocalDate birthDate;

    public UserDto(Long id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public UserDto() {
    }
}
