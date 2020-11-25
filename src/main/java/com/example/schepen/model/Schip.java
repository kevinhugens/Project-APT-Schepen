package com.example.schepen.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

public class Schip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int capaciteit;
    private String startLocatie;
    private String eindLocatie;
    private List<Container> containers;
    private int rederijId;

    public Schip() {
    }

    public Schip(String name, int capaciteit, String startLocatie, String eindLocatie, List<Container> containers, int rederijId) {
        this.name = name;
        this.capaciteit = capaciteit;
        this.startLocatie = startLocatie;
        this.eindLocatie = eindLocatie;
        this.containers = containers;
        this.rederijId = rederijId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartLocatie() {
        return startLocatie;
    }

    public void setStartLocatie(String startLocatie) {
        this.startLocatie = startLocatie;
    }

    public String getEindLocatie() {
        return eindLocatie;
    }

    public void setEindLocatie(String eindLocatie) {
        this.eindLocatie = eindLocatie;
    }

    public int getCapaciteit() {
        return capaciteit;
    }

    public void setCapaciteit(int capaciteit) {
        this.capaciteit = capaciteit;
    }

    public List<Container> getContainers() {
        return containers;
    }

    public void setContainers(List<Container> containers) {
        this.containers = containers;
    }

    public int getRederijId() {
        return rederijId;
    }

    public void setRederijId(int rederijId) {
        this.rederijId = rederijId;
    }
}
