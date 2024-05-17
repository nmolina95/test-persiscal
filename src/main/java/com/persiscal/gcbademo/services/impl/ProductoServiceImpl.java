package com.persiscal.gcbademo.services.impl;

import com.persiscal.gcbademo.dtos.ProductoDTO;
import com.persiscal.gcbademo.models.Producto;
import com.persiscal.gcbademo.repositories.ProductoRepository;
import com.persiscal.gcbademo.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    ProductoRepository productoRepository;

    @Override
    public ResponseEntity<?> findById(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findByNombre(String nombre) {
        Producto producto = productoRepository.findByNombre(nombre)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(productoRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> create(ProductoDTO productoDTO) {
        return armarProducto(productoDTO);
    }

    @Override
    @Transactional
    public ResponseEntity<?> update(Long id, ProductoDTO productoDTO) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
        Boolean seActualizo = false;

        if(productoDTO.getNombre() != null && !productoDTO.getNombre().isEmpty()) {
            producto.setNombre(productoDTO.getNombre());
            seActualizo = true;
        }
        if(productoDTO.getDescripcion() != null) {
            producto.setDescripcion(productoDTO.getDescripcion());
            seActualizo = true;
        }
        if(productoDTO.getCantidad() != null && productoDTO.getCantidad() >= 0) {
            producto.setCantidad(productoDTO.getCantidad());
            seActualizo = true;
        }
        if(productoDTO.getPrecio() != null && productoDTO.getPrecio() >= 0) {
            producto.setPrecio(productoDTO.getPrecio());
            seActualizo = true;
        }

        if(!seActualizo) {
            return new ResponseEntity<>("No se pasó ningún dato para la actualización", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(productoRepository.save(producto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

        productoRepository.delete(producto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private ResponseEntity<?> armarProducto(ProductoDTO productoDTO) {
        if(productoDTO.getNombre() == null || productoDTO.getCantidad() == null || productoDTO.getDescripcion() == null
        || productoDTO.getPrecio() == null) {
            return new ResponseEntity<>("Faltan completar datos del producto", HttpStatus.BAD_REQUEST);
        }

        Producto producto = Producto.builder()
                .nombre(productoDTO.getNombre())
                .cantidad(productoDTO.getCantidad())
                .descripcion(productoDTO.getDescripcion())
                .precio(productoDTO.getPrecio())
                .build();

        productoRepository.save(producto);

        return new ResponseEntity<>(producto, HttpStatus.CREATED);
    }
}
