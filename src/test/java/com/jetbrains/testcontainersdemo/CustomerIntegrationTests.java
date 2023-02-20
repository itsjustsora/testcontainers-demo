package com.jetbrains.testcontainersdemo;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
// @Testcontainers
class CustomerIntegrationTests extends AbstractTest {

	@Autowired
	private CustomerDao customerDao;

	@Test
	void when_using_a_clean_db_this_should_be_empty() {
		// container.withClasspathResourceMapping("application.yml", "/temp/application.yml", BindMode.READ_ONLY);
		// << useful methods >>
		// container.withFileSystemBind();
		// container.exeInContainer("ls", "al");
		// String stdout = container.getLogs(OutputFrame.OutputType.STDERR);
		// container.withLogConsumer(new Sl4jLogConsumer());
		// Integer onYourMachine = container.getMappedPort(3306);

		List<Customer> customers = customerDao.findAll();
		assertThat(customers).hasSize(2);
	}
}
