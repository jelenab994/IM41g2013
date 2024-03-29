package rpp.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rpp.jpa.Grupa;
import rpp.jpa.Student;
import rpp.reps.GrupaRepository;
import rpp.reps.StudentRepository;

@CrossOrigin
@RestController
public class StudentRestController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private GrupaRepository grupaRepository;
	
	//vraca sve studente u JSON formatu
	@GetMapping("student")
	public Collection<Student> getStudent(){
		return studentRepository.findAll();
	}
	
	//vraca sve studente po id-u u JSON formatu
	@GetMapping("student/{id}")
	public Student getStudent(@PathVariable("id") Integer id) {
		return studentRepository.getOne(id);
	}
	
	//nova metoda za prikaz studenata po grupama
	@GetMapping("studentZaGrupuId/{id}")
	public Collection<Student> studentID (@PathVariable("id") Integer id){
		Grupa g = grupaRepository.getOne(id);
		return studentRepository.findByGrupa(g);
	}
	
	//vraca sve studente po broju indeksa u JSON formatu
	@GetMapping("studentBrojIndeksa/{brojIndeksa}")
	public Collection<Student> getStudentByIndeks(@PathVariable("brojIndeksa") String brojIndeksa){
		return studentRepository.findByBrojIndeksaContainingIgnoreCase(brojIndeksa);
	}
	
	//vraca sve studente po imenu u JSON formatu
	@GetMapping("studentIme/{ime}")
	public Collection<Student> getStudentByIme(@PathVariable("ime") String ime){
		return studentRepository.findByImeContainingIgnoreCase(ime);
	}
	
	//vraca sve studente po prezimenu u JSON formatu
	@GetMapping("studentPrezime/{prezime}")
	public Collection<Student> getStudenByPrezime(@PathVariable("prezime") String prezime){
		return studentRepository.findByPrezimeContainingIgnoreCase(prezime);
	}
	
	//brisanje studenata preko prosledjenog id-a
	@DeleteMapping("student/{id}")
	public ResponseEntity<Student> deleteStudent(@PathVariable("id") Integer id) {
		if (!studentRepository.existsById(id))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		studentRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//insert
	@PostMapping("student")
	public ResponseEntity<Student> insertStudent(@RequestBody Student student) {
		if (!studentRepository.existsById(student.getId())) {
			studentRepository.save(student);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	//update
	@PutMapping("student")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
		if (!studentRepository.existsById(student.getId()))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		studentRepository.save(student);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
