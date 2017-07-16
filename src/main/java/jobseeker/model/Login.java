package main.java.jobseeker.model;

/**
 * Created by caroline on 18/06/17.
 */
public class Login {

    private String login;
    private String pswd;

    public Login(String login, String pswd) {
        this.login = login;
        this.pswd = pswd;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    @Override
    public String toString() {
        return "Login{" +
                "login='" + login + '\'' +
                ", pswd='" + pswd + '\'' +
                '}';
    }
}
