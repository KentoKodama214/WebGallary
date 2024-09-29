package com.web.gallary.hepler;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class KbnHelperTest {

	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class convertToLinkedHashMap {
		@Test
		@Order(1)
		@DisplayName("正常系：区分グループなし")
		void convertToLinkedHashMap_not_kbnGroup() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：区分グループあり")
		void convertToLinkedHashMap_with_kbnGroup() {
			assertTrue(false);
		}
	}
}