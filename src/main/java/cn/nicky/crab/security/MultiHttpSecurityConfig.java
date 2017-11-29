package cn.nicky.crab.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MultiHttpSecurityConfig {

    @Bean
    public CustomUserDetailsService customUserDetailsService(){
        return new CustomUserDetailsService();
    }
    @Configuration
    public static class ClientConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/welcome").permitAll()
                    .anyRequest()
                    .authenticated()
                    // log in
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?error=loginError")
                    .defaultSuccessUrl("/index")
                    // logout
                    .and().logout().logoutUrl("/logout")
                    .logoutSuccessUrl("/login");
//                    .and()
//                    .csrf()
//                    .disable();
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/**/*.*");
        }

//        @Override
//        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.userDetailsService(customUserDetailsService);
//        }
//
//        @Bean
//        public CustomUserDetailsService customUserDetailsService(){
//            return new CustomUserDetailsService();
//        }
    }

    @Order(1)
    @Configuration
    public static class AdminConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/admin/**")
                    .authorizeRequests()
                    .antMatchers("/admin/login").permitAll()
                    .anyRequest().hasRole("ADMIN")
                    // log in
                    .and()
                    .formLogin()
                    .loginPage("/admin/login")
                    .failureUrl("/admin/login2?error=loginError")
                    .defaultSuccessUrl("/admin/home")
                    // logout
                    .and().logout().logoutUrl("/**/logout")
                    .logoutSuccessUrl("/admin/login").deleteCookies("JSESSIONID");
//                    .and()
//                    .csrf()
//                    .disable();
        }

//        @Override
//        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.userDetailsService(customUserDetailsService);
//        }
    }
}