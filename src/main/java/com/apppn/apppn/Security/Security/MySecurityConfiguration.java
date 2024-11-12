package com.apppn.apppn.Security.Security;




import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizeHandler;

    @Autowired
    private UserDetailsServiceImple userDetailsServiceImple;

    @Autowired
    private JwtAuthenticationFilter authenticationFilter;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean(); // To change body of generated methods, choose Tools | Templates.
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsServiceImple).passwordEncoder(passwordEncoder());
    }


    
  

    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> customOAuth2UserService() {
        return new DefaultOAuth2UserService() {
            @SuppressWarnings("unchecked")
            @Override
            public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
                OAuth2User oAuth2User = super.loadUser(userRequest); // Intenta cargar el usuario desde el proveedor
                System.out.println("++++++++++++"+oAuth2User);
    
                // Si los atributos están vacíos, intenta recuperarlos manualmente
                if (oAuth2User.getAttributes().isEmpty()) {
                    System.out.println("Recuperando atributos manualmente...");
    
                    RestTemplate restTemplate = new RestTemplate();
                    String userInfoEndpointUri = userRequest.getClientRegistration()
                            .getProviderDetails().getUserInfoEndpoint().getUri();
    
                    // Recupera los atributos del usuario del endpoint de información
                    Map<String, Object> userAttributes = null;
                    try {
                        userAttributes = restTemplate.getForObject(userInfoEndpointUri, Map.class);
                    } catch (Exception e) {
                        throw new OAuth2AuthenticationException(e.getMessage());
                    }
    
                    if (userAttributes == null) {
                        throw new OAuth2AuthenticationException("No se pudieron recuperar los atributos del usuario.");
                    }
    
                    // Crea y devuelve un nuevo objeto OAuth2User con los atributos recuperados
                    return new DefaultOAuth2User(
                        Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                        userAttributes,
                        "sub" // "sub" es el atributo principal que puede contener el identificador del usuario
                    );
                }
    
                return oAuth2User; // Si los atributos ya están presentes, devuelve el usuario original
            }
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
        .cors().and()
        .authorizeRequests()
                .antMatchers("/api/v1/auth/**", "/oauth2/callback")
                .permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated()
                // .and().exceptionHandling()
                // .authenticationEntryPoint(unauthorizeHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().oauth2Login().userInfoEndpoint().userService(customOAuth2UserService());
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
