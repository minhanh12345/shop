package com.example.project_shop.jwt.payload.request;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.Set;
@Data
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    private String fullName;
    private String address;
    private String phone;

}
