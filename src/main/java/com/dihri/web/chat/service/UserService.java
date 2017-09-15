package com.dihri.web.chat.service;

import com.dihri.web.chat.model.User;

/**
 * Service for working with user
 */
public interface UserService {
    /**
     * Get user by login
     * @param login login
     * @return user
     */
    User getUserByLogin(String login);

    /**
     * Get authorization user
     * @return authorization user
     */
    User getAuthorizeUser();

    /**
     * Create new user
     * @param login login user
     * @param password password user
     */
    void createUser(String login,String password);

    /**
     * is exists login user
     * @param login login
     * @return true - if login exists
     */
    boolean isExistsLogin(String login);
}
