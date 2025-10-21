package dao;

import core.DBConnector;
import entity.Owner;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OwnerDAO {

    // Yardımcı metot: ResultSet'ten Owner nesnesini oluşturur.
    private Owner createOwnerFromResultSet(ResultSet rs) throws SQLException {
        Owner owner = new Owner();
        owner.setId(rs.getInt("id"));
        owner.setName(rs.getString("name"));
        owner.setPhone(rs.getString("phone"));
        owner.setMail(rs.getString("mail"));
        owner.setAddress(rs.getString("address"));
        owner.setCity(rs.getString("city"));
        return owner;
    }

    // Değerlendirme formu 7: Yeni kayıt ekleme (Save)
    public boolean save(Owner owner) {
        String sql = "INSERT INTO Owner (name, phone, mail, address, city) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, owner.getName());
            pstmt.setString(2, owner.getPhone());
            pstmt.setString(3, owner.getMail());
            pstmt.setString(4, owner.getAddress());
            pstmt.setString(5, owner.getCity());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Değerlendirme formu 8: ID'ye göre kayıt bulma (FindByID)
    public Owner findById(int id) {
        Owner owner = null;
        String sql = "SELECT * FROM Owner WHERE id = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                owner = createOwnerFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return owner;
    }

    // Değerlendirme formu 9: Tüm kayıtları listeleme (FindAll)
    public List<Owner> findAll() {
        List<Owner> ownerList = new ArrayList<>();
        String sql = "SELECT * FROM Owner ORDER BY id";
        try (Connection conn = DBConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ownerList.add(createOwnerFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ownerList;
    }

    // Değerlendirme formu 10: Kayıt güncelleme (Update)
    public boolean update(Owner owner) {
        String sql = "UPDATE Owner SET name = ?, phone = ?, mail = ?, address = ?, city = ? WHERE id = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, owner.getName());
            pstmt.setString(2, owner.getPhone());
            pstmt.setString(3, owner.getMail());
            pstmt.setString(4, owner.getAddress());
            pstmt.setString(5, owner.getCity());
            pstmt.setInt(6, owner.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Değerlendirme formu 11: Kayıt silme (Delete)
    public boolean delete(int id) {
        String sql = "DELETE FROM Owner WHERE id = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Owner> findByName(String name) {
        List<Owner> ownerList = new ArrayList<>();
        String sql = "SELECT * FROM Owner WHERE name ILIKE ? ORDER BY id";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ownerList.add(createOwnerFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ownerList;
    }
}