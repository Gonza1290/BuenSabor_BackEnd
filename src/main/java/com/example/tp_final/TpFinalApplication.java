package com.example.tp_final;

import com.example.tp_final.DTO.ArticuloManufacturadoDTO;
import com.example.tp_final.Entidades.*;
import com.example.tp_final.Enumeraciones.*;
import com.example.tp_final.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class
TpFinalApplication {
    //Inyeccion de dependencias por atributo
    //
    public static void main(String[] args) {
        SpringApplication.run(TpFinalApplication.class, args);
    }
    @Bean
    public CommandLineRunner run() {
        return (args) -> {
            //Hardcode
        };
    }
}
