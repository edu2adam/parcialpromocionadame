package icop.parcialpromocionadame.repository;

import icop.parcialpromocionadame.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

    
    boolean existsBySocioId(Long socioId);

    
    boolean existsByTituloLibroAndDevueltoFalse(String tituloLibro);

    // creo que lo voy a ysar par alistar los prestamos
    List<Prestamo> findByDevueltoFalse();

}