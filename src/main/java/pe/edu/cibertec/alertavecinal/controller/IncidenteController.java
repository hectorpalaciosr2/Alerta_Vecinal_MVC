package pe.edu.cibertec.alertavecinal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.alertavecinal.dto.GenericResponseDto;
import pe.edu.cibertec.alertavecinal.exception.ResourceNotFoundException;
import pe.edu.cibertec.alertavecinal.model.Incidente;
import pe.edu.cibertec.alertavecinal.service.IncidenteService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/incidents")
public class IncidenteController {

    private final IncidenteService incidenteService;

    @GetMapping
    public ResponseEntity<GenericResponseDto<List<Incidente>>> listarIncidentes() {
        List<Incidente> incidentes = incidenteService.listarTodos();
        if(incidentes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        GenericResponseDto<List<Incidente>> response =
                GenericResponseDto.<List<Incidente>>builder()
                        .response(incidentes)
                        .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponseDto<Incidente>> obtenerIncidente(@PathVariable Long id) {
        Incidente incidente = incidenteService.buscarPorId(id);
        if(incidente == null){
            throw new ResourceNotFoundException(
                    "El incidente con ID = " + id + " no existe.");
        }
        GenericResponseDto<Incidente> response =
                GenericResponseDto.<Incidente>builder()
                        .response(incidente)
                        .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GenericResponseDto<Incidente>> crearIncidente(@RequestBody Incidente incidente) {
        Incidente nuevo = incidenteService.guardar(incidente);
        GenericResponseDto<Incidente> response =
                GenericResponseDto.<Incidente>builder()
                        .response(nuevo)
                        .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponseDto<Incidente>> actualizarIncidente(@PathVariable Long id, @RequestBody Incidente detalles) {
        Incidente actualizado = incidenteService.actualizar(id, detalles);
        if(actualizado == null){
            throw new ResourceNotFoundException(
                    "El incidente con ID = " + id + " no existe.");
        }
        GenericResponseDto<Incidente> response =
                GenericResponseDto.<Incidente>builder()
                        .response(actualizado)
                        .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponseDto<String>> eliminarIncidente(@PathVariable Long id) {
        Incidente incidente = incidenteService.buscarPorId(id);
        if(incidente == null){
            throw new ResourceNotFoundException(
                    "El incidente con ID = " + id + " no existe.");
        }
        incidenteService.eliminar(id);
        GenericResponseDto<String> response =
                GenericResponseDto.<String>builder()
                        .response("Incidente eliminado exitosamente")
                        .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
