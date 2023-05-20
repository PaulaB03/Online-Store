package service;

import model.User;

public interface UserService {
    void register();
    void addUser(User user);
    User getUserByEmail(String email);
    void printUsers();
}