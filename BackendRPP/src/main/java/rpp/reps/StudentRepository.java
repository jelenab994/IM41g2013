package rpp.reps;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rpp.jpa.Grupa;
import rpp.jpa.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	Collection<Student> findByGrupa(Grupa g);

	Collection<Student> findByBrojIndeksaContainingIgnoreCase(String brojIndeksa);

	Collection<Student> findByImeContainingIgnoreCase(String ime);

	Collection<Student> findByPrezimeContainingIgnoreCase(String prezime);
}
