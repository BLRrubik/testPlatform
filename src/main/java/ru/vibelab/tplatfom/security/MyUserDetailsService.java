package ru.vibelab.tplatfom.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vibelab.tplatfom.domain.User;
import ru.vibelab.tplatfom.repos.RoleRepo;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private RoleRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        System.out.println("UserDetailsService: " + username);
        User user = userRepository.findUserByUsername(username);



        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        Collection<? extends GrantedAuthority> grantedAuthorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.toString())).collect(Collectors.toList());


        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}