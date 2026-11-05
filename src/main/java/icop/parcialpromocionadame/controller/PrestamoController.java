package icop.parcialpromocionadame.controller;


import icop.parcialpromocionadame.dto.RequestPrestamoDto;
import icop.parcialpromocionadame.exception.OperacionInvalidaException;
import icop.parcialpromocionadame.exception.RecursoNoEncontradoException;
import icop.parcialpromocionadame.service.PrestamoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    // para crear un prestamo POST /api/prestamos
    @PostMapping
    public ResponseEntity<?> crearPrestamo(@RequestBody RequestPrestamoDto dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(prestamoService.crearPrestamo(dto));
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (OperacionInvalidaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    // para listar préstamos → GET /api/prestamos
    @GetMapping
    public ResponseEntity<?> listarPrestamos() {
        try {
            return ResponseEntity.ok(prestamoService.listarPrestamos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
   


    // para borrar préstamo  DELETE /api/prestamos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPrestamo(@PathVariable Long id) {
        try {
            prestamoService.eliminarPrestamo(id);
            return ResponseEntity.noContent().build();
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }







    // para buscar préstamo por id → GET /api/prestamos/{id} NO LO ESTOY USANDO RECORDAR BORRAR
    /*
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPrestamo(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(prestamoService.obtenerPrestamo(id));
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
 */

















}