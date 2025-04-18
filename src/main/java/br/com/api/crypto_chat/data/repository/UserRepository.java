package br.com.api.crypto_chat.data.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.crypto_chat.data.entity.User;
import br.com.api.crypto_chat.data.enums.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     * Find a user by their email
     * 
     * @param email The user's email
     * @return Optional containing the user if found
     */
    Optional<User> findByEmail(String email);

    /**
     * Find active users by their role
     * 
     * @param role The user role to filter by
     * @return List of active users with the specified role
     */
    List<User> findByRoleAndIsActiveTrue(UserRole role);

    /**
     * Check if a user exists with the given login or email
     * 
     * @param login The login to check
     * @param email The email to check
     * @return true if a user exists with either the login or email
     */
    boolean existsByEmail(String email);

}
