package com.example.schepen.service;

import com.example.schepen.model.Schip;
import com.example.schepen.repository.SchipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchipService {

    @Autowired
    private SchipRepository schipRepository;

    public Schip getSchipByNaam(String naam) {
        Schip schip;
        schip = schipRepository.getByNaam(naam);

        return schip;
    }

    public List<Schip> getSchepenByStartlocatie(String startLocatie) {
        List<Schip> schips;
        schips = schipRepository.getAllByStartLocatie(startLocatie);
        return schips;
    }

    public List<Schip> getSchepenByEindLocatie(String eindLocatie) {
        List<Schip> schips;
        schips = schipRepository.getAllByEindLocatie(eindLocatie);
        return schips;
    }

    public Schip updateSchip(Schip schip) {
        Schip newSchip = schipRepository.findById(schip.getId()).orElseThrow();

        newSchip.setCapaciteit(schip.getCapaciteit());
        newSchip.setEindLocatie(schip.getEindLocatie());
        newSchip.setStartLocatie(schip.getStartLocatie());
        newSchip.setRederijId(schip.getRederijId());
        newSchip.setNaam(schip.getNaam());

        schipRepository.save(newSchip);

        return newSchip;
    }

    public Schip insertSchip(Schip schip) {
        return schipRepository.save(schip);
    }


}
