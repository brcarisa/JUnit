package edu.school21.models;

public class User {
    private Long identifier;
    private String login;
    private String password;
    private boolean authenticationStatus;

    public User(Long identifier, String login, String password, boolean authenticationStatus) {
        this.identifier = identifier;
        this.login = login;
        this.password = password;
        this.authenticationStatus = authenticationStatus;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAuthenticationStatus() {
        return authenticationStatus;
    }

    public void setAuthenticationStatus(boolean authenticationStatus) {
        this.authenticationStatus = authenticationStatus;
    }

}
