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
    public Schip getSchipById(
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

    @PutMapping("/schepen/update")
    public Schip updateSchip(@RequestBody Schip schip) {
        Schip retrievedSchip;
        retrievedSchip = schipService.updateSchip(schip);

        return retrievedSchip;
    }

    @PostMapping("/schepen/insert")
    public Schip addNewSchip(@RequestBody Schip schip) {
        schipService.insertSchip(schip);
        return schip;
    }

    @DeleteMapping("/schepen/delete/{id}")
    public void deleteSchip(@PathVariable int id) {
        schipRepository.deleteById(id);
    }

    @PostConstruct
    public void fillDB() {
        if (schipRepository.count() == 0){
            schipRepository.save(new Schip("Schip 1",500,"Antwerpen","New York","1"));
            schipRepository.save(new Schip("Schip 2",300,"Amsterdam","Parijs","1"));
        }
    }
}
