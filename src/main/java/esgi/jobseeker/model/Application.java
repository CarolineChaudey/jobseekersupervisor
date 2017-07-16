package esgi.jobseeker.model;

/**
 * Created by caroline on 13/07/17.
 */
public class Application {

    private String id;
    private String state;
    private String seekerId;
    private String letterId;
    private String resumeId;
    private AdForRow ad;

    public Application(String id, String state, String seekerId, String letterId, String resumeId, String adId, AdForRow ad) {
        this.id = id;
        this.state = state;
        this.seekerId = seekerId;
        this.letterId = letterId;
        this.resumeId = resumeId;
        this.ad = ad;
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

    public String getSeekerId() {
        return seekerId;
    }

    public void setSeekerId(String seekerId) {
        this.seekerId = seekerId;
    }

    public String getLetterId() {
        return letterId;
    }

    public void setLetterId(String letterId) {
        this.letterId = letterId;
    }

    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }

    public AdForRow getAd() {
        return ad;
    }

    public void setAd(AdForRow ad) {
        this.ad = ad;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id='" + id + '\'' +
                ", state='" + state + '\'' +
                ", seekerId='" + seekerId + '\'' +
                ", letterId='" + letterId + '\'' +
                ", resumeId='" + resumeId + '\'' +
                ", ad=" + ad.toString() +
                '}';
    }
}
