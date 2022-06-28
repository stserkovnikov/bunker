package models;

public class AuthModel {
    private String login;
    private String password;
    @SuppressWarnings("unused")
    public String getLogin() {
        return login;
    }

    public AuthModel setLogin(String login) {
        this.login = login;
        return this;
    }

    @SuppressWarnings("unused")
    public String getPassword() {
        return password;
    }

    public AuthModel setPassword(String password) {
        this.password = password;
        return this;
    }


}
