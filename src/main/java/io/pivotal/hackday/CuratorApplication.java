package io.pivotal.hackday;

import io.pivotal.hackday.firehose.FirehoseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Arrays;

@SpringBootApplication
@EnableAutoConfiguration
@EnableAsync
public class CuratorApplication extends SpringBootServletInitializer {

	@Autowired
	FirehoseService firehoseService;

	@PostConstruct
	public void run() throws Exception {
		firehoseService.start();
	}

	public static void main(String[] args) {
		SpringApplication.run(CuratorApplication.class, args);
	}
}
