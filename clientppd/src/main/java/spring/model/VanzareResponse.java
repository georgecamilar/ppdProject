package spring.model;
import java.io.Serializable;
import java.util.UUID;

public class VanzareResponse implements Serializable {
    public UUID vanzare_id;
    public Boolean success;

    public VanzareResponse(UUID vanzare_id, Boolean success) {
        this.vanzare_id = vanzare_id;
        this.success = success;
    }
}