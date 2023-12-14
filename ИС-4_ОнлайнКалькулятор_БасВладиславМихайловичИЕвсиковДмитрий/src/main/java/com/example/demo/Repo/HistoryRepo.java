package com.example.demo.Repo;

import com.example.demo.Entity.History;
import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HistoryRepo extends JpaRepository<History, Long> {
    Iterable<History> findByUsr_id(Long id);
}
