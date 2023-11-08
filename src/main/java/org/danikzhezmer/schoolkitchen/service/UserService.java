package org.danikzhezmer.schoolkitchen.service;

import org.danikzhezmer.schoolkitchen.entity.Role;
import org.danikzhezmer.schoolkitchen.entity.User;
import org.danikzhezmer.schoolkitchen.exception.EntityNotFoundException;
import org.danikzhezmer.schoolkitchen.helper.Roles;
import org.danikzhezmer.schoolkitchen.repository.RoleRepository;
import org.danikzhezmer.schoolkitchen.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found: " + username));
    }

    public User save(User user){
       Role role = roleRepository.findByRoleName(Roles.USER.getRoleName())
               .orElseThrow(() -> new EntityNotFoundException("Role not found"));
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
