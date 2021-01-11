package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.model.Spectacol;
import spring.repos.SpectacolRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(path = "rest/spectacole")
public class SpectacolController {

    @Autowired
    private SpectacolRepository spectacleRepository;

    @GetMapping("/error")
    private ResponseEntity<?> errorReturn(String errorMessage) {
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        Collection<Spectacol> collection = new ArrayList<>();
        for (Spectacol c : spectacleRepository.findAll()) {
            collection.add(c);
        }
        Spectacol[] toReturn = new Spectacol[collection.size()];
        toReturn = collection.toArray(toReturn);
        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @RequestMapping(value = "/{spectacolId}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable int spectacolId) {
        Optional<Spectacol> spectacol = spectacleRepository.findById(spectacolId);

        return new ResponseEntity<>(spectacol, HttpStatus.OK);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Spectacol s) {
        Spectacol spectacol = spectacleRepository.save(s);

        return new ResponseEntity<>(spectacol, HttpStatus.OK);
    }
}
