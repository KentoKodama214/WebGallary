package com.web.gallary.controller.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.web.gallary.controller.AccountController;
import com.web.gallary.exception.ForbiddenAccountException;
import com.web.gallary.model.KbnMstModel;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
@Sql("/sql/common/ResetAccountNoSeq.sql")
public class AccountControllerIntegrationTest {
	@Autowired
	private AccountController accountController;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Nested
	@Order(1)
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/controller/AccountControllerIntegrationTest.sql")
	class register {
		private List<KbnMstModel> createPrefectureList() {
			List<KbnMstModel> prefectureList = new ArrayList<KbnMstModel>();
			prefectureList.add(KbnMstModel.builder()
					.kbnClassCode("prefecture")
					.kbnCode("Hokkaido")
					.sortOrder(1)
					.kbnGroupCode("Hokkaido_Tohoku")
					.kbnClassJapaneseName("都道府県")
					.kbnGroupJapaneseName("北海道・東北")
					.kbnJapaneseName("北海道")
					.kbnClassEnglishName("prefecture")
					.kbnGroupEnglishName("Hokkaido_Tohoku")
					.kbnEnglishName("Hokkaido")
					.explanation("北海道はでっかいどう")
					.build());
			prefectureList.add(KbnMstModel.builder()
					.kbnClassCode("prefecture")
					.kbnCode("Okinawa")
					.sortOrder(47)
					.kbnGroupCode("Kyushu_Okinawa")
					.kbnClassJapaneseName("都道府県")
					.kbnGroupJapaneseName("九州・沖縄")
					.kbnJapaneseName("沖縄")
					.kbnClassEnglishName("prefecture")
					.kbnGroupEnglishName("Kyushu_Okinawa")
					.kbnEnglishName("Okinawa")
					.explanation("沖縄は南国")
					.build());
			
			return prefectureList;
		}
		
		private Map<String, List<KbnMstModel>> createLinkedHashMap() {
			LinkedHashMap<String, List<KbnMstModel>> kbnMstLinkedHashMap = new LinkedHashMap<String, List<KbnMstModel>>();
			
			List<KbnMstModel> hokkaido_tohoku = new ArrayList<KbnMstModel>();
			hokkaido_tohoku.add(KbnMstModel.builder()
					.kbnClassCode("prefecture")
					.kbnCode("Hokkaido")
					.sortOrder(1)
					.kbnGroupCode("Hokkaido_Tohoku")
					.kbnClassJapaneseName("都道府県")
					.kbnGroupJapaneseName("北海道・東北")
					.kbnJapaneseName("北海道")
					.kbnClassEnglishName("prefecture")
					.kbnGroupEnglishName("Hokkaido_Tohoku")
					.kbnEnglishName("Hokkaido")
					.explanation("北海道はでっかいどう")
					.build());
			hokkaido_tohoku.add(KbnMstModel.builder()
					.kbnClassCode("prefecture")
					.kbnCode("Aomori")
					.sortOrder(2)
					.kbnGroupCode("Hokkaido_Tohoku")
					.kbnClassJapaneseName("都道府県")
					.kbnGroupJapaneseName("北海道・東北")
					.kbnJapaneseName("青森")
					.kbnClassEnglishName("prefecture")
					.kbnGroupEnglishName("Hokkaido_Tohoku")
					.kbnEnglishName("Aomori")
					.explanation("青森は本州最北")
					.build());
			kbnMstLinkedHashMap.put("北海道・東北", hokkaido_tohoku);
			
			List<KbnMstModel> kyushu_okinawa = new ArrayList<KbnMstModel>();
			kyushu_okinawa.add(KbnMstModel.builder()
					.kbnClassCode("prefecture")
					.kbnCode("Kagoshima")
					.sortOrder(46)
					.kbnGroupCode("Kyushu_Okinawa")
					.kbnClassJapaneseName("都道府県")
					.kbnGroupJapaneseName("九州・沖縄")
					.kbnJapaneseName("鹿児島")
					.kbnClassEnglishName("prefecture")
					.kbnGroupEnglishName("Kyushu_Okinawa")
					.kbnEnglishName("Kagoshima")
					.explanation("鹿児島は九州最南")
					.build());
			kyushu_okinawa.add(KbnMstModel.builder()
					.kbnClassCode("prefecture")
					.kbnCode("Okinawa")
					.sortOrder(47)
					.kbnGroupCode("Kyushu_Okinawa")
					.kbnClassJapaneseName("都道府県")
					.kbnGroupJapaneseName("九州・沖縄")
					.kbnJapaneseName("沖縄")
					.kbnClassEnglishName("prefecture")
					.kbnGroupEnglishName("Kyushu_Okinawa")
					.kbnEnglishName("Okinawa")
					.explanation("沖縄は南国")
					.build());
			kbnMstLinkedHashMap.put("九州・沖縄", kyushu_okinawa);
			
			return kbnMstLinkedHashMap;
		}
		
		@Test
		@Order(1)
		@SuppressWarnings("unchecked")
		@DisplayName("正常系")
		void register_success() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(2)
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/controller/AccountControllerIntegrationTest.sql")
	class account_setting {
		private List<KbnMstModel> createPrefectureList() {
			List<KbnMstModel> prefectureList = new ArrayList<KbnMstModel>();
			prefectureList.add(KbnMstModel.builder()
					.kbnClassCode("prefecture")
					.kbnCode("Hokkaido")
					.sortOrder(1)
					.kbnGroupCode("Hokkaido_Tohoku")
					.kbnClassJapaneseName("都道府県")
					.kbnGroupJapaneseName("北海道・東北")
					.kbnJapaneseName("北海道")
					.kbnClassEnglishName("prefecture")
					.kbnGroupEnglishName("Hokkaido_Tohoku")
					.kbnEnglishName("Hokkaido")
					.explanation("北海道はでっかいどう")
					.build());
			prefectureList.add(KbnMstModel.builder()
					.kbnClassCode("prefecture")
					.kbnCode("Okinawa")
					.sortOrder(47)
					.kbnGroupCode("Kyushu_Okinawa")
					.kbnClassJapaneseName("都道府県")
					.kbnGroupJapaneseName("九州・沖縄")
					.kbnJapaneseName("沖縄")
					.kbnClassEnglishName("prefecture")
					.kbnGroupEnglishName("Kyushu_Okinawa")
					.kbnEnglishName("Okinawa")
					.explanation("沖縄は南国")
					.build());
			
			return prefectureList;
		}
		
		private Map<String, List<KbnMstModel>> createLinkedHashMap() {
			LinkedHashMap<String, List<KbnMstModel>> kbnMstLinkedHashMap = new LinkedHashMap<String, List<KbnMstModel>>();
			
			List<KbnMstModel> hokkaido_tohoku = new ArrayList<KbnMstModel>();
			hokkaido_tohoku.add(KbnMstModel.builder()
					.kbnClassCode("prefecture")
					.kbnCode("Hokkaido")
					.sortOrder(1)
					.kbnGroupCode("Hokkaido_Tohoku")
					.kbnClassJapaneseName("都道府県")
					.kbnGroupJapaneseName("北海道・東北")
					.kbnJapaneseName("北海道")
					.kbnClassEnglishName("prefecture")
					.kbnGroupEnglishName("Hokkaido_Tohoku")
					.kbnEnglishName("Hokkaido")
					.explanation("北海道はでっかいどう")
					.build());
			hokkaido_tohoku.add(KbnMstModel.builder()
					.kbnClassCode("prefecture")
					.kbnCode("Aomori")
					.sortOrder(2)
					.kbnGroupCode("Hokkaido_Tohoku")
					.kbnClassJapaneseName("都道府県")
					.kbnGroupJapaneseName("北海道・東北")
					.kbnJapaneseName("青森")
					.kbnClassEnglishName("prefecture")
					.kbnGroupEnglishName("Hokkaido_Tohoku")
					.kbnEnglishName("Aomori")
					.explanation("青森は本州最北")
					.build());
			kbnMstLinkedHashMap.put("北海道・東北", hokkaido_tohoku);
			
			List<KbnMstModel> kyushu_okinawa = new ArrayList<KbnMstModel>();
			kyushu_okinawa.add(KbnMstModel.builder()
					.kbnClassCode("prefecture")
					.kbnCode("Kagoshima")
					.sortOrder(46)
					.kbnGroupCode("Kyushu_Okinawa")
					.kbnClassJapaneseName("都道府県")
					.kbnGroupJapaneseName("九州・沖縄")
					.kbnJapaneseName("鹿児島")
					.kbnClassEnglishName("prefecture")
					.kbnGroupEnglishName("Kyushu_Okinawa")
					.kbnEnglishName("Kagoshima")
					.explanation("鹿児島は九州最南")
					.build());
			kyushu_okinawa.add(KbnMstModel.builder()
					.kbnClassCode("prefecture")
					.kbnCode("Okinawa")
					.sortOrder(47)
					.kbnGroupCode("Kyushu_Okinawa")
					.kbnClassJapaneseName("都道府県")
					.kbnGroupJapaneseName("九州・沖縄")
					.kbnJapaneseName("沖縄")
					.kbnClassEnglishName("prefecture")
					.kbnGroupEnglishName("Kyushu_Okinawa")
					.kbnEnglishName("Okinawa")
					.explanation("沖縄は南国")
					.build());
			kbnMstLinkedHashMap.put("九州・沖縄", kyushu_okinawa);
			
			return kbnMstLinkedHashMap;
		}
		
		@Test
		@Order(1)
		@DisplayName("正常系：生年月日が1900/01/01の場合")
		void account_setting_birthdate_is_19000101() throws ForbiddenAccountException {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：生年月日が1900/01/01以外の場合")
		void account_setting_birthdate_is_not_19000101() throws ForbiddenAccountException {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：ForbiddenAccountExceptionをthrowする")
		void account_setting_ForbiddenAccountException() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(3)
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/controller/AccountControllerIntegrationTest.sql")
	class account_list {
		@Test
		@Order(1)
		@DisplayName("正常系")
		void account_list_success() {
			assertTrue(false);
		}
	}
}