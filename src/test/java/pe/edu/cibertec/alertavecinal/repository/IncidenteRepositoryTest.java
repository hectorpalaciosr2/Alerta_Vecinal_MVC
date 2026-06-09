package pe.edu.cibertec.alertavecinal.repository;

import pe.edu.cibertec.alertavecinal.model.Incidente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {
    "spring.datasource.url=jdbc:h2:mem:testdb",
    "spring.datasource.driverClassName=org.h2.Driver",
    "spring.datasource.username=sa",
    "spring.datasource.password=",
    "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
    "spring.jpa.hibernate.ddl-auto=create-drop"
})
public class IncidenteRepositoryTest {

    @Autowired
    private IncidenteRepository incidenteRepository;

    @Test
    public void testInsertarIncidente() {
        Incidente incidente = new Incidente();
        incidente.setTipo("Personas Sospechosas");
        incidente.setDescripcion("Dos sujetos tomando fotos a las casas");
        incidente.setDireccion("Jirón Los Recuerdos cuadra 2");
        incidente.setEstado("PENDIENTE");

        Incidente guardado = incidenteRepository.save(incidente);

        assertThat(guardado).isNotNull();
        assertThat(guardado.getId()).isGreaterThan(0);
    }

    @Test
    public void testActualizarIncidente() {
        Incidente incidente = new Incidente();
        incidente.setTipo("Vehículo Sospechoso");
        incidente.setDescripcion("Auto con lunas polarizadas estacionado");
        incidente.setDireccion("Jirón Los Recuerdos cuadra 3");
        incidente.setEstado("PENDIENTE");
        Incidente guardado = incidenteRepository.save(incidente);


        guardado.setEstado("ATENDIDO");
        Incidente actualizado = incidenteRepository.save(guardado);

        assertThat(actualizado.getEstado()).isEqualTo("ATENDIDO");
    }

    @Test
    public void testEliminarIncidente() {
        Incidente incidente = new Incidente();
        incidente.setTipo("Ruidos Molestos");
        incidente.setDescripcion("Fiesta con volumen alto en la madrugada");
        incidente.setDireccion("Jirón Los Recuerdos cuadra 1");
        incidente.setEstado("PENDIENTE");
        Incidente guardado = incidenteRepository.save(incidente);

        incidenteRepository.delete(guardado);

        Optional<Incidente> buscado = incidenteRepository.findById(guardado.getId());
        assertThat(buscado).isEmpty();
    }

    @Test
    public void testListarIncidentes() {
        Incidente inc1 = new Incidente();
        inc1.setTipo("Robo");
        inc1.setDescripcion("Arrebato de celular a un vecino");
        inc1.setDireccion("Jirón Los Recuerdos cuadra 4");
        inc1.setEstado("PENDIENTE");
        Incidente inc2 = new Incidente();
        inc2.setTipo("Persona Sospechosa");
        inc2.setDescripcion("Sujeto merodeando las casas");
        inc2.setDireccion("Jirón Los Recuerdos cruce con Velasco Astete");
        inc2.setEstado("PENDIENTE");
        
        incidenteRepository.save(inc1);
        incidenteRepository.save(inc2);

        List<Incidente> incidentes = incidenteRepository.findAll();

        assertThat(incidentes).isNotNull();
        assertThat(incidentes.size()).isGreaterThanOrEqualTo(2);
    }
}
