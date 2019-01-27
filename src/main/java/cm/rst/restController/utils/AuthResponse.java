package cm.rst.restController.utils;

import org.springframework.http.HttpStatus;

public class AuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private int status= HttpStatus.OK.value();

    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
