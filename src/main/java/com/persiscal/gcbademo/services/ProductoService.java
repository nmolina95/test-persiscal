package com.persiscal.gcbademo.services;

import com.persiscal.gcbademo.dtos.ProductoDTO;
import org.springframework.http.ResponseEntity;

public interface ProductoService {
    ResponseEntity<?> create(ProductoDTO productoDTO);
    ResponseEntity<?> findById(Long id);
    ResponseEntity<?> findByNombre(String nombre);
    ResponseEntity<?> getAll();
    ResponseEntity<?> update(Long id, ProductoDTO productoDTO);
    ResponseEntity<?> delete(Long id);
}
