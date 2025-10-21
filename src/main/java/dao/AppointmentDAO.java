package dao;

import core.DBConnector;
import entity.Appointment;
import entity.Animal;
import entity.Vet;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    // Yardımcı metot: ResultSet'ten Appointment, Animal ve Vet nesnelerini oluşturur.
    private Appointment createAppointmentFromResultSet(ResultSet rs) throws SQLException {
        Appointment appointment = new Appointment();
        appointment.setId(rs.getInt("id"));

        // TIMESTAMP verisini LocalDateTime'a dönüştürme (PostgreSQL destekler)
        Timestamp ts = rs.getTimestamp("appointment_date");
        appointment.setAppointmentDate(ts.toLocalDateTime());

        // İlişkili Animal nesnesini oluşturma (JOIN'den gelen veri ile)
        Animal animal = new Animal();
        animal.setId(rs.getInt("animal_id"));
        animal.setName(rs.getString("animal_name"));
        appointment.setAnimal(animal);

        // İlişkili Vet nesnesini oluşturma (JOIN'den gelen veri ile)
        Vet vet = new Vet();
        vet.setId(rs.getInt("vet_id"));
        vet.setName(rs.getString("vet_name"));
        appointment.setVet(vet);

        return appointment;
    }

    // Değerlendirme formu 7: Yeni Randevu kaydı ekleme
    public boolean save(Appointment appointment) {
        String sql = "INSERT INTO Appointment (appointment_date, animal_id, vet_id) VALUES (?, ?, ?)";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // LocalDateTime'ı JDBC'nin anlayacağı TIMESTAMP formatına dönüştürme
            pstmt.setTimestamp(1, Timestamp.valueOf(appointment.getAppointmentDate()));
            pstmt.setInt(2, appointment.getAnimal().getId());
            pstmt.setInt(3, appointment.getVet().getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Değerlendirme formu 9: Tüm Randevuları ilişkili Animal ve Vet bilgileriyle listeleme
    public List<Appointment> findAll() {
        List<Appointment> appointmentList = new ArrayList<>();
        // İKİ TANE JOIN KULLANILARAK Üç Tabloyu Birleştirme
        String sql = "SELECT a.*, an.name AS animal_name, v.name AS vet_name " +
                "FROM Appointment a " +
                "JOIN Animal an ON a.animal_id = an.id " +
                "JOIN Vet v ON a.vet_id = v.id ORDER BY a.appointment_date";

        try (Connection conn = DBConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                appointmentList.add(createAppointmentFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentList;
    }


    public List<Appointment> findByVetAndDate(int vetId, LocalDateTime date, LocalDateTime end) {

        return new ArrayList<>();
    }
}