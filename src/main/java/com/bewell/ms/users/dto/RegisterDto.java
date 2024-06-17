package com.bewell.ms.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDto {

    @NotBlank(message = "The email is required.")
    @Email(message = "The email address is invalid.", flags = { Pattern.Flag.CASE_INSENSITIVE })
    private String email;

    @NotBlank(message = "The password is required.")
    private String password;

    @NotBlank(message = "The first name is required.")
    @Size(min = 3, max = 100, message = "The length of first name must be between 3 and 100 characters.")
    private String firstName;

    @NotBlank(message = "The last name is required.")
    @Size(min = 3, max = 100, message = "The length of last name must be between 3 and 100 characters.")
    private String lastName;

    @NotBlank(message = "The gender is required.")
    private String gender;
}
