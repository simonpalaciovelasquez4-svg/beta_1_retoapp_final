
package com.example.beta_1_retoapp.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.beta_1_retoapp.models.Empresa;
import com.example.beta_1_retoapp.services.EmpresaServicio;

@RestController 
@RequestMapping("/api/usuarios")
public class EmpresaControlador {

    @Autowired 
    EmpresaServicio servicio;

    //controlador para guardar
    @PostMapping 
    public ResponseEntity<?> guardar(@RequestBody Empresa datos){
        return ResponseEntity.status(HttpStatus.OK).body(servicio.guardarEmpresa(datos));
    }

    //controlador para buscar
    public ResponseEntity<?> buscar(){
        return ResponseEntity.status(HttpStatus.OK).body(servicio.obtenerEmpresas());
    }

    //controlador para modificar
    @PutMapping("{/id}") 
    public ResponseEntity<?> modificar(@PathVariable UUID id, @RequestBody Empresa datos){
        return ResponseEntity.status(HttpStatus.OK).body(servicio.modificarEmpresa(datos));
    }

    //controlador para eliminar
    @DeleteMapping ("{/id}")
    public ResponseEntity<?> eliminar(@PathVariable  UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(servicio.eliminar(id));
    }

}