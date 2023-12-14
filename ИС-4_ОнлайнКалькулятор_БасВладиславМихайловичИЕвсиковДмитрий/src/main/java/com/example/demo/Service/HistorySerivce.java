package com.example.demo.Service;

import com.example.demo.Entity.History;
import com.example.demo.Entity.User;
import com.example.demo.Repo.HistoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistorySerivce {


    private final HistoryRepo repo;

    public HistorySerivce(HistoryRepo repo) {
        this.repo = repo;
    }

    public void save(History history){repo.save(history);}

    public Iterable<History> getAll(){return repo.findAll();}

    public void del(Long id){repo.deleteById(id);}
    public Optional<History> findById(Long id){return repo.findById(id);}

    public Iterable<History> findByUserId(Long id){return repo.findByUsr_id(id);}






}
