package Services;

import Controllers.AuthController;
import Models.Users;
import Repositories.UserRepository;

public class AuthService {
    private static Users user;
    private final UserRepository UserRepository;
    public AuthService(UserRepository UserRepository) {
        this.UserRepository = UserRepository;
    }

    public boolean register(String user_name, String user_pass) {
        Users user = new Users(1, user_name, user_pass);
        if (user_name.length() > 3 && user_pass.length() > 3) {
            this.addUser(user);
            this.setCurrentUser(user);
            System.out.println("Added User successfully!");
            return true;
        }
        return false;
    }

    public boolean login(String user_name, String user_pass) {
        Users user = this.getUserByUserName(user_name);
        if (user != null && user.getUserPass().equals(user_pass)) {
            this.setCurrentUser(user);
            System.out.println("Login successful!");
            return true;
        }
        System.out.println("Wrong credentials!");
        return false;
    }

    private void setCurrentUser(Users user) {
        AuthService.user = user;
    }

    public static Users getCurrentUser() {
        return AuthService.user;
    }

    public static long getCurrentUserId() {
        Users user = AuthService.getCurrentUser();
        return user.getUserId();
    }

    private void addUser(Users user) {
        this.UserRepository.addUser(user);
    }

    public Users getUserById(long id) {
        return this.UserRepository.getUserById(id);
    }

    public Users getUserByUserName(String user_name) {
        return this.UserRepository.getUserByUserName(user_name);
    }
}
