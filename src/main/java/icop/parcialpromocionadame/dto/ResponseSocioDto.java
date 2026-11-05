package icop.parcialpromocionadame.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseSocioDto {
    private String nombreCompleto;
    private String dni;
}