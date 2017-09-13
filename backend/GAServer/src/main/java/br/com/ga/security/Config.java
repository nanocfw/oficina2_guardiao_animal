/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author Marciano
 */
@EnableWebSecurity
public class Config extends WebSecurityConfigurerAdapter
{

    private static final String ADMIN = "ADMIN";
//    private static final String CLIENTE = "CLIENTE";
//    private static final String PRESTADOR = "PRESTADOR";

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .antMatchers("/**").hasRole(ADMIN)
                //                .antMatchers("/ga/**").hasRole(PRESTADOR)
                //                .antMatchers("/ga/client/**").hasRole(CLIENTE)
                .and()
                .formLogin();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.inMemoryAuthentication()
                .withUser("admin").password("admin").roles(ADMIN) //                .and()
                //                .withUser("prestador").password("prestador").roles(PRESTADOR)
                //                .and()
                //                .withUser("cliente").password("cliente").roles(CLIENTE)
                ;
    }

}
