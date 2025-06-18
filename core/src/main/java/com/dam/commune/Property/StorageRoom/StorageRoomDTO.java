package com.dam.commune.property.storageRoom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StorageRoomDTO {
    private Long id;
    private String cadastralReference;
    private Double squareMeters;
    private Integer storageNumber;
    private Double coefficient;
    private String communityName;
    private String ownerName;
    private String ownerDni;

}