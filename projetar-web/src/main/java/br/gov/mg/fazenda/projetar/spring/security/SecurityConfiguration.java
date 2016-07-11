package br.gov.mg.fazenda.projetar.spring.security;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.FilterType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


/**
 * 
 * @author leonardo luz fernandes
 * @since 02/04/2016
 * @version 0.1
 * 
 */


@ComponentScan(	
		basePackages={"br.gov.mg.fazenda.projetar.spring.security"},
		lazyInit=true,
		useDefaultFilters=true
		//,
		//excludeFilters={
		//		@Filter(type=FilterType.ANNOTATION,value=javax.inject.Named.class) , 
		//		@Filter(type=FilterType.ANNOTATION,value=javax.inject.Inject.class),
		//		@Filter(type=FilterType.ANNOTATION,value=javax.inject.Singleton.class)
		//}
)
@Configuration
@EnableWebSecurity(debug=false)
@EnableGlobalMethodSecurity(securedEnabled=true,jsr250Enabled=true,prePostEnabled=true)
@SuppressWarnings("serial")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter implements Serializable {
	
	private Logger logger = LoggerFactory.getLogger( SecurityConfiguration.class.getName() );
	
	@Autowired
	private UserDetailsService userDetailService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public SecurityConfiguration() {
		logger.info("Starting Security Configuration ..." );
	}
	
	//@Autowired
	@Override
	public void configure(  AuthenticationManagerBuilder builder ) throws Exception {
		
		try{
		
			logger.info( "Security Configuration: Authentication Manager Builder" );
			logger.info( "User Detail service is wired? %s " , ( userDetailService != null ? "true" : "false" ) );
			logger.info( "Password Encoder is wired? %s " , ( encoder != null ? "true" : "false" ) );
			
			builder.userDetailsService( userDetailService ).passwordEncoder( encoder );
		
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
		
	}
	
	@Bean(name="myAuthenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	//@Autowired
	@Override
	public void configure( HttpSecurity hs ) throws Exception {
		
		try{
		
			logger.info("Security Configuration: Http Security");
			
			//
			hs.authorizeRequests()
				.antMatchers(
						"/index.**",
						"/login.**",
						"/500.**",
						"/503.**",
						"/401.**",
						"/403.**",
						"/404.**",						
						"/error.**").permitAll()
				.antMatchers("/resources/**").permitAll()
				.antMatchers("/javax.faces.resource/**").permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.formLogin()
					.loginPage("/login.xhtml")
					.failureUrl("/login.xhtml?error=true")
					.defaultSuccessUrl("/pages/dashboard/index.xhtml",true)
					.usernameParameter("j_username")
					.passwordParameter("j_password")
					.permitAll()
				.and()
				.logout()
					.logoutUrl("/pages/logout/logout.xhtml")
					.logoutRequestMatcher(new AntPathRequestMatcher( "/pages/logout/logout.xhtml" , "GET" , true  ) )
					.logoutSuccessUrl("/login.xhtml")
					.invalidateHttpSession(true)
					//.logoutSuccessHandler(logoutSuccessHandler)
					//.addLogoutHandler(logoutHandler)
					.deleteCookies("JSESSIONID","XSRF-TOKEN","X-XSRF-TOKEN")
					.clearAuthentication(true)
				.and()
					.exceptionHandling()
					.accessDeniedPage("/403.xhtml")
					.accessDeniedHandler( new CustomAccessDeniedHandler() )
				.and()
					.httpBasic()
				.and()
					.sessionManagement()
					.maximumSessions(1)
					.expiredUrl("/login.xhtml?expired=true");
			
		
				hs.csrf().disable();
				//.csrfTokenRepository( new CookieCsrfTokenRepository() )
						
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
		
	}
	
	//@Autowired
	@Override
	public void configure( WebSecurity ws ){
		
		logger.info( "Security Configuration: WebSecurity " );
		
		ws.ignoring().
		antMatchers(
				"/resources/**",
				"/javax.faces.resource/**");
		
	}
	
}
