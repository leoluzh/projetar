package br.gov.mg.fazenda.projetar.secutity.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtils {

	public static final String encode( String password ){
		try {
			BCryptPasswordEncoder encoder = encoder();
			return encoder.encode( password );
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return password;
		}
		
	}
	
	public static final BCryptPasswordEncoder encoder() throws NoSuchAlgorithmException {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder( 7 , SecureRandom.getInstance("SHA1PRNG") );
		return encoder;
	}
	
}
