package spring.controller;

import spring.model.Spectacol;
import spring.repos.SpectacleRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spring.services.Service;

import java.util.ArrayList;
import java.util.Collection;


@Controller
@RequestMapping(path = "/rest")
public class RestControllerPPD {

    private Service service;

    public RestControllerPPD(){
        service = new Service();
    }
    @GetMapping("/hello")
    public String getHello() {
        return "Hello";
    }

    @GetMapping("/error")
    private ResponseEntity<?> errorReturn(String errorMessage) {
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/getAllSpectacles", method = RequestMethod.GET)
    public ResponseEntity<?> getAllSpectacles() {
        Collection<Spectacol> collection = new ArrayList<>();
        for (Spectacol c : service.getSpectacolRepository().findAll()) {
            collection.add(c);
        }
        Spectacol[] toReturn = new Spectacol[collection.size()];
        toReturn = collection.toArray(toReturn);
        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }
}
