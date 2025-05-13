package com.dam.commune.property.StorageRoom;

import com.dam.commune.property.Property;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class StorageRoom extends Property {
  private int storageNumber;
}
