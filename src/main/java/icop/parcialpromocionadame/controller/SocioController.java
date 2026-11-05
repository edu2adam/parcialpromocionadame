package icop.parcialpromocionadame.controller;


import icop.parcialpromocionadame.dto.RequestSocioDto;
import icop.parcialpromocionadame.dto.ResponseSocioDto;
import icop.parcialpromocionadame.exception.OperacionInvalidaException;
import icop.parcialpromocionadame.exception.RecursoNoEncontradoException;
import icop.parcialpromocionadame.service.SocioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/socios")
public class SocioController {

    private final SocioService socioService;

    public SocioController(SocioService socioService) {
        this.socioService = socioService;
    }

    // para crear un socio  POST /api/socios
    @PostMapping
    public ResponseEntity<?> crearSocio(@RequestBody RequestSocioDto dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(socioService.crearSocio(dto));
        } catch (OperacionInvalidaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    // para listar socios  GET /api/socios
    @GetMapping
    public ResponseEntity<?> listarSocios() {
        try {
            return ResponseEntity.ok(socioService.listarSocios());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    // para borrar socio  DELETE /api/socios/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarSocio(@PathVariable Long id) {
        try {
            socioService.eliminarSocio(id);
            return ResponseEntity.noContent().build();
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (OperacionInvalidaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
















    

    // para buscar socio por id GET /api/socios/{id} NO LO ESTOY USANDO RECORDAR BORRAR
    /*
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerSocio(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(socioService.obtenerSocio(id));
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
         */



}