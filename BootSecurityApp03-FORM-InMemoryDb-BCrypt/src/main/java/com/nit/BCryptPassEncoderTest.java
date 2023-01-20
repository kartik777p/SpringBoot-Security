package com.nit;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPassEncoderTest {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		String pass1=encoder.encode("Kartik@123");
		String pass2=encoder.encode("Saurabh@123");
		String pass3=encoder.encode("Sam@123");
		String pass4=encoder.encode("Kp@123");
		System.out.println(pass1);
		System.out.println(pass2);
		System.out.println(pass3);
		System.out.println(pass4);

	}

}
