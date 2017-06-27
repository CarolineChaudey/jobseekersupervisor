package esgi.jobseeker.model;

/**
 * Created by caroline on 27/06/17.
 */
public class ContractType {
    private String name;

    public ContractType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ContractType{" +
                "name='" + name + '\'' +
                '}';
    }
}
