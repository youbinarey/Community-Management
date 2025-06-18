package com.dam.commune.invoice;

import java.time.LocalDate;


import com.dam.commune.community.Community;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "invoice", uniqueConstraints = @UniqueConstraint(columnNames = { "date", "community_id" }))

public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private Double electricity;
    private Double water;
    private Double trash;
    private Double elevator;
    private Double maintenance;

    @ManyToOne
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;

}
