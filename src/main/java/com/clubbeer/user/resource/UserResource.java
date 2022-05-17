package com.clubbeer.user.resource;

import com.clubbeer.user.entity.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class UserResource {

    public static final int PASSWORD_MIN_LENGTH = 4;
    public static final int PASSWORD_MAX_LENGTH = 100;

    private Long id;

    @Email
    @Size(min = 5, max = 254)
    private String email;

    private boolean activated = false;

    private String authorities;

    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;

    public UserResource(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.activated = user.isActivated();
        this.authorities = user.getAuthority();
        this.password = user.getPassword();
    }

    public User toEntity() {
        User user = new User();
        user.setId(this.id);
        user.setEmail(this.email);
        user.setActivated(this.activated);
        return user;
    }
}
