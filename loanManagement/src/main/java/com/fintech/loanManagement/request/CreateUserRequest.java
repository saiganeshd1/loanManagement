package com.fintech.loanManagement.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUserRequest {
    @NotBlank(message = "Name is mandatory")
    public String name;

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is mandatory")
    public String email;

    @Pattern(regexp = "^\\d{10}$", message = "Phone must be 10 digits")
    public String phone;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    public Date dateOfBirth;

    @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$", message = "PAN number must be valid (e.g. ABCDE1234F)")
    private String panNo;

    @Pattern(regexp = "^\\d{12}$", message = "Aadhar number must be exactly 12 digits")
    private String aadhaarNo;
}
