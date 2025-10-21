package service;

import dao.OwnerDAO;
import entity.Owner;
import java.util.List;

public class OwnerService {

    private final OwnerDAO ownerDAO;

    public OwnerService() {
        this.ownerDAO = new OwnerDAO();
    }

    // Değerlendirme formu 12: Kayıt eklemeden önce iş kuralı kontrolü yapılır.
    public boolean saveOwner(Owner owner) {
        if (owner.getName() == null || owner.getName().trim().isEmpty()) {
            System.err.println("Validation Error: Owner name cannot be empty.");
            return false;
        }
        if (owner.getMail() != null && !owner.getMail().contains("@")) {
            System.err.println("Validation Error: Invalid email format.");
            return false;
        }

        return ownerDAO.save(owner);
    }

    public Owner getOwner(int id) {
        return ownerDAO.findById(id);
    }

    public List<Owner> getAllOwners() {
        return ownerDAO.findAll();
    }

    // Owner ismine göre filtreleme
    public List<Owner> getOwnersByName(String name) {
        return ownerDAO.findByName(name);
    }

    public boolean updateOwner(Owner owner) {
        if (owner.getId() <= 0 || ownerDAO.findById(owner.getId()) == null) {
            System.err.println("Update Error: Owner with ID " + owner.getId() + " not found.");
            return false;
        }
        if (owner.getName() == null || owner.getName().trim().isEmpty()) {
            System.err.println("Validation Error: Owner name cannot be empty for update.");
            return false;
        }

        return ownerDAO.update(owner);
    }

    public boolean deleteOwner(int id) {
        if (id <= 0) {
            System.err.println("Delete Error: Invalid ID provided.");
            return false;
        }

        if (ownerDAO.findById(id) == null) {
            System.err.println("Delete Error: Owner with ID " + id + " not found.");
            return false;
        }

        return ownerDAO.delete(id);
    }
}