package Services;

import Models.Users;
import Repositories.UserRepository;

public class AuthService {
    private final UserRepository UserRepository;
    public AuthService(UserRepository UserRepository) {
        this.UserRepository = UserRepository;
    }

    public void addUser(String user_name, String user_pass) {
        this.UserRepository.addUser(user_name, user_pass);
    }

    public Users getUserById(long id) {
        return this.UserRepository.getUserById(id);
    }

    public Users getUserByUserName(String user_name) {
        return this.UserRepository.getUserByUserName(user_name);
    }
}
