package com.jetbrains.testcontainersdemo;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CustomerIntegrationTests {

	@Autowired
	private CustomerDao customerDao;

	@Test
	void when_using_a_clean_db_this_should_be_empty() {
		List<Customer> customers = customerDao.findAll();
		assertThat(customers).hasSize(2);
	}
}
