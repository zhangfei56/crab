package cn.nicky.crab.security;

import cn.nicky.crab.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MultiHttpSecurityConfig {

    @Configuration
    public static class ClientConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/anyone/**").permitAll()
                    .antMatchers("/wx/**").permitAll()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/client/register").permitAll()

                    .antMatchers("/welcome").permitAll()
                    .antMatchers("/index").permitAll()
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
                    .deleteCookies()
                    .logoutSuccessUrl("/index");
//                    .and()
//                    .csrf()
//                    .disable();
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/**/*.*");
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(customUserDetailsService()).passwordEncoder(new PasswordEncoder() {
                @Override
                public String encode(CharSequence charSequence) {
                    String md = new Md5PasswordEncoder().encodePassword("123", AppConstants.MD5_PASSWORD_ENCODER_SALT);
                    return new Md5PasswordEncoder().encodePassword(charSequence.toString(), AppConstants.MD5_PASSWORD_ENCODER_SALT);
                }

                @Override
                public boolean matches(CharSequence rawPassword, String encodedPassword) {
                    return new Md5PasswordEncoder().encodePassword(rawPassword.toString(), AppConstants.MD5_PASSWORD_ENCODER_SALT)
                            .equals(encodedPassword);
                }
            });
        }

        @Bean
        public CustomUserDetailsService customUserDetailsService(){
            return new CustomUserDetailsService();
        }

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
                    .failureUrl("/admin/login?error=loginError")
                    .defaultSuccessUrl("/admin/users")
                    // logout
                    .and().logout().logoutUrl("/**/logout")
                    .logoutSuccessUrl("/admin/login").deleteCookies("JSESSIONID");
//                    .and()
//                    .csrf()
//                    .disable();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(customUserDetailsService()).passwordEncoder(new PasswordEncoder() {
                @Override
                public String encode(CharSequence charSequence) {
                    return new Md5PasswordEncoder().encodePassword(charSequence.toString(), AppConstants.MD5_PASSWORD_ENCODER_SALT);
                }

                @Override
                public boolean matches(CharSequence rawPassword, String encodedPassword) {
                    return new Md5PasswordEncoder().encodePassword(rawPassword.toString(), AppConstants.MD5_PASSWORD_ENCODER_SALT)
                            .equals(encodedPassword);
                }
            });
        }


        @Bean
        public CustomUserDetailsService customUserDetailsService(){
            return new CustomUserDetailsService();
        }
    }
}