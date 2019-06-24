package com.spring.security.demo.SecurityDemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfig implements WebMvcConfigurer{

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		System.out.println("view registered here...");
		registry.addViewController("/welcome").setViewName("welcome");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/error").setViewName("error");
		registry.addViewController("/welcome").setViewName("welcome");
		registry.addViewController("/logout-success").setViewName("logout-success");
		//registry.addRedirectViewController("/login", "/topics");
	}

	@Override
	public void configureViewResolvers (ViewResolverRegistry registry) {
		//by default prefix = "/WEB-INF/" and  suffix = ".jsp"
		System.out.println("view resolver registry...");
		//registry.jsp().prefix("/views/");
		InternalResourceViewResolver resolver= new InternalResourceViewResolver();
		resolver.setPrefix("/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		registry.viewResolver(resolver);
	}

	/*@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver= new InternalResourceViewResolver();
		resolver.setPrefix("");
		resolver.setSuffix(".html");
		//resolver.setViewClass(JstlView.class);
		System.out.println("viewResolver registered...");
		return resolver;
	} */

	@Configuration
	protected class SpringAuth extends WebSecurityConfigurerAdapter {

		@Autowired
		UserDetailsService userDetailsService;

		/*		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication().withUser("user").password("{noop}user").roles("USER")
			.and()
			.withUser("admin").password("{noop}admin").roles("ADMIN");
		}*/

		public AuthenticationProvider authProvider() {
			DaoAuthenticationProvider daoAuth= new DaoAuthenticationProvider();
			daoAuth.setUserDetailsService(userDetailsService);
			//daoAuth.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
			//daoAuth.setPasswordEncoder(new BCryptPasswordEncoder());
			daoAuth.setPasswordEncoder(new BCryptPasswordEncoder());

			return daoAuth;
		}

		public void configure(HttpSecurity http) throws Exception {
			http.csrf().disable();
			http.httpBasic() //--> Basic auth when connecting from Postman
			.and()
			.authorizeRequests().antMatchers(HttpMethod.POST).hasRole("ADMIN")
			.and()
			.authorizeRequests().antMatchers("/topics/**").hasRole("USER")
			.and()
			.authorizeRequests().anyRequest().fullyAuthenticated()
			.and()			
			.formLogin().loginPage("/login").failureForwardUrl("/error").defaultSuccessUrl("/welcome").permitAll()
			/*.and()
			.exceptionHandling().accessDeniedPage("/error")*/
			.and()
			.logout().invalidateHttpSession(true).clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/logout-success")
			.permitAll();
		}
	}

}
