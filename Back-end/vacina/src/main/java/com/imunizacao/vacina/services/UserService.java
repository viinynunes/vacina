package com.imunizacao.vacina.services;

import com.imunizacao.vacina.model.User;
import com.imunizacao.vacina.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("Username " + username + " not found");
        } else {
            return user;
        }
    }

    public User create(User user) throws Exception {
        if (userRepository.findByUserName(user.getUsername()) != null) {
            throw new Exception("Username " + user.getUsername() + " alreary exists");
        }

        user.setPassword(encoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow();
    }

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
