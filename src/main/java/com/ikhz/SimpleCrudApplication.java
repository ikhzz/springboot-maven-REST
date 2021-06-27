package com.ikhz;

import com.ikhz.utility.EncryptionHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SimpleCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleCrudApplication.class, args);
	}

}
