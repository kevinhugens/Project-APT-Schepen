package com.example.schepen.controller;

import com.example.schepen.model.Container;
import com.example.schepen.model.Schip;
import com.example.schepen.repository.SchipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SchipController {
    @Autowired
    private SchipRepository schipRepository;

    @GetMapping("/schepen/naam/{naam}")
    public List<Schip> getSchepenByName(@PathVariable String naam){
        return schipRepository.getAllByName(naam);
    }

    @GetMapping("/schepen/startlocatie/{locatie}")
    public List<Schip> getSchepenByStartlocatie(@PathVariable String locatie){
        return schipRepository.getAllByStartLocatie(locatie);
    }

    @GetMapping("/schepen/eindlocatie/{locatie}")
    public List<Schip> getSchepenByEindlocatie(@PathVariable String locatie){
        return schipRepository.getAllByEindLocatie(locatie);
    }

    @PostConstruct
    public void fillDB() {
        if(schipRepository.count() == 0){
            List<Container> lijstContainersSchip1 = new ArrayList<Container>();
            lijstContainersSchip1.add(new Container(1,1500,"Speelgoed","Antwerpen","New York"));
            lijstContainersSchip1.add(new Container(1,2000,"Fruit","Antwerpen","New York"));

            List<Container> lijstContainersSchip2 = new ArrayList<Container>();
            lijstContainersSchip2.add(new Container(2,1000,"Groenten","Amsterdam","Parijs"));
            lijstContainersSchip2.add(new Container(2,1300,"Multimedia","Amsterdam","Parijs"));

            schipRepository.save(new Schip("Schip 1",500,"Antwerpen","New York",lijstContainersSchip1,1));
            schipRepository.save(new Schip("Schip 2",300,"Amsterdam","Parijs",lijstContainersSchip2,1));
        }
    }
}
