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
            @Override
            public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
                OAuth2User oAuth2User = super.loadUser(userRequest);

                // Si los atributos están vacíos, intenta recuperarlos manualmente
                if (oAuth2User.getAttributes().isEmpty()) {
                    RestTemplate restTemplate = new RestTemplate();
                    String userInfoEndpointUri = userRequest.getClientRegistration()
                        .getProviderDetails().getUserInfoEndpoint().getUri();

                    @SuppressWarnings("unchecked")
                    Map<String, Object> userAttributes = restTemplate
                        .getForObject(userInfoEndpointUri, Map.class);

                    return new DefaultOAuth2User(
                        Collections.singleton(new SimpleGrantedAuthority("USER")),
                        userAttributes,
                        "sub"); // Cambia "sub" si el atributo principal es diferente
                }

                return oAuth2User;
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
