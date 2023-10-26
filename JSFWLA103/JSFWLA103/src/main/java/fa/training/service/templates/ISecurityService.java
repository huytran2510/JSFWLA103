package fa.training.service.templates;

public interface ISecurityService {
    String findLoggedInUsername();
    void autoLogin(String username, String password);
}
