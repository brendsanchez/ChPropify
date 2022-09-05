package com.propify.challenge;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Property {

    private Integer id; // must be null for INSERT and not null for UPDATE
    //se cambia por que nunca sera null si es tipo int ya que por default es 0

    @Temporal(TemporalType.TIME)
    private Date createTime; //se cambia a tipo date

    private PropertyType type;

    @DecimalMin(value = "0.0")
    private double rentPrice; // must be greater than 0, 2 decimal places

    private Address address; // must not be null

    @Email(message = "must be a valid email addess")
    private String emailAddress; // must be a valid email address

    @NotNull(message = "code must not be null")
    @Pattern(regexp = "^[A-Z0-9]{1,10}(?:List)?$", message = "code only uppercase letters or numbers, 10 characters")
    private String code; // not null, only uppercase letters or numbers, 10 characters
}
