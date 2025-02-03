package sg.edu.nus.adproject.Model;

public class AdminAuthResponse {
    private boolean success;
    private String redirectUrl;
    private String message;

    public AdminAuthResponse(boolean success, String redirectUrl, String message) {
        this.success = success;
        this.redirectUrl = redirectUrl;
        this.message = message;
    }

    public boolean isSuccess() { return success; }
    public String getRedirectUrl() { return redirectUrl; }
    public String getMessage() { return message; }
}
