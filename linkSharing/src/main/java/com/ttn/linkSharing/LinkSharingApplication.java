package com.ttn.linkSharing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.ttn.linkSharing.entity"})
@EnableJpaRepositories(basePackages = {"com.ttn.linkSharing.repository"})
public class LinkSharingApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinkSharingApplication.class, args);
	}

}
