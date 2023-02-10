package EcomerceAPI.Login;

public class LoginRequest {
    String userEmail;
    String UserPassword;



    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getSetUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        this.UserPassword = userPassword;
    }


}
