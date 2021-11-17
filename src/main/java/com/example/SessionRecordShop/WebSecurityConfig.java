package com.example.SessionRecordShop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.SessionRecordShop.webcontrol.UserDetailServiceImpl;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailServiceImpl userDetailsService;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests().antMatchers("/css/**", "/", "/recordlist", "/shoppingcart", "/addcart/{id}", "/plusone/{id}", "/minusone/{id}", "/deleteitem/{id}").permitAll()
        .and()
        .authorizeRequests().anyRequest().authenticated()
        .and()
      .formLogin()
      	  .loginPage("/login") // jos .loginPage("/login"), silloin Controllerille metodi, joka ohjaa "/login" end pointtiin. Ilman .loginPage("/login") koodi ohjaa default login sivulle. 
          .defaultSuccessUrl("/recordlist", true)
          .permitAll()
          .and()
      .logout()
          .permitAll();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationMB) throws Exception {
    	authenticationMB.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

}
