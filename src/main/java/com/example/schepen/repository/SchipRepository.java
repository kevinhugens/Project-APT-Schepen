package com.example.schepen.repository;

import com.example.schepen.model.Schip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchipRepository extends JpaRepository<Schip, Integer> {
    Schip getByName(String name);
    List<Schip> getAllByStartLocatie(String locatie);
    List<Schip> getAllByEindLocatie(String locatie);
    List<Schip> getSchipsByRederijId(int rederijID);
}