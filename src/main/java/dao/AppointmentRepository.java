package dao;

import entity.Animal;
import entity.Appointment;
import entity.Vet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {

    List<Appointment> findAllByVetAndAppointmentDateBetween(Vet vet, LocalDateTime min, LocalDateTime max);
    List<Appointment> findAllByAnimalAndAppointmentDateBetween(Animal animal, LocalDateTime min, LocalDateTime max);
}
