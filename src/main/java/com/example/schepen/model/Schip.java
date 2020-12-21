package com.example.schepen.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
@Getter
@Setter
public class Schip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int capaciteit;
    private String startLocatie;
    private String eindLocatie;
    private int rederijId;

    public Schip() {
    }

    public Schip(String name, int capaciteit, String startLocatie, String eindLocatie, int rederijId) {
        this.name = name;
        this.capaciteit = capaciteit;
        this.startLocatie = startLocatie;
        this.eindLocatie = eindLocatie;
        this.rederijId = rederijId;
    }
}
