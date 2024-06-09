package com.bewell.ms.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.Date;

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

    @NotNull(message = "The date of birth is required.")
    @Past(message = "The date of birth must be in the past.")
    private Date birthDate;

    @NotBlank(message = "The gender is required.")
    private String gender;
}
