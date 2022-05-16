package com.clubbeer.user.service;

import com.clubbeer.user.entity.User;
import com.clubbeer.user.repository.IUserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {
    private final IUserRepository iUserRepository;

    public UserService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    public User getUserByEmail(String email){
        return iUserRepository.findByEmail(email)
                .orElseThrow(() ->  new UsernameNotFoundException("User with email " + email + " was not found in the database"));
    }
}
