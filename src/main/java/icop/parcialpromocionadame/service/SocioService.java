package icop.parcialpromocionadame.service;

import icop.parcialpromocionadame.dto.RequestSocioDto;
import icop.parcialpromocionadame.dto.ResponseSocioDto;
import icop.parcialpromocionadame.entity.Socio;
import icop.parcialpromocionadame.exception.OperacionInvalidaException;
import icop.parcialpromocionadame.exception.RecursoNoEncontradoException;
import icop.parcialpromocionadame.repository.PrestamoRepository;
import icop.parcialpromocionadame.repository.SocioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SocioService {

    private final SocioRepository socioRepository;
    private final PrestamoRepository prestamoRepository;

    public SocioService(SocioRepository socioRepository, PrestamoRepository prestamoRepository) {
        this.socioRepository = socioRepository;
        this.prestamoRepository = prestamoRepository;
    }

    // para crear socios  si entendi bien usa POST /api/socios
    public ResponseSocioDto crearSocio(RequestSocioDto dto) {
        if (socioRepository.existsByDni(dto.getDni())) {
            throw new OperacionInvalidaException("Ya existe un socio con el DNI: " + dto.getDni());
        }
        Socio socio = new Socio();
        socio.setNombre(dto.getNombre());
        socio.setApellido(dto.getApellido());
        socio.setDni(dto.getDni());
        Socio guardado = socioRepository.save(socio);
        return convertirAResponse(guardado);
    }

    // para listar socios , si entendi bien GET /api/socios
    public List<ResponseSocioDto> listarSocios() {
        return socioRepository.findAll().stream()
                .map(s -> convertirAResponse(s))
                .collect(Collectors.toList());
    }

    //Funcion auxiliar
    public Socio buscarSocioPorId(Long id) {
        return socioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Socio no encontrado con id: " + id));
    }


    // para borrrar socios DELETE /api/socios/{id}
    public void eliminarSocio(Long id) {
        Socio socio = buscarSocioPorId(id);
        
        if (prestamoRepository.existsBySocioId(id)) {
            throw new OperacionInvalidaException("No se puede eliminar el socio con id " + id + " porque tiene préstamos asociados.");
        }
        socioRepository.delete(socio);
    }

    // funcon auxiliar
    private ResponseSocioDto convertirAResponse(Socio s) {
        String nombreCompleto = s.getNombre() + " " + s.getApellido();
        return new ResponseSocioDto(nombreCompleto, s.getDni());
    }
}