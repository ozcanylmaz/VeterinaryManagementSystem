package dao;

import core.DBConnector;
import entity.Vaccine;
import entity.Animal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VaccineDAO {

    // Yardımcı metot: ResultSet'ten Vaccine ve ilişkili Animal nesnesini oluşturur.
    private Vaccine createVaccineFromResultSet(ResultSet rs) throws SQLException {
        Vaccine vaccine = new Vaccine();
        vaccine.setId(rs.getInt("id"));
        vaccine.setName(rs.getString("name"));
        vaccine.setCode(rs.getString("code"));

        // Tarih alanları
        vaccine.setProtectionStartDate(rs.getDate("protection_start_date").toLocalDate());
        vaccine.setProtectionEndDate(rs.getDate("protection_end_date").toLocalDate());

        // İlişkili Animal nesnesini oluşturma
        Animal animal = new Animal();
        animal.setId(rs.getInt("animal_id"));
        animal.setName(rs.getString("animal_name")); // JOIN'den gelen isim
        vaccine.setAnimal(animal);

        return vaccine;
    }

    // Değerlendirme formu 7: Yeni Aşı kaydı ekleme (Create)
    public boolean save(Vaccine vaccine) {
        String sql = "INSERT INTO Vaccine (name, code, protection_start_date, protection_end_date, animal_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, vaccine.getName());
            pstmt.setString(2, vaccine.getCode());
            pstmt.setDate(3, Date.valueOf(vaccine.getProtectionStartDate()));
            pstmt.setDate(4, Date.valueOf(vaccine.getProtectionEndDate()));
            pstmt.setInt(5, vaccine.getAnimal().getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Değerlendirme formu 9: Tüm aşı kayıtlarını ilişkili Animal bilgileriyle listeleme (Read All)
    public List<Vaccine> findAll() {
        List<Vaccine> vaccineList = new ArrayList<>();
        String sql = "SELECT v.*, an.name AS animal_name " +
                "FROM Vaccine v JOIN Animal an ON v.animal_id = an.id ORDER BY v.id";

        try (Connection conn = DBConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                vaccineList.add(createVaccineFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vaccineList;
    }

    //  TAMAMLANAN KOD: ID'ye göre tek aşı kaydı bulma (Read By ID)
    // Değerlendirme formu 8: ID'ye göre aşı kaydı bulma (FindByID) metodu.
    public Vaccine findById(int id) {
        Vaccine vaccine = null;
        // Basitçe ID'ye göre bulur, JOIN kullanmadık çünkü çoğunlukla sadece ID'yi doğrulamak için kullanılır.
        String sql = "SELECT * FROM Vaccine WHERE id = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Burada createVaccineFromResultSet helper'ını kullanamayız çünkü JOIN yok.
                vaccine = new Vaccine();
                vaccine.setId(rs.getInt("id"));
                vaccine.setName(rs.getString("name"));
                vaccine.setCode(rs.getString("code"));
                vaccine.setProtectionStartDate(rs.getDate("protection_start_date").toLocalDate());
                vaccine.setProtectionEndDate(rs.getDate("protection_end_date").toLocalDate());

                // Animal objesi de burada oluşturulmalı (sadece ID ile)
                Animal animal = new Animal();
                animal.setId(rs.getInt("animal_id"));
                vaccine.setAnimal(animal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vaccine;
    }

    //  TAMAMLANAN KOD: Kayıt Güncelleme (Update)
    // Değerlendirme formu 10: Aşı kaydı güncelleme (Update) metodu.
    public boolean update(Vaccine vaccine) {
        String sql = "UPDATE Vaccine SET name = ?, code = ?, protection_start_date = ?, protection_end_date = ?, animal_id = ? WHERE id = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, vaccine.getName());
            pstmt.setString(2, vaccine.getCode());
            pstmt.setDate(3, Date.valueOf(vaccine.getProtectionStartDate()));
            pstmt.setDate(4, Date.valueOf(vaccine.getProtectionEndDate()));
            pstmt.setInt(5, vaccine.getAnimal().getId());
            pstmt.setInt(6, vaccine.getId()); // Hangi kaydı güncelleyeceğimizi belirtiriz.

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM Vaccine WHERE id = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Değerlendirme formu 20: Belirli bir hayvanın aşı geçmişini listeleme
    public List<Vaccine> findByAnimalId(int animalId) {
        List<Vaccine> vaccineList = new ArrayList<>();
        String sql = "SELECT v.*, an.name AS animal_name FROM Vaccine v JOIN Animal an ON v.animal_id = an.id WHERE v.animal_id = ?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, animalId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                vaccineList.add(createVaccineFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vaccineList;
    }

    public List<Vaccine> findByProtectionEndDateRange(LocalDate start, LocalDate end) {
        return null;
    }
}