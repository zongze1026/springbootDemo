package com.zongze.security.config;
import com.zongze.security.component.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Date 2020/8/27 10:10
 * @Created by xzz
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    @Autowired
    private LoginFailureHandler loginFailureHandler;
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private TokenAuthFilter tokenAuthFilter;
    @Autowired
    private AuthUserDetailService authUserDetailService;
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    @Autowired
    private BCryptPasswordEncoder encoder;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authUserDetailService).passwordEncoder(encoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/agent/**");
        web.ignoring().antMatchers("/queryBaseLawTaskLog/**");
        web.ignoring().antMatchers(HttpMethod.GET,
                "/v2/api-docs",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui.html/**",
                "/webjars/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //禁用session
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
            .httpBasic().authenticationEntryPoint(accessDeniedHandler)
                .and()
                //配置有多个子节点；执行的顺序会根据配置的顺序执行
            .authorizeRequests()
                .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/", "/login","/login/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/login/logout")
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll()
                .and()
            .csrf().disable()
                //认证失败处理器
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);

        http.addFilterBefore(tokenAuthFilter, UsernamePasswordAuthenticationFilter.class);

    }






}
