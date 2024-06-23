package com.bewell.ms.users.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class UserUpdateRequest {

    @NotBlank(message = "The first name is required.")
    @Size(min = 3, max = 100, message = "The length of first name must be between 3 and 100 characters.")
    private String firstName;

    @NotBlank(message = "The last name is required.")
    @Size(min = 3, max = 100, message = "The length of last name must be between 3 and 100 characters.")
    private String lastName;

    private Date birthDate;

    @NotBlank(message = "The gender is required.")
    private String gender;
}
