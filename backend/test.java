@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	private JWTFilter jwtFilter;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf()
			.disable()
			.authorizeHttpRequests()
			.requestMatchers(AppConstants.PUBLIC_URLS).permitAll()
			.requestMatchers(AppConstants.USER_URLS).hasAnyAuthority("USER", "ADMIN")
			.requestMatchers(AppConstants.ADMIN_URLS).hasAuthority("ADMIN")
			.anyRequest()
			.authenticated()
			.and()
			.exceptionHandling().authenticationEntryPoint(
					(request, response, authException) -> 
						response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"))
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);	
		
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		http.authenticationProvider(daoAuthenticationProvider());
		
		DefaultSecurityFilterChain defaultSecurityFilterChain = http.build();
		
		return defaultSecurityFilterChain;
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		
		provider.setUserDetailsService(userDetailsServiceImpl);
		provider.setPasswordEncoder(passwordEncoder());
		
		return provider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
}

