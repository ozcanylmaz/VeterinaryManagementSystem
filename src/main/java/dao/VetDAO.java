package dao;

import core.DBConnector;
import entity.Vet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VetDAO {

    // Değerlendirme formu 7: Yeni Vet kaydı ekleme (Save)
    public boolean save(Vet vet) {
        String sql = "INSERT INTO Vet (name, phone, mail, address) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, vet.getName());
            pstmt.setString(2, vet.getPhone());
            pstmt.setString(3, vet.getMail());
            pstmt.setString(4, vet.getAddress());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Değerlendirme formu 9: Tüm Vet kayıtlarını listeleme (FindAll)
    public List<Vet> findAll() {
        List<Vet> vetList = new ArrayList<>();
        String sql = "SELECT * FROM Vet ORDER BY id";
        try (Connection conn = DBConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Vet vet = new Vet();
                vet.setId(rs.getInt("id"));
                vet.setName(rs.getString("name"));
                vet.setPhone(rs.getString("phone"));
                vet.setMail(rs.getString("mail"));
                vet.setAddress(rs.getString("address"));
                vetList.add(vet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vetList;
    }

    // Değerlendirme formu 8: ID'ye göre Vet bulma (FindByID)
    public Vet findById(int id) {
        Vet vet = null;
        String sql = "SELECT * FROM Vet WHERE id = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                vet = new Vet();
                vet.setId(rs.getInt("id"));
                vet.setName(rs.getString("name"));
                vet.setPhone(rs.getString("phone"));
                vet.setMail(rs.getString("mail"));
                vet.setAddress(rs.getString("address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vet;
    }

    //  TAMAMLANAN KOD: Vet kaydı güncelleme (Update)
    // Değerlendirme formu 10: Vet kaydı güncelleme (Update) metodu.
    public boolean update(Vet vet) {
        String sql = "UPDATE Vet SET name = ?, phone = ?, mail = ?, address = ? WHERE id = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, vet.getName());
            pstmt.setString(2, vet.getPhone());
            pstmt.setString(3, vet.getMail());
            pstmt.setString(4, vet.getAddress());
            pstmt.setInt(5, vet.getId()); // Hangi kaydı güncelleyeceğimizi belirtiriz.

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM Vet WHERE id = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}