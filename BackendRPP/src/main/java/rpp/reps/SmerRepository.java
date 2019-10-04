package rpp.reps;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rpp.jpa.Smer;

public interface SmerRepository extends JpaRepository<Smer, Integer>{
	Collection<Smer> findByNazivContainingIgnoreCase(String naziv);
	Collection<Smer> findByOznakaContainingIgnoreCase(String oznaka);
}
