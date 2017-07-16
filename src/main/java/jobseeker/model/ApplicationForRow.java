package main.java.jobseeker.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by caroline on 16/07/17.
 */
public class ApplicationForRow {
    private String id;
    private String state;
    private Date createdAt;
    private String position;
    private String company;
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    public ApplicationForRow(String id, String state, Date createdAt, String position, String company) {
        this.id = id;
        this.state = state;
        this.createdAt = createdAt;
        this.position = position;
        this.company = company;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreatedAt() {
        return dateFormat.format(createdAt);
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "ApplicationForRow{" +
                "id='" + id + '\'' +
                ", state='" + state + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", position='" + position + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
