package com.firstclub.membership;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MembershipApplication {

	public static void main(String[] args) {
		System.out.println("FirstClub Membership App Starting......");
		SpringApplication.run(MembershipApplication.class, args);
		System.out.println("FirstClub Membership App Started......");
		System.out.println("API end Point: GET http://localhost:8080/api/plans");
	}

}
