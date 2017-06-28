package esgi.jobseeker.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by caroline on 28/06/17.
 */
public class AdToSend {
    private String position;
    private String description;
    private String contactEmail;
    private String urlApplication;
    private Date publicationDate;
    private String company;
    private Integer jobDuration;
    private List<String> tags;
    private List<String> contractTypes;
    private String website;

    public AdToSend(Ad ad) {
        this.position = ad.getPosition();
        this.description = ad.getDescription();
        this.contactEmail = ad.getEmail();
        this.urlApplication = ad.getUrl();
        this.company = ad.getCompany();
        this.tags = ad.getTags();
        this.jobDuration = ad.getJobDuration();
        if (null != ad.getWebsite()) {
            this.website = ad.getWebsite().getName();
        }
        this.publicationDate = ad.getPublicationDate();
        this.contractTypes = parseContractTypes(ad.getContractTypes());
    }

    private List<String> parseContractTypes(List<ContractType> contractTypes) {
        List<String> contractNames = new ArrayList<>();
        for (ContractType ct : contractTypes) {
            contractNames.add(ct.getName());
        }
        return contractNames;
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

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getUrlApplication() {
        return urlApplication;
    }

    public void setUrlApplication(String urlApplication) {
        this.urlApplication = urlApplication;
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

    public List<String> getContractTypes() {
        return contractTypes;
    }

    public Integer getJobDuration() {
        return jobDuration;
    }

    public void setJobDuration(int jobDuration) {
        this.jobDuration = jobDuration;
    }

    public void setContractTypes(List<String> contractTypes) {
        this.contractTypes = contractTypes;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Override
    public String toString() {
        return "AdToSend{" +
                "position='" + position + '\'' +
                ", description='" + description + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", urlApplication='" + urlApplication + '\'' +
                ", publicationDate=" + publicationDate +
                ", company='" + company + '\'' +
                ", jobDuration=" + jobDuration +
                ", tags=" + tags +
                ", contractTypes=" + contractTypes +
                ", website='" + website + '\'' +
                '}';
    }
}
