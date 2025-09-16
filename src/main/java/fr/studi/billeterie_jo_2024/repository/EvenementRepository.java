package fr.studi.billeterie_jo_2024.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.studi.billeterie_jo_2024.pojo.Evenement;

public interface EvenementRepository extends JpaRepository<Evenement, Long> {

}
