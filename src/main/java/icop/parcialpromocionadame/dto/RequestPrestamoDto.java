package icop.parcialpromocionadame.dto;




import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class RequestPrestamoDto {
    private String tituloLibro;
    private LocalDate fechaPrestamo;
    private Long socioId;
}