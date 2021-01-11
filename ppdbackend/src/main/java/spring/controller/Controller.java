package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import spring.model.Spectacol;
import spring.repos.SpectacolRepository;

import java.util.ArrayList;
import java.util.Collection;


@RestController
@RequestMapping(path = "/rest")
public class Controller {

    @Autowired
    private SpectacolRepository spectacleRepository;


    @GetMapping("/hello")
    public String getHello() {
        return "Hello";
    }

    @GetMapping("/error")
    private ResponseEntity<?> errorReturn(String errorMessage) {
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

}
