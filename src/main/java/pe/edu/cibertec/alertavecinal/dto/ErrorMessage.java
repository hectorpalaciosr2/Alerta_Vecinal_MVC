package pe.edu.cibertec.alertavecinal.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Builder
@Data
public class ErrorMessage {
    private Integer statusCode;
    private String message;
    private LocalDate dateError;
}
