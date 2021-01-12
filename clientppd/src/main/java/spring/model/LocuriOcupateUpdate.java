package spring.model;

import java.io.Serializable;
import java.util.List;

public class LocuriOcupateUpdate implements Serializable {
    public List<Integer> locuriOcupate;

    public LocuriOcupateUpdate(List<Integer> locuriOcupate) {
        this.locuriOcupate = locuriOcupate;
    }
}
