// package com.spring.social.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import
// org.springframework.security.core.userdetails.UsernameNotFoundException;

// import com.spring.social.repository.UsersRepository;

// import lombok.RequiredArgsConstructor;

// @Configuration
// @RequiredArgsConstructor
// public class ApplicationConfig {

// private final UsersRepository usersRepository;

// @Bean
// public UserDetailsService userDetailsService() {
// return username -> usersRepository.findByUsername(username)
// .orElseThrow(() -> new UsernameNotFoundException("User not Found"));
// }
// }
