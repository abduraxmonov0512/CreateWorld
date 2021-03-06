package com.CreateWorld.createWorld.config;

import com.CreateWorld.createWorld.security.jwt.JwtConfigurer;
import com.CreateWorld.createWorld.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    private static final String ADMIN_ENDPOINT = "/api/admin/**";
    public static final String ADMIN_COUNTRY_ENDPOINT = "/api/adminCountry/***";
    private static final String LOGIN_ENDPOINT = "/api/auth/**";
    public static final String ADMIN_REGION_ENDPOINT = "/api/adminRegion/***";
    public static final String ADMIN_DISTRICT_ENDPOINT = "/api/adminDistrict/**";

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(LOGIN_ENDPOINT).permitAll()
                .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
                .antMatchers(ADMIN_COUNTRY_ENDPOINT).hasRole("ADMIN_COUNTRY")
                .antMatchers(ADMIN_REGION_ENDPOINT).hasRole("ADMIN_REGION")
                .antMatchers(ADMIN_DISTRICT_ENDPOINT).hasRole("ADMIN_DISTRICT")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
