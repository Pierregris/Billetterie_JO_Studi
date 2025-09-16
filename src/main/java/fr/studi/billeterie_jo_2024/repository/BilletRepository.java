package fr.studi.billeterie_jo_2024.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.studi.billeterie_jo_2024.pojo.Billet;

public interface BilletRepository extends JpaRepository<Billet, Long> {

}
