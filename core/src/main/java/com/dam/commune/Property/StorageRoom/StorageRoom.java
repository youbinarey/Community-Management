package com.dam.commune.property.StorageRoom;

import com.dam.commune.property.Property;

import jakarta.persistence.Entity;

@Entity
public class StorageRoom extends Property{
  private int storageNumber;
}
