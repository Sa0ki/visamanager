package com.visamanager.security.services;


import com.visamanager.models.Admin;
import com.visamanager.models.Client;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServicePerso implements UserDetailsService {
    private ClientAccountService accountService;
    private AdminAccountService adminAccountService;
    @Override
    public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {
        Client client = accountService.loadClientByEmail(input);
        if(client == null){
            Admin admin = adminAccountService.loadAdminBySecretCode(input);
            if(admin == null)
                throw new UsernameNotFoundException(String.format("User with  '%s' not found.", input));
            else{
                String [] roleList = admin.getRoles().stream()
                        .map(role -> role.getName())
                        .toArray(String[]:: new);

                UserDetails userDetails = User.withUsername(admin.getSecretCode())
                        .password(admin.getPassword())
                        .roles(roleList)
                        .build();

                return userDetails;
            }
        }
        else{
            String [] roleList = client.getRoles().stream()
                    .map(role -> role.getName())
                    .toArray(String[]:: new);

            UserDetails userDetails = User.withUsername(client.getEmail())
                    .password(client.getPassword())
                    .roles(roleList)
                    .build();

            return userDetails;
        }
    }
}
