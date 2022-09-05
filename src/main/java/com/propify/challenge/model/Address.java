package com.propify.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Address {

    @NotBlank(message = "street must not be null or blank")
    private String street; // must not be null or blank

    @NotBlank(message = "city must not be null or blank")
    private String city; // must not be null or blank

    @NotBlank(message = "state must not be null or blank")
    @Pattern(regexp = "^[A-z]{2}$", message = "state 2-letter code")
    private String state; // 2-letter code, must not be null or blank

    @NotBlank(message = "zip must not be null or blank")
    @Pattern(regexp = "^[0-9]{5}$", message = "5-digit code")
    private String zip; // 5-digit code, must not be null or blank

    @Pattern(regexp="^(?:-?(?:1[01]|[0-9])(?:\\.\\d{1,2})?|12)$", message = "timezone not valid, example: 0, 1, 2, 3.5, -11 etc")
    private String timezone; // Must be a valid timezone
}
