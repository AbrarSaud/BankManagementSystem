package com.example.bankmanagementsystem.Service;

import com.example.bankmanagementsystem.Model.MyUser;
import com.example.bankmanagementsystem.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final AuthRepository authRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException {
        MyUser myUser = authRepository.findUserByUsername(username);
        if (myUser == null) {
            throw new UsernameNotFoundException("Wrong username or password");
        }
        return myUser;
    }
}
