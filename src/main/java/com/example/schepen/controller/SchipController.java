package com.example.schepen.controller;

import com.example.schepen.model.Schip;
import com.example.schepen.repository.SchipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/schepen")
    public Schip updateSchip(@RequestBody Schip schip) {
        Schip retrievedSchip = schipRepository.findById(schip.getId()).get();
        retrievedSchip.setCapaciteit(schip.getCapaciteit());
        retrievedSchip.setEindLocatie(schip.getEindLocatie());
        retrievedSchip.setStartLocatie(schip.getStartLocatie());
        retrievedSchip.setRederijId(schip.getRederijId());
        retrievedSchip.setName(schip.getName());
        schipRepository.save(retrievedSchip);
        return retrievedSchip;
    }

    @PostMapping("/schepen")
    public Schip addNewSchip(@RequestBody Schip schip) {
        schipRepository.save(schip);
        return schip;
    }

    @DeleteMapping("/schepen/{id}")
    public ResponseEntity deleteSchip(@PathVariable Integer schipID) {
        Schip schip = schipRepository.findById(schipID).get();
        if(schip != null) {
            schipRepository.delete(schip);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostConstruct
    public void fillDB() {
        if(schipRepository.count() == 0){
            schipRepository.save(new Schip("Schip 1",500,"Antwerpen","New York",1));
            schipRepository.save(new Schip("Schip 2",300,"Amsterdam","Parijs",1));
        }
    }
}
