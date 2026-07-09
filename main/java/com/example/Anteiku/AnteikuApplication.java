package com.example.Anteiku;

import com.example.Anteiku.service.cafeteriaservicio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AnteikuApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnteikuApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(cafeteriaservicio cafeteriaServicio) {
        return cafeteriaServicio.cargarDatosIniciales();
    }
}
