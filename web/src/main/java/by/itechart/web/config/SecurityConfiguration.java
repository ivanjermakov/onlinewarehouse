package by.itechart.web.config;

import by.itechart.web.security.JwtAuthenticationEntryPoint;
import by.itechart.web.security.JwtAuthorizationTokenFilter;
import by.itechart.web.security.JwtUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@ComponentScan
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.route.authentication.path}")
    private String authenticationPath;


    private final JwtAuthenticationEntryPoint unauthorizedHandler;
    private JwtUserDetailsServiceImpl jwtUserDetailsService;
    final JwtAuthorizationTokenFilter authenticationTokenFilter;


    @Autowired
    public SecurityConfiguration(JwtUserDetailsServiceImpl jwtUserDetailsService,
                                 JwtAuthenticationEntryPoint unauthorizedHandler,
                                 JwtAuthorizationTokenFilter authenticationTokenFilter) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.unauthorizedHandler = unauthorizedHandler;
        this.authenticationTokenFilter = authenticationTokenFilter;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(jwtUserDetailsService)
                .passwordEncoder(passwordEncoderBean());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers(
                        HttpMethod.POST,
                        authenticationPath
                )

                // allow anonymous resource requests
                .and()
                .ignoring()
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                )

        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/console/**").permitAll() // TODO: Enables h2 console - only for development environment
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/signUp").permitAll()
                .antMatchers("/api/**").authenticated() //Redundant
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutUrl("/api/logout")
                .deleteCookies(REMEMBER_ME_COOKIE)
                .permitAll()
                .and()
                .headers() // TODO: Enables h2 console - only for development environment
                .frameOptions()
                .disable()
                .and()
                .rememberMe()
                .rememberMeServices(rememberMeService())
                .key(REMEMBER_ME_TOKEN)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new Http401AuthenticationEntryPoint("Access Denied"))
                .and()
                .csrf()
                .csrfTokenRepository(csrfTokenRepository())
                .and()
                .cors()
                .and()
                .addFilterAfter(csrfFilter, CsrfFilter.class)
        ;
        // @formatter:on
        http
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()

                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()

                // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .authorizeRequests()

                // Un-secure H2 Database
                .antMatchers("/h2-console/**/**").permitAll()

                .antMatchers("/auth/**").permitAll()
                .anyRequest().authenticated();

        http
                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        http
                .headers()
                .frameOptions().sameOrigin()
                .cacheControl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("OPTIONS");
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }

}
