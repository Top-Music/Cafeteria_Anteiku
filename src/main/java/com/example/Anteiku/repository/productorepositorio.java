package com.example.Anteiku.repository;

import com.example.Anteiku.model.producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface productorepositorio extends JpaRepository<producto, Long> {

    List<producto> findByNombreIgnoreCase(String nombre);
}
