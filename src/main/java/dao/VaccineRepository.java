package dao;

import entity.Animal;
import entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine,Integer> {
    List<Vaccine> findAllByAnimal(Animal animal);
    List<Vaccine> findAllByProtectionEndDateBetween(LocalDate min, LocalDate max);
}
