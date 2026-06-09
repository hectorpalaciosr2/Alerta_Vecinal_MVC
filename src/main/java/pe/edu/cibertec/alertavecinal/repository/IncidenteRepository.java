package pe.edu.cibertec.alertavecinal.repository;

import pe.edu.cibertec.alertavecinal.model.Incidente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidenteRepository extends JpaRepository<Incidente, Long> {
}
