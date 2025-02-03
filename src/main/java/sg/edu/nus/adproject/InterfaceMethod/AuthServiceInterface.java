package sg.edu.nus.adproject.InterfaceMethod;

public interface AuthServiceInterface {
    boolean login(String username, String password);
    boolean register(String username, String password);
}
