package models;

public class AuthModel {
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public AuthModel(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public AuthModel() {
    }

    public AuthModel setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AuthModel setPassword(String password) {
        this.password = password;
        return this;
    }


}
