package icop.parcialpromocionadame.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "prestamo")
@Getter
@Setter
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo_libro")
    private String tituloLibro;

    @Column(name = "fecha_prestamo")
    private LocalDate fechaPrestamo;

    private Boolean devuelto;

    
    @ManyToOne
    @JoinColumn(name = "socio_id", nullable = false)
    private Socio socio;
}
