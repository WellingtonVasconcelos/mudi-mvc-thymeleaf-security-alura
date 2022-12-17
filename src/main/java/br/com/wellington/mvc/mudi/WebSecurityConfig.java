package br.com.wellington.mvc.mudi;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests()
            .antMatchers("/home/**")
            	.permitAll()
            .anyRequest()
            	.authenticated()
            .and()
            .formLogin(form -> form
            		.loginPage("/login")
            		.defaultSuccessUrl("/usuario/pedido", true)
            		.permitAll()
            		)
            	.logout(logout -> {
            		logout.logoutUrl("/logout")
            		.logoutSuccessUrl("/home");
            	})
        		.csrf().disable();

        return http.build();
    }

	
	@Bean
    public UserDetailsManager users(DataSource dataSource) {

        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);

        return users;
    }
	
}