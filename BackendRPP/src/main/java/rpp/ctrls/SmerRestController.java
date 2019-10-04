package rpp.ctrls;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rpp.jpa.Smer;
import rpp.reps.SmerRepository;

@CrossOrigin
@RestController
public class SmerRestController {

	@Autowired
	private SmerRepository smerRepository;

	@Autowired
	private JdbcTemplate JdbcTemplate;

	// vraca sve smerove u JSON formatu
	@GetMapping("smer")
	public Collection<Smer> getSmer() {
		return smerRepository.findAll();
	}

	// vraca sve smerove po id-u JSON formatu
	@GetMapping("smer/{id}")
	public Smer getSmer(@PathVariable("id") Integer id) {
		return smerRepository.getOne(id);
	}

	// vraca sve smerove po nazivu JSON formatu
	@GetMapping("smerNaziv/{naziv}")
	public Collection<Smer> getSmerByNaziv(@PathVariable("naziv") String naziv) {
		return smerRepository.findByNazivContainingIgnoreCase(naziv);
	}

	// vraca sve smerove po oznaci u JSON formatu
	@GetMapping("smerOznaka/{oznaka}")
	public Collection<Smer> getSmerByOznaka(@PathVariable("oznaka") String oznaka) {
		return smerRepository.findByOznakaContainingIgnoreCase(oznaka);
	}

	// brisanje projekata preko prosledjenog id-a
	@DeleteMapping("smer/{id}")
	public ResponseEntity<Smer> deleteSmer(@PathVariable("id") Integer id) {
		if (!smerRepository.existsById(id))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		smerRepository.deleteById(id);
		 if(id == -100)
			 JdbcTemplate.execute("INSERT INTO \"smer\"(\"id\",\"naziv\", \"oznaka\") VALUES (-100,'Animacija u inzenjerstvu','AI') ");
			return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// insert
	@PostMapping("smer")
	public ResponseEntity<Smer> insertSmer(@RequestBody Smer smer) {
		if (!smerRepository.existsById(smer.getId())) {
			smerRepository.save(smer);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	// update
	@PutMapping("smer")
	public ResponseEntity<Smer> updateSmer(@RequestBody Smer smer) {
		if (!smerRepository.existsById(smer.getId()))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		smerRepository.save(smer);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
