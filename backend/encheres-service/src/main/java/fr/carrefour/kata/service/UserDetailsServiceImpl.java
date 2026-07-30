package fr.carrefour.kata.service;

import fr.carrefour.kata.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.stream.Collectors;
import fr.carrefour.kata.entity.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User u = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var authorities = u.getRoles().stream().map(r -> new SimpleGrantedAuthority(r)).collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getPassword(), authorities);
    }
}
