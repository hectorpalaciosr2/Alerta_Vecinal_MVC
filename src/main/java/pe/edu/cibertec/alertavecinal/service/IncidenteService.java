package pe.edu.cibertec.alertavecinal.service;

import lombok.RequiredArgsConstructor;
import pe.edu.cibertec.alertavecinal.exception.ResourceNotFoundException;
import pe.edu.cibertec.alertavecinal.model.Incidente;
import pe.edu.cibertec.alertavecinal.repository.IncidenteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class IncidenteService {

    private final IncidenteRepository incidenteRepository;

    public List<Incidente> listarTodos() {
        return incidenteRepository.findAll();
    }

    public Incidente buscarPorId(Long id) {
        return incidenteRepository.findById(id)
                .orElse(null);
    }

    public Incidente guardar(Incidente incidente) {
        if(incidente.getEstado() == null || incidente.getEstado().isEmpty()){
            incidente.setEstado("PENDIENTE");
        }
        return incidenteRepository.save(incidente);
    }

    public Incidente actualizar(Long id, Incidente incidenteDetalles) {
        Incidente incidente = buscarPorId(id);
        if (incidente == null) return null;
        
        incidente.setTipo(incidenteDetalles.getTipo());
        incidente.setDescripcion(incidenteDetalles.getDescripcion());
        incidente.setDireccion(incidenteDetalles.getDireccion());
        incidente.setEstado(incidenteDetalles.getEstado());
        
        return incidenteRepository.save(incidente);
    }

    public void eliminar(Long id) {
        Incidente incidente = buscarPorId(id);
        if (incidente != null) {
            incidenteRepository.delete(incidente);
        }
    }
}
