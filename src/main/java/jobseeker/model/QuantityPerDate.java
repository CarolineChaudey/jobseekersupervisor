package main.java.jobseeker.model;

import java.util.Date;

/**
 * Created by caroline on 06/07/17.
 */
public class QuantityPerDate {
    private Date day;
    private Integer nb;

    public QuantityPerDate(Date day, Integer nb) {
        this.day = day;
        this.nb = nb;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Integer getNb() {
        return nb;
    }

    public void setNb(Integer nb) {
        this.nb = nb;
    }

    @Override
    public String toString() {
        return "QuantityPerDate{" +
                "day=" + day +
                ", nb=" + nb +
                '}';
    }
}
