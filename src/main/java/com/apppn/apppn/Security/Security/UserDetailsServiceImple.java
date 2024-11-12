package com.apppn.apppn.Security.Security;




import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.apppn.apppn.Models.User;
import com.apppn.apppn.Repository.UserRepository;


@Service
public class UserDetailsServiceImple implements UserDetailsService {

    private final UserRepository usuarioRepository;

  

    public UserDetailsServiceImple(UserRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User usuario = this.usuarioRepository.findByEmail(username);
        if (usuario == null) {
            return null;

        }
        return usuario;
    }

}
