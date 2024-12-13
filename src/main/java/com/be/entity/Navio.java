package com.be.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Navio extends BaseEntity {
    private String nome;
}
