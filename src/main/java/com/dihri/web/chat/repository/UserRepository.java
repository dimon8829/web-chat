package com.dihri.web.chat.repository;

import com.dihri.web.chat.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for users
 */
public interface UserRepository extends CrudRepository<User,Integer> {
    /**
     * Get user by login
     * @param login login
     * @return user
     */
    User findUserByLogin(String login);
}
