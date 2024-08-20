package com.pwa.saas_server.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author jere
 */
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = String.valueOf(authentication.getPrincipal());
        //注意：这里的 password 是指原始密码，也就是password_raw
        String password = String.valueOf(authentication.getCredentials());

        logger.info("username = " + username + ", password = " + password);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        logger.info("userDetails = " + userDetails.toString());
        logger.info("userDetails.getUsername() = " + userDetails.getUsername());
        logger.info("userDetails.getPassword() = " + userDetails.getPassword());
        logger.info("userDetails.getAuthorities() = " + userDetails.getAuthorities());
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            logger.error("authenticate password and userDetails.getPassword() is match!");
            return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
        }
        logger.error("password is not match~~~~~~~~~~~~~");

        throw new BadCredentialsException("Error!!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
