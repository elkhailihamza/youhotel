package Repositories;

import Models.Users;

public interface UserRepository {
    void addUser(String user_name, String user_pass);
    Users getUserById(long id);
    Users getUserByUserName(String user_name);
}
