package service;

import dao.AppointmentDAO;
import entity.Appointment;
import java.time.LocalDateTime;
import java.util.List;

public class AppointmentService {

    private final AppointmentDAO appointmentDAO;
    // Validsayon için AnimalDAO ve VetDAO'nun buraya eklenmesi ileri seviye bir geliştirme olacaktır.

    public AppointmentService() {
        this.appointmentDAO = new AppointmentDAO();
    }

    public boolean saveAppointment(Appointment appointment) {

        if (appointment.getAppointmentDate() == null || appointment.getAnimal() == null || appointment.getVet() == null) {
            System.err.println("Validation Error: Appointment date, Animal ID, and Vet ID cannot be empty.");
            return false;
        }

        // KRİTİK İŞ KURALI: Çakışma Kontrolü
        int vetId = appointment.getVet().getId();
        LocalDateTime date = appointment.getAppointmentDate();

        List<Appointment> existingAppointments;
        LocalDateTime end = null;
        existingAppointments = appointmentDAO.findByVetAndDate(vetId, date, end);

        if (!existingAppointments.isEmpty()) {
            System.err.println("Business Rule Violation: Vet " + vetId + " is already booked on " + date + ".");
            return false;
        }

        return appointmentDAO.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentDAO.findAll();
    }

    public List<Appointment> getAppointmentsByDateRangeAndAnimal(int animalId, LocalDateTime start, LocalDateTime end) {
        return appointmentDAO.findByVetAndDate(animalId, start, end);
    }

    // Projeyi tamamlamak için find, update ve delete metotları da burada yer almalıdır.
}