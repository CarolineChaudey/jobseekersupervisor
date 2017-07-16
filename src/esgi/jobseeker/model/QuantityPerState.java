package esgi.jobseeker.model;

/**
 * Created by caroline on 06/07/17.
 */
public class QuantityPerState {
    private String state;
    private Integer nb;

    public QuantityPerState(String state, Integer nb) {
        this.state = state;
        this.nb = nb;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getNb() {
        return nb;
    }

    public void setNb(Integer nb) {
        this.nb = nb;
    }

    @Override
    public String toString() {
        return "QuantityPerState{" +
                "state='" + state + '\'' +
                ", nb=" + nb +
                '}';
    }
}
