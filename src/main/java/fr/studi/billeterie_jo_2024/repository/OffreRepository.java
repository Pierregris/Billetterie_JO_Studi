package fr.studi.billeterie_jo_2024.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.studi.billeterie_jo_2024.pojo.Offre;

@Repository
public interface OffreRepository extends JpaRepository<Offre, String> {

	public List<Offre> findByActive(Boolean active);

}
