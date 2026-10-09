package com.example.beta_1_retoapp.services;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.beta_1_retoapp.models.Reto;
import com.example.beta_1_retoapp.repository.IRepositorioReto;


@Service
public class RetoServicio {

    private final IRepositorioReto retoRepository;

    public RetoServicio(IRepositorioReto retoRepository) {
        this.retoRepository = retoRepository;
    }

    private void validarReto(Reto reto) {
        if (reto == null) {
            throw new IllegalArgumentException("El reto no puede ser nulo");
        }
        if (reto.getId() == null) {
            throw new IllegalArgumentException("El ID del reto no puede estar vacío");
        }
    }

    private void validarRetoNuevo(Reto reto) {
        validarReto(reto);
        if (retoRepository.existsById(reto.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El ID del reto ya existe");
        }
    }

    public Reto guardarReto(Reto reto) {
        validarRetoNuevo(reto);
        return retoRepository.save(reto);
    }

    public List<Reto> obtenerRetos() {
        return retoRepository.findAll();
    }

    public Reto modificarReto(Reto reto) {
        validarReto(reto);
        Optional<Reto> retoExistente = retoRepository.findById(reto.getId());
        if (retoExistente.isPresent()) {
            return retoRepository.save(reto);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El reto no existe");
        }
    }

    public boolean eliminar(UUID id) {
        if (retoRepository.existsById(id)) {
            retoRepository.deleteById(id);
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el reto");
        }
    }
}
