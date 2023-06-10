package com.example.tanfeeth.Config;


import com.example.tanfeeth.Service.MyUserDetailsService;
import com.example.tanfeeth.Model.MyUser;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringConfig {

    private final MyUserDetailsService myUserDetailsService;


    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(myUserDetailsService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        System.out.println(authenticationProvider);
        return authenticationProvider;
    }

//    @Bean
//    public DaoAuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
//        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
//        return daoAuthenticationProvider;
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/register").permitAll()
                //admin access
                .requestMatchers("/api/v1/auth/admin").hasAuthority("ADMIN")
                //user access
                .requestMatchers("/api/v1/auth/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();
    }
}

