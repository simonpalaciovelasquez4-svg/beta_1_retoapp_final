package com.example.beta_1_retoapp.services;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.beta_1_retoapp.models.Empresa;
import com.example.beta_1_retoapp.repository.IRepositorioEmpresa;


@Service
public class EmpresaServicio {

    private final IRepositorioEmpresa empresaRepository;

    public EmpresaServicio(IRepositorioEmpresa empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    private void validarEmpresa(Empresa empresa) {
        if (empresa == null) {
            throw new IllegalArgumentException("La empresa no puede ser nula");
        }
        if (empresa.getId() == null) {
            throw new IllegalArgumentException("El ID de la empresa no puede estar vacío");
        }
    }

    private void validarEmpresaNueva(Empresa empresa) {
        validarEmpresa(empresa);
        if (empresaRepository.existsById(empresa.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El ID de la empresa ya existe");
        }
    }

    public Empresa guardarEmpresa(Empresa empresa) {
        validarEmpresaNueva(empresa);
        return empresaRepository.save(empresa);
    }

    public List<Empresa> obtenerEmpresas() {
        return empresaRepository.findAll();
    }

    public Empresa modificarEmpresa(Empresa empresa) {
        validarEmpresa(empresa);
        Optional<Empresa> empresaExistente = empresaRepository.findById(empresa.getId());
        if (empresaExistente.isPresent()) {
            return empresaRepository.save(empresa);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La empresa no existe");
        }
    }

    public boolean eliminar(UUID id) {
        if (empresaRepository.existsById(id)) {
            empresaRepository.deleteById(id);
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró la empresa");
        }
    }
}
