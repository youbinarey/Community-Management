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
    private int storageNumber;
    private String communityName;
    private String ownerName;
}