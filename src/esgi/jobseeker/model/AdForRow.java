package esgi.jobseeker.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by caroline on 30/06/17.
 */
public class AdForRow {
    private String id;
    private String position;
    private String email;
    private String jobDuration;
    private String publicationDate;
    private String company;
    private String nbApplications;

    public AdForRow(Ad ad) {
        super();

        this.id = ad.getId();

        this.position = ad.getPosition();

        if (null == ad.getEmail()) {
            this.email = "-";
        } else {
            this.email = ad.getEmail();
        }

        if (null == ad.getJobDuration()) {
            this.jobDuration = "indéterminée";
        } else {
            this.jobDuration = ad.getJobDuration().toString();
        }

        this.publicationDate = formatDate(ad.getPublicationDate());

        if (null == ad.getCompany()) {
            this.company = "particulier";
        } else {
            this.company = ad.getCompany();
        }

        if (null == ad.getNbApplications()) {
            this.nbApplications = "null";
        } else {
            this.nbApplications = ad.getNbApplications() + "";
        }
    }

    private String formatDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobDuration() {
        return jobDuration;
    }

    public void setJobDuration(String jobDuration) {
        this.jobDuration = jobDuration;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getNbApplications() {
        return nbApplications;
    }

    public void setNbApplications(String nbApplications) {
        this.nbApplications = nbApplications;
    }

    @Override
    public String toString() {
        return "AdForRow{" +
                "id='" + id + '\'' +
                ", position='" + position + '\'' +
                ", email='" + email + '\'' +
                ", jobDuration='" + jobDuration + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                ", company='" + company + '\'' +
                ", nbApplications='" + nbApplications + '\'' +
                '}';
    }
}
