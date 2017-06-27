package esgi.jobseeker.model;

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
    private String organization;
    private List<String> tags;
    private List<ContractType> proposedContracts;
    private Website website;
    private Supervisor author;

    public Ad(String id, String position, String description, String email, String url, String organization,
              List<String> tags, List<ContractType> proposedContracts, Website website, Supervisor author) {
        this.id = id;
        this.position = position;
        this.description = description;
        this.email = email;
        this.url = url;
        this.organization = organization;
        this.tags = tags;
        this.proposedContracts = proposedContracts;
        this.website = website;
        this.author = author;
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

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<ContractType> getProposedContracts() {
        return proposedContracts;
    }

    public void setProposedContracts(List<ContractType> proposedContracts) {
        this.proposedContracts = proposedContracts;
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

    @Override
    public String toString() {
        return "Ad{" +
                "id='" + id + '\'' +
                ", position='" + position + '\'' +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", url='" + url + '\'' +
                ", organization='" + organization + '\'' +
                ", tags=" + tags +
                ", proposedContracts=" + proposedContracts +
                ", website=" + website +
                ", author=" + author +
                '}';
    }
}
