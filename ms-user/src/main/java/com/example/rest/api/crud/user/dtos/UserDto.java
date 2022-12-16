package com.example.rest.api.crud.user.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserDto {

    @NotBlank
    String username;
    @NotBlank
    String password;
    @Email
    String email;
}
