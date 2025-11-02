package view;

import entity.Appointment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.AppointmentService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<Appointment> create(@RequestBody Appointment appointment){
        Appointment result = appointmentService.saveAppointment(appointment);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAll(){
        List<Appointment> result = appointmentService.getAllAppointments();
        return ResponseEntity.ok(result);
    }

    @GetMapping(params = {"animalId","minDate", "maxDate"})
    public ResponseEntity<List<Appointment>> getAllByDate(
            @RequestParam(name = "animalId") int animalId,
            @RequestParam(name = "minDate") LocalDateTime minDate,
            @RequestParam(name = "maxDate") LocalDateTime maxDate){
        List<Appointment> result = appointmentService.getAppointmentsByDateRangeAndAnimal(animalId, minDate, maxDate);
        return ResponseEntity.ok(result);

    }
}
