package org.danikzhezmer.schoolkitchen.service;

import org.danikzhezmer.schoolkitchen.entity.User;
import org.danikzhezmer.schoolkitchen.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found: " + username));
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public List<User> findAll(){return userRepository.findAll();}

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }
}
