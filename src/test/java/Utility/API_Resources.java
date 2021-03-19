package Utility;

public enum API_Resources {
	Auth_SignUp("services/users/auth/signup"),
    Auth_ValidateOTP("services/users/auth/validateOTP"),
    Auth_SendOTP("services/users/auth/sendOTP"),
    Auth_Signin("services/users/auth/signin"),
    Auth_GetUserMenu("services/users/auth/getUserMenu");

    private String resource;

    API_Resources(String resource) {
        this.resource = resource;
    }

    public String getResources(){
        return resource;
    }
}
