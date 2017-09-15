package com.dihri.web.chat.service;

import com.dihri.web.chat.model.User;
import com.dihri.web.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShaPasswordEncoder shaPasswordEncoder;

    @Override
    public User getUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    @Override
    public User getAuthorizeUser() {
        org.springframework.security.core.userdetails.User user =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userRepository.findUserByLogin(user.getUsername());
    }

    @Override
    public void createUser(String login, String password) {
        User user = new User(login, shaPasswordEncoder.encodePassword(password,null));
        userRepository.save(user);
    }

    @Override
    public boolean isExistsLogin(String login) {
        User user = getUserByLogin(login);
        return user!=null;
    }
}
