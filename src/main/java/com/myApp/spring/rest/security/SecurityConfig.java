
package com.myApp.spring.rest.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // add users for in memory authentication
        UserBuilder users = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(users.username("john").password("test123").roles("EMPLOYEE"))
                .withUser(users.username("mary").password("test123").roles("EMPLOYEE", "MANAGER"))
                .withUser(users.username("susan").password("test123").roles("EMPLOYEE", "ADMIN"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // secures all REST endpoints under "/api/employees"
        // and adds following security authorizations
        //
        // EMPLOYEE role can perform following
        // 1. Get a list of all employees.  GET /api/employees
        // 2. Get a single employee.  GET /api/employees/{employeeId}

        //
        // MANAGER role can perform following
        // 1. Add a new customer.  POST /api/employees
        // 2. Update an existing employee.  PUT /api/employees
        //

        //
        // ADMIN role can perform following
        // 1. Delete a customer.  DELETE /api/employees/{employeeId}
        //

        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                .antMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                .antMatchers(HttpMethod.POST, "/api/employees").hasAnyRole("MANAGER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/employees/**").hasAnyRole("MANAGER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/employees").hasAnyRole("MANAGER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/employees/**").hasAnyRole("MANAGER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
                .and()
                .httpBasic()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}