package com.clubbeer.user.service;

import com.clubbeer.authority.repository.IAuthorityRepository;
import com.clubbeer.common.exception.EmailAlreadyUsedException;
import com.clubbeer.user.entity.User;
import com.clubbeer.user.repository.IUserRepository;
import com.clubbeer.user.resource.UserResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {
    private final Logger log = LoggerFactory.getLogger(UserService.class);
    public static final String USER = "ROLE_USER";

    private final IUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final IAuthorityRepository authorityRepository;

    @Autowired
    public UserService(IUserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       IAuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
    }

    public User registerUser(UserResource userResource) {
        userRepository
                .findByEmail(userResource.getEmail())
                .ifPresent(existingUser -> {
                    boolean removed = removeNonActivatedUser(existingUser);
                    if (!removed) {
                        throw new EmailAlreadyUsedException();
                    }
                });
        User newUser = userResource.toEntity();
        String encryptedPassword = passwordEncoder.encode(userResource.getPassword());
        newUser.setAuthority(USER);
        newUser.setPassword(encryptedPassword);
        userRepository.save(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    private boolean removeNonActivatedUser(User existingUser) {
        if (existingUser.isActivated()) {
            return false;
        }
        userRepository.delete(existingUser);
        userRepository.flush();
        return true;
    }
}
