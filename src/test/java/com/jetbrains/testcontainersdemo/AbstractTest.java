package com.jetbrains.testcontainersdemo;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

public class AbstractTest {

	/**
	 * - having one database per one test -> add 'static' keyword
	 * - docker run -e MYSQL_USERNAME=... -e MYSQL_PASSWORD=... mysql:latest
	 * - when you user withReuse() method, and you have @Container then it will shut down this container.
	 * so all you have to do is delete @TestContainers, @Container.
	 */
	// @Container
	private static MySQLContainer container = (MySQLContainer)new MySQLContainer("mysql:latest")
		.withReuse(true); // not restart
	// withUsername(...)
	// withPassword()

	// and if you have your specific image
	/*
	 * @Container
	 * public static GenericContainer genericContainer = new GenericContainer("myimage:mytag");
	 * */


	@DynamicPropertySource
	public static void overrideProps(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", container::getJdbcUrl);
		registry.add("spring.datasource.username", container::getUsername);
		registry.add("spring.datasource.password", container::getPassword);
	}

	@BeforeAll
	public static void setup() {
		container.start();
	}
}
