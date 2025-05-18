package com.dam.commune.community;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunityDTO {
    private String address;
    private String postalCode;
    private int propertiesCount;
}
