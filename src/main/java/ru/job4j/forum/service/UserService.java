package ru.job4j.forum.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;
    private final PasswordEncoder passwordEncoder;

    public UserService(InMemoryUserDetailsManager inMemoryUserDetailsManager,
                       PasswordEncoder passwordEncoder) {
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean saveNewUser(String username, String password) {
        boolean rsl = false;
        if (!inMemoryUserDetailsManager.userExists(username)) {
            inMemoryUserDetailsManager.createUser(User.withUsername(username)
                    .password(password).roles("USER").build());
            rsl = true;
        }
        return rsl;
    }
}
