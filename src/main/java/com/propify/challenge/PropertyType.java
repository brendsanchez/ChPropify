package com.propify.challenge;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public enum PropertyType {
    SINGLE_FAMILY("Single Family"),
    MULTI_FAMILY("Multi-family"),
    CONDOMINIUM("Condominium"),
    TOWNHOUSE("Townhouse");

    private String type;
}
