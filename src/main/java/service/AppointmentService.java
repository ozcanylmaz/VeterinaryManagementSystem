package service;

import dao.AppointmentDAO;
import dao.AppointmentRepository;
import entity.Animal;
import entity.Appointment;
import entity.Vet;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {


    private final AppointmentRepository appointmentRepository;
    // Validsayon için AnimalDAO ve VetDAO'nun buraya eklenmesi ileri seviye bir geliştirme olacaktır.

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;

    }

    public Appointment saveAppointment(Appointment appointment) {

        if (appointment.getAppointmentDate() == null || appointment.getAnimal() == null || appointment.getVet() == null) {
            throw new IllegalArgumentException("Validation Error: Appointment date, Animal ID, and Vet ID cannot be empty.");
        }

        // KRİTİK İŞ KURALI: Çakışma Kontrolü
        int vetId = appointment.getVet().getId();
        LocalDateTime date = appointment.getAppointmentDate();

        List<Appointment> existingAppointments;
        LocalDateTime end = null;
        Vet vet = new Vet();
        vet.setId(appointment.getVet().getId());
        existingAppointments = appointmentRepository.findAllByVetAndAppointmentDateBetween(vet, date, end);

        if (!existingAppointments.isEmpty()) {
            throw new IllegalArgumentException("Business Rule Violation: Vet " + vetId + " is already booked on " + date + ".");
        }

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getAppointmentsByDateRangeAndAnimal(int animalId, LocalDateTime start, LocalDateTime end) {
        Animal animal = new Animal();
        animal.setId(animalId);
        return appointmentRepository.findAllByAnimalAndAppointmentDateBetween(animal, start, end);
    }

    // Projeyi tamamlamak için find, update ve delete metotları da burada yer almalıdır.
}