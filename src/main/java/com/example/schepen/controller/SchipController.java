package com.example.schepen.controller;

import com.example.schepen.model.Schip;
import com.example.schepen.repository.SchipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class SchipController {
    @Autowired
    private SchipRepository schipRepository;

//    @GetMapping("/schepen/naam/{naam}")
//    public List<Schip> getSchepenByName(@PathVariable String naam){
//        return schipRepository.getAllByName(naam);
//    }

    @GetMapping("/schepen/naam/{naam}")
    public Schip getSchipByNaam(@PathVariable String naam) {
        return schipRepository.getByName(naam);
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
            schipRepository.save(new Schip("Schip 1",500,"Antwerpen","New York",1));
            schipRepository.save(new Schip("Schip 2",300,"Amsterdam","Parijs",1));
        }
    }
}
