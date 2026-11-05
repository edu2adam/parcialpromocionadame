package icop.parcialpromocionadame.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ResponsePrestamoDto {
    private Long id;
    private String tituloLibro;
    private LocalDate fechaPrestamo;
    private Boolean devuelto;
    private String nombreSocio;
}