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

import rpp.jpa.Projekat;
import rpp.reps.ProjekatRepository;

@CrossOrigin
@RestController
public class ProjekatRestController {

	@Autowired
	private ProjekatRepository projekatRepository;

	// vraca sve projekte u JSON formatu
	@GetMapping("projekat")
	public Collection<Projekat> getProjekat() {
		return projekatRepository.findAll();
	}

	// vraca sve projekte po id-u JSON formatu
	@GetMapping("projekat/{id}")
	private Projekat getProjekat(@PathVariable("id") Integer id) {
		return projekatRepository.getOne(id);
	}

	// vraca sve projekte po nazivu u JSON formatu
	@GetMapping("projekatNaziv/{naziv}")
	public Collection<Projekat> getProjekatByNaziv(@PathVariable("naziv") String naziv) {
		return projekatRepository.findByNazivContainingIgnoreCase(naziv);
	}

	// vraca sve projekte po oznaci u JSON formatu
	@GetMapping("projekatOznaka/{oznaka}")
	public Collection<Projekat> getProjekatByOznaka(@PathVariable("oznaka") String oznaka) {
		return projekatRepository.findByOznakaContainingIgnoreCase(oznaka);
	}

	// brisanje projekata preko prosledjenog id-a
	@DeleteMapping("projekat/{id}")
	public ResponseEntity<Projekat> deleteProjekat(@PathVariable("id") Integer id) {
		if (!projekatRepository.existsById(id))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		projekatRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// insert
	@PostMapping("projekat")
	public ResponseEntity<Projekat> insertProjekat(@RequestBody Projekat projekat) {
		if (!projekatRepository.existsById(projekat.getId())) {
			projekatRepository.save(projekat);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}

	// update
	@PutMapping("projekat")
	public ResponseEntity<Projekat> updateProjekat(@RequestBody Projekat projekat) {
		if (!projekatRepository.existsById(projekat.getId()))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		projekatRepository.save(projekat);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}