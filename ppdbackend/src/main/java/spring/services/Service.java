package spring.services;

import spring.repos.SpectacleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import spring.repos.VanzareLocRepository;
import spring.repos.VanzareRepository;
@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private SpectacleRepository spectacolRepository;

    @Autowired
    private VanzareRepository vanzareRepository;

    @Autowired
    private VanzareLocRepository vanzareLocRepository;

    public Service(){
        
    }

    /** Getters and setters for utilising the functions*/

    public SpectacleRepository getSpectacolRepository() {
        return spectacolRepository;
    }

    public VanzareRepository getVanzareRepository() {
        return vanzareRepository;
    }

    public VanzareLocRepository getVanzareLocRepository() {
        return vanzareLocRepository;
    }
}
