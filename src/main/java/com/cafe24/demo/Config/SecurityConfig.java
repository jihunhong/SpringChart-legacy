package com.cafe24.demo.Config;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.cafe24.demo.VO.SocialType;

import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        CharacterEncodingFilter filter = new CharacterEncodingFilter();

        http.authorizeRequests()
                .antMatchers("/", "/genie", "/melon", "/bugs", "/oauth2/**", "/login/**", "/MDB/**", "/favicon/**" , "/css/**", "/img/**", "/js/**","/h2-console/**", "/youtube/**")
                .permitAll()
                .antMatchers("/google").hasAuthority(SocialType.GOOGLE.getRoleType())
                .anyRequest().authenticated()
            .and()
                .oauth2Login()
                .defaultSuccessUrl("/loginSuccess")
                .failureUrl("/loginFailure")
            .and()
                .headers().frameOptions().disable()
            .and()
                .exceptionHandling()
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint(
                        "/login"))
            .and()
                .formLogin()
                .successForwardUrl("/")
            .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
            .and()
                .addFilterBefore(filter, CsrfFilter.class)
                .csrf().disable();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(OAuth2ClientProperties oAuth2ClientProperties){
        
        List<ClientRegistration> registrations = oAuth2ClientProperties.getRegistration().keySet().stream()
                                                        .map(client -> getRegistration(oAuth2ClientProperties, client))
                                                        .filter(Objects::nonNull)
                                                        .collect(Collectors.toList());
        
        return new InMemoryClientRegistrationRepository(registrations);
    }

    private ClientRegistration getRegistration(OAuth2ClientProperties clientProperties, String client){

        if("google".equals(client)){
            OAuth2ClientProperties.Registration registration = clientProperties.getRegistration().get("google");

            return CommonOAuth2Provider.GOOGLE.getBuilder(client)
                        .clientId(registration.getClientId())
                        .clientSecret(registration.getClientSecret())
                        .scope("email","profile","https://www.googleapis.com/auth/youtube")
                        .build();
        }

        return null;
    }
}