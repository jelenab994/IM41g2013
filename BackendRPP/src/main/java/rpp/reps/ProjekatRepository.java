package rpp.reps;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rpp.jpa.Projekat;

public interface ProjekatRepository extends JpaRepository<Projekat, Integer> {
	Collection<Projekat> findByNazivContainingIgnoreCase(String naziv);

	Collection<Projekat> findByOznakaContainingIgnoreCase(String oznaka);
}
