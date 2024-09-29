package com.web.gallary.service.impl;

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
public class KbnMstServiceImplTest {

	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class getPrefectureList {
		@Test
		@Order(1)
		@DisplayName("正常系：区分マスタが存在する場合")
		void getPrefectureList_found() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：区分マスタが存在しない場合")
		void getPrefectureList_not_found() {
			assertTrue(false);
		}
	}
}