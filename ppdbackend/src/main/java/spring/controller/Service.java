package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import spring.repos.SpectacolRepository;
import spring.repos.VanzareLocRepository;
import spring.repos.VanzareRepository;

public class Service {

    @Autowired
    private SpectacolRepository spectacolRepository;
    @Autowired
    private VanzareRepository vanzareRepository;
    @Autowired
    private VanzareLocRepository vanzareLocRepository;

    public SpectacolRepository getSpectacolRepository() {
        return spectacolRepository;
    }

    public void setSpectacolRepository(SpectacolRepository spectacolRepository) {
        this.spectacolRepository = spectacolRepository;
    }

    public VanzareRepository getVanzareRepository() {
        return vanzareRepository;
    }

    public void setVanzareRepository(VanzareRepository vanzareRepository) {
        this.vanzareRepository = vanzareRepository;
    }

    public VanzareLocRepository getVanzareLocRepository() {
        return vanzareLocRepository;
    }

    public void setVanzareLocRepository(VanzareLocRepository vanzareLocRepository) {
        this.vanzareLocRepository = vanzareLocRepository;
    }
}
