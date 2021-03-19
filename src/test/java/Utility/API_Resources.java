package Utility;

public enum API_Resources {
	Auth_SignUp("services/xxx"),
    Auth_ValidateOTP("services/xxx"),
    Auth_SendOTP("services/xxx"),
    Auth_Signin("services/xxx"),
    Auth_GetUserMenu("services/xxx");

    private String resource;

    API_Resources(String resource) {
        this.resource = resource;
    }

    public String getResources(){
        return resource;
    }
}
