package icop.parcialpromocionadame.service;
import icop.parcialpromocionadame.dto.RequestPrestamoDto;
import icop.parcialpromocionadame.dto.ResponsePrestamoDto;

import icop.parcialpromocionadame.entity.Prestamo;
import icop.parcialpromocionadame.entity.Socio;
import icop.parcialpromocionadame.exception.OperacionInvalidaException;
import icop.parcialpromocionadame.exception.RecursoNoEncontradoException;
import icop.parcialpromocionadame.repository.PrestamoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final SocioService socioService;

    public PrestamoService(PrestamoRepository prestamoRepository, SocioService socioService) {
        this.prestamoRepository = prestamoRepository;
        this.socioService = socioService;
    }

    // para crear préstamo si entendi bien es POST /api/prestamos
    public ResponsePrestamoDto crearPrestamo(RequestPrestamoDto dto) {
        Socio socio = socioService.buscarSocioPorId(dto.getSocioId());
        //validarTitulo(dto.getTituloLibro());
        if (prestamoRepository.existsByTituloLibroAndDevueltoFalse(dto.getTituloLibro())) {
            throw new OperacionInvalidaException("El libro '" + dto.getTituloLibro() + "' ya está prestado.");
        }
        
        Prestamo prestamo = new Prestamo();
        prestamo.setTituloLibro(dto.getTituloLibro());
        prestamo.setFechaPrestamo(dto.getFechaPrestamo() != null ? dto.getFechaPrestamo() : LocalDate.now());
        prestamo.setDevuelto(false);
        prestamo.setSocio(socio);
        Prestamo guardado = prestamoRepository.save(prestamo);
        return convertirAResponse(guardado);
    }

    // para listar los prestamos GET /api/prestamos
    public List<ResponsePrestamoDto> listarPrestamos() {
        return prestamoRepository.findAll().stream()
                .map(p -> convertirAResponse(p))
                .collect(Collectors.toList());
    }
    //para eliminar prestamos
    
    public void eliminarPrestamo(Long id) {
        Prestamo prestamo = buscarPrestamoPorId(id);
        prestamoRepository.delete(prestamo);
    }


    // para buscar el prestamo de un socio GET /api/prestamos/socio/{socioId}
    public List<ResponsePrestamoDto> listarPrestamosDeSocio(Long socioId) {
        Socio socio = socioService.buscarSocioPorId(socioId);
        return socio.getPrestamos().stream()
                .map(p -> convertirAResponse(p))
                .collect(Collectors.toList());
    }

    private Prestamo buscarPrestamoPorId(Long id) {
        return prestamoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Préstamo no encontrado con id: " + id));
    }
    private ResponsePrestamoDto convertirAResponse(Prestamo p) {
        String nombreSocio = p.getSocio().getNombre() + " " + p.getSocio().getApellido();
        return new ResponsePrestamoDto(p.getId(), p.getTituloLibro(), p.getFechaPrestamo(),
                p.getDevuelto(), nombreSocio);
    }
/*
    

*/

}