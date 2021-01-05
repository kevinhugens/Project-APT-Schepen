package com.example.schepen.controller;

import com.example.schepen.model.Schip;
import com.example.schepen.repository.SchipRepository;
import com.example.schepen.service.SchipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class SchipController {


    @Autowired
    private SchipRepository schipRepository;

    @Autowired
    private SchipService schipService;

    @GetMapping("/schepen/{id}")
    public Schip getContainerById(
            @PathVariable int id) {
        return schipService.getBySchipId(id);
    }

    @GetMapping("/schepen")
    public List<Schip> getAllSchips() {
        return schipService.getAllSchips();
    }

    @GetMapping("/schepen/naam/{naam}")
    public Schip getSchipByNaam(
            @PathVariable String naam) {
        return schipService.getSchipByNaam(naam);
    }

    @GetMapping("/schepen/startlocatie/{locatie}")
    public List<Schip> getSchepenByStartlocatie(
            @PathVariable String locatie){
        return schipService.getSchepenByStartlocatie(locatie);
    }

    @GetMapping("/schepen/rederij/{id}")
    public List<Schip> getSchepenByRederijID(
            @PathVariable String id) {
        return schipService.getSchepenByRederijId(id);
    }

    @GetMapping("/schepen/eindlocatie/{locatie}")
    public List<Schip> getSchepenByEindlocatie(
            @PathVariable String locatie){
        return schipService.getSchepenByEindLocatie(locatie);
    }

    @PutMapping("/schepen")
    public Schip updateSchip(@RequestBody Schip schip) {
        Schip retrievedSchip;
        retrievedSchip = schipService.updateSchip(schip);

        return retrievedSchip;
    }

    @PostMapping("/schepen")
    public Schip addNewSchip(@RequestBody Schip schip) {
        schipService.insertSchip(schip);
        return schip;
    }

    @DeleteMapping("/schepen/{id}")
    public ResponseEntity deleteSchip(@PathVariable Integer schipID) {
        Schip schip = schipRepository.findById(schipID).get();

        if (schip != null) {
            schipRepository.delete(schip);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostConstruct
    public void fillDB() {
        if (schipRepository.count() == 0){
            schipRepository.save(new Schip("Schip 1",500,"Antwerpen","New York","1"));
            schipRepository.save(new Schip("Schip 2",300,"Amsterdam","Parijs","1"));
        }
    }
}
