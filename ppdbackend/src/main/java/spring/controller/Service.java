package spring.controller;
import  spring.repos.SalaRepository;
import  spring.repos.SpectacolRepository;
import  spring.repos.VanzareRepository;

public class Service {
    private SpectacolRepository spectacolRepository;
    private VanzareRepository vanzareRepository;
    private SalaRepository salaRepository;

    public SalaRepository getSalaRepository() {
        return salaRepository;
    }
    public void setSalaRepository(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

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
}
