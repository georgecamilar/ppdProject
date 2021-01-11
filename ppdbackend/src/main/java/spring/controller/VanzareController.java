package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.model.Spectacol;
import spring.model.Vanzare;
import spring.repos.VanzareRepository;
import spring.repos.SpectacolRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(path = "rest/vanzari")
public class VanzareController {
    @Autowired
    private VanzareRepository vanzareRepository;

    @Autowired
    private SpectacolRepository spectacolRepository;

    @GetMapping("/error")
    private ResponseEntity<?> errorReturn(String errorMessage) {
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        Collection<Vanzare> collection = new ArrayList<>();
        for (Vanzare c : vanzareRepository.findAll()) {
            collection.add(c);
        }
        Vanzare[] toReturn = new Vanzare[collection.size()];
        toReturn = collection.toArray(toReturn);
        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @RequestMapping(value = "/{vanzareId}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable int vanzareId) {
        Optional<Vanzare> vanzare = vanzareRepository.findById(vanzareId);

        return new ResponseEntity<>(vanzare, HttpStatus.OK);
    }
}
