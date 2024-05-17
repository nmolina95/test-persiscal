package com.persiscal.gcbademo.controllers;

import com.persiscal.gcbademo.dtos.ProductoDTO;
import com.persiscal.gcbademo.services.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    ProductoService productoService;

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProducto(@PathVariable("id") Long id) {
        return productoService.findById(id);
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> obtenerPorNombre(@RequestParam String nombre) {
        return productoService.findByNombre(nombre);
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodos() {
        return productoService.getAll();
    }

    @PostMapping
    public ResponseEntity<?> crearProducto(@RequestBody @Valid ProductoDTO productoDTO) {
        return productoService.create(productoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable("id") Long id, @RequestBody ProductoDTO productoDTO) {
        return productoService.update(id, productoDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> borrarProducto(@PathVariable("id") Long id) {
        return productoService.delete(id);
    }
}
