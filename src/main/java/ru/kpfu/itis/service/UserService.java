package ru.kpfu.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.UserAuthority;
import ru.kpfu.itis.repository.UserAuthorityRepository;
import ru.kpfu.itis.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserAuthorityRepository userAuthorityRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public void registerUser(User user) {
        if(userRepo.findByUsername(user.getUsername()) != null){
            throw new DuplicateKeyException("Duplicate key - username field.");
        }
        user.addAuthority(userAuthorityRepo.findByAuthority("ROLE_USER"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setConfirmPassword(user.getPassword());
        userRepo.save(user);
    }

    public User updateFullNameAndAuthorities(Long id, Set<UserAuthority> authorities) {
        try {
            User user = userRepo.findById(id).get();
            user.setConfirmPassword(user.getPassword());
            user.setAuthorities(authorities);
            return user;
        } catch (NoSuchElementException ex) {
            throw new EntityNotFoundException("User with id " + id + "has not been found.");
        }
    }

}
