//UserService
package com.wad.firstmvc.services;

import com.wad.firstmvc.domain.User;
import java.util.List;

public interface UserService {
    void addUser(User user);
    List<User> getAllUsers();
}
