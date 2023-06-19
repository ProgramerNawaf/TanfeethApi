package com.example.tanfeeth.Config;


import com.example.tanfeeth.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringConfiguration {

    private final MyUserDetailsService myUserDetailsService;


    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(myUserDetailsService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
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
                .requestMatchers("api/v1/admin/**").permitAll()// admin
                .requestMatchers("/api/v1/account/register/**").permitAll()



                .requestMatchers("/api/v1/account/delete").authenticated()
                .requestMatchers("/api/v1/account/logout").authenticated()

                //only inneed
                .requestMatchers("/api/v1/complaint/**").hasAuthority("INNEED")
                .requestMatchers("/api/v1/inneed-company/**").hasAuthority("INNEED")
                .requestMatchers("/api/v1/project/add","/api/v1/project/delete").hasAuthority("INNEED")


                // only operation
                .requestMatchers("/api/v1/opreation-comany/**").hasAuthority("OPERATION")
                .requestMatchers("/api/v1/staff/**").hasAuthority("OPERATION")
                .requestMatchers("/api/v1/project/project-finish/{projectId}").hasAuthority("OPERATION")
                .requestMatchers("/api/v1/report/add/{idProject}").hasAuthority("OPERATION")


                // all inneed and operation
                .requestMatchers("/api/v1/project/get-all-for-company").authenticated()
                .requestMatchers("/api/v1/project/get-project-company/{projectId}").authenticated()
                .requestMatchers("/api/v1/request/**").authenticated()
                .requestMatchers("/api/v1/project/get-delayed").authenticated()
                .requestMatchers("/api/v1/report/get/{idProject}").authenticated()

                .requestMatchers("/api/v1/account/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/account/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();
    }
}

