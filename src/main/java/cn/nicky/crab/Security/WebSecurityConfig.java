package cn.nicky.crab.Security;

import cn.nicky.crab.config.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig {

    @Configuration
    @Order(1)
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/api/**")
                    .authorizeRequests()
                    .anyRequest().hasRole("ADMIN")
                    .and()
                    .httpBasic();
        }
    }

    @Configuration
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/", "/home").permitAll()
                    //其他地址的访问均需验证权限
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    //指定登录页是"/login"
                    .loginPage("/login")
                    .defaultSuccessUrl("/hello")//登录成功后默认跳转到"/hello"
                    .permitAll()
                    .and()
                    .logout()
                    .logoutSuccessUrl("/home")//退出登录后的默认url是"/home"
                    .permitAll();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(customUserDetailsService());
        }

        @Bean
        public CustomUserDetailsService customUserDetailsService(){
            return new CustomUserDetailsService();
        }
    }


}
