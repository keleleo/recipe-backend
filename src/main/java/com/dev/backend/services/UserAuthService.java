package com.dev.backend.services;

import com.dev.backend.models.UserAccount;
import com.dev.backend.models.UserAccountAuth;
import com.dev.backend.repositories.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService implements UserDetailsService {
    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByEmail(email);

        if (userAccount == null) {
            throw new UsernameNotFoundException("Email not found");
        }

        UserAccountAuth userAccountAuth = new UserAccountAuth(userAccount);
        return userAccountAuth;
    }
}
