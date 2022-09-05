package com.propify.challenge;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Address {

    public String street; // must not be null or blank

    public String city; // must not be null or blank

    public String state; // 2-letter code, must not be null or blank

    public String zip; // 5-digit code, must not be null or blank

    public String timezone; // Must be a valid timezone
}
