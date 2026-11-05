package icop.parcialpromocionadame.repository;
import icop.parcialpromocionadame.entity.Prestamo;
import icop.parcialpromocionadame.entity.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SocioRepository extends JpaRepository<Socio, Long> {

    // con esto voy a buscar que no exista otro con el mismo DNI
    boolean existsByDni(String dni);

    // Para modificar, creo que lo voy a usar para eliminar
    boolean existsByDniAndIdNot(String dni, Long id);

    // creo que lo voy a usar para listar los socios
    List<Socio> findByDni(String dni);


}