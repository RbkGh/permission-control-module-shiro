package com.rodneyboachie.permcontrol;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = PermControlApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PermControlApplicationTests {

	@Test
	void contextLoads() {
	}

}
