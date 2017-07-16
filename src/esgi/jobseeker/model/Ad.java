package esgi.jobseeker.model;

import java.util.Date;
import java.util.List;

/**
 * Created by caroline on 27/06/17.
 */
public class Ad {
    private String id;
    private String position;
    private String description;
    private String email;
    private String url;
    private Integer jobDuration;
    private Date publicationDate;
    private String company;
    private List<String> tags;
    private List<ContractType> contractTypes;
    private Website website;
    private Supervisor author;
    private Integer nbApplications;

    public Ad(String id, String position, String description, String email, String url, String company, Integer jobDuration,
              List<String> tags, List<ContractType> proposedContracts, Website website, Supervisor author, Integer nbApplications) {
        this.id = id;
        this.position = position;
        this.description = description;
        this.email = email;
        this.url = url;
        this.company = company;
        this.jobDuration = jobDuration;
        this.tags = tags;
        this.contractTypes = proposedContracts;
        this.website = website;
        this.author = author;
        this.publicationDate = new Date();
        this.nbApplications = nbApplications;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getJobDuration() {
        return jobDuration;
    }

    public void setJobDuration(int jobDuration) {
        this.jobDuration = jobDuration;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<ContractType> getContractTypes() {
        return contractTypes;
    }

    public void setContractTypes(List<ContractType> contractTypes) {
        this.contractTypes = contractTypes;
    }

    public Website getWebsite() {
        return website;
    }

    public void setWebsite(Website website) {
        this.website = website;
    }

    public Supervisor getAuthor() {
        return author;
    }

    public void setAuthor(Supervisor author) {
        this.author = author;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setJobDuration(Integer jobDuration) {
        this.jobDuration = jobDuration;
    }

    public Integer getNbApplications() {
        return nbApplications;
    }

    public void setNbApplications(Integer nbApplications) {
        this.nbApplications = nbApplications;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "id='" + id + '\'' +
                ", position='" + position + '\'' +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", url='" + url + '\'' +
                ", jobDuration=" + jobDuration +
                ", publicationDate=" + publicationDate +
                ", company='" + company + '\'' +
                ", tags=" + tags +
                ", contractTypes=" + contractTypes +
                ", website=" + website +
                ", author=" + author +
                ", nbApplications=" + nbApplications +
                '}';
    }
}
