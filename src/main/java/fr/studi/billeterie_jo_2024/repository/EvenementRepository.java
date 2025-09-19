package fr.studi.billeterie_jo_2024.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.studi.billeterie_jo_2024.pojo.Evenement;

public interface EvenementRepository extends JpaRepository<Evenement, Long> {

	List<Evenement> findBySport(String sport);

	@Query("SELECT DISTINCT e.sport FROM Evenement e")
	List<String> findAllSports();
}
