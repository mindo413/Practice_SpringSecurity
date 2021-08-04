package com.dhkim.session_login;

import com.dhkim.session_login.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${static.url}")
    private String staticUrl;

    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Autowired
    private LoginService loginService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //실제 인증을 진행할 Provider
        auth.userDetailsService(loginService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) {
        //이미지,자바스크립트,css 등 정적 자원(static)에 대한 보안 설정
        //web.ignoring().antMatchers(staticUrl + "/**");
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //HTTP 요청 관련 보안 설정
        //HttpServletRequest에 따라 접근 권한 설정
        http.authorizeRequests()
                .antMatchers("/login/**").authenticated()
                .antMatchers("/user/**").hasRole("MEMBER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/**").permitAll()
            .and()
                //로그인 설정
                .formLogin()
                .loginPage("/login/login")
                //.defaultSuccessUrl("/")
                //.failureUrl("/login/login")
                .successHandler(new LoginSuccessHandler())
                .failureHandler(failureHandler)
                .permitAll()
            .and()
                // 로그아웃 설정
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/login/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true) // 세션 제거
            .and()
                //예외처리 핸들링
                .exceptionHandling().accessDeniedPage("/user/denied");
    }

}
