package com.example.utilitiesservice.configuration;

import com.example.utilitiesservice.services.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final MyUserDetailsService userDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/v1/roles/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/v1/bills/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/v1/paymentCards/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/v1/residences/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/v1/companies/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/v1/utilityMeters/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/v1/users/**").hasRole("ADMIN")
                .antMatchers("/v3/api-docs").hasRole("ADMIN")
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.cors();

    }

}
