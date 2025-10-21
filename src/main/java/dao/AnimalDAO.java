package dao;

import core.DBConnector;
import entity.Animal;
import entity.Owner;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {

    // Yardımcı metot: ResultSet'ten Animal ve ilişkili Owner nesnesini oluşturur.
    private Animal createAnimalFromResultSet(ResultSet rs) throws SQLException {
        Animal animal = new Animal();
        animal.setId(rs.getInt("id"));
        animal.setName(rs.getString("name"));
        animal.setSpecies(rs.getString("species"));
        animal.setBreed(rs.getString("breed"));

        // İlişkili Owner nesnesini oluşturma
        Owner owner = new Owner();
        owner.setId(rs.getInt("owner_id"));
        owner.setName(rs.getString("owner_name"));

        animal.setOwner(owner);
        return animal;
    }

    // Değerlendirme formu 7: Yeni Animal kaydı ekleme
    public boolean save(Animal animal) {
        String sql = "INSERT INTO Animal (name, species, breed, owner_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, animal.getName());
            pstmt.setString(2, animal.getSpecies());
            pstmt.setString(3, animal.getBreed());
            pstmt.setInt(4, animal.getOwner().getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Değerlendirme formu 9: Tüm hayvanları ilişkili Owner bilgileriyle listeleme
    public List<Animal> findAll() {
        List<Animal> animalList = new ArrayList<>();
        String sql = "SELECT a.*, o.name AS owner_name FROM Animal a JOIN Owner o ON a.owner_id = o.id ORDER BY a.id";

        try (Connection conn = DBConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                animalList.add(createAnimalFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animalList;
    }

    // Değerlendirme formu 8: ID'ye göre hayvan bulma (ilişkili Owner bilgisiyle)
    public Animal findById(int id) {
        Animal animal = null;
        String sql = "SELECT a.*, o.name AS owner_name FROM Animal a JOIN Owner o ON a.owner_id = o.id WHERE a.id = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                animal = createAnimalFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animal;
    }

    // Değerlendirme formu 14: Belirli bir sahibin tüm hayvanlarını listeleme
    public List<Animal> findByOwnerId(int ownerId) {
        List<Animal> animalList = new ArrayList<>();
        String sql = "SELECT a.*, o.name AS owner_name FROM Animal a JOIN Owner o ON a.owner_id = o.id WHERE a.owner_id = ?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, ownerId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                animalList.add(createAnimalFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animalList;
    }

    // Değerlendirme formu 10: Animal kaydı güncelleme (Update)
    public boolean update(Animal animal) {
        String sql = "UPDATE Animal SET name = ?, species = ?, breed = ?, owner_id = ? WHERE id = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, animal.getName());
            pstmt.setString(2, animal.getSpecies());
            pstmt.setString(3, animal.getBreed());
            pstmt.setInt(4, animal.getOwner().getId());
            pstmt.setInt(5, animal.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Değerlendirme formu 11: Animal kaydı silme (Delete)
    public boolean delete(int id) {
        String sql = "DELETE FROM Animal WHERE id = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Animal> findByName(String name) {
        List<Animal> animalList = new ArrayList<>();
        String sql = "SELECT a.*, o.name AS owner_name " +
                "FROM Animal a JOIN Owner o ON a.owner_id = o.id " +
                "WHERE a.name ILIKE ? ORDER BY a.id";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                animalList.add(createAnimalFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animalList;
    }
}