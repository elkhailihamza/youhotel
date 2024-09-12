package Repositories;

import Models.Users;

public interface UserRepository {
    void addUser(Users user);
    Users getUserById(long id);
    Users getUserByUserName(String user_name);
}
