package com.web.gallary.controller.integration;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.gallary.AccountPrincipal;
import com.web.gallary.controller.request.PhotoDeleteRequest;
import com.web.gallary.controller.request.PhotoListRequest;
import com.web.gallary.entity.Account;
import com.web.gallary.entity.PhotoFavorite;
import com.web.gallary.entity.PhotoMst;
import com.web.gallary.entity.PhotoTagMst;
import com.web.gallary.enumuration.ErrorValues;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class PhotoRestControllerIntegrationTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Nested
	@Order(1)
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/controller/PhotoRestControllerIntegrationTest.sql")
	class getPhotoList {
		@Test
		@Order(1)
		@DisplayName("正常系：Nullのパラメータがある場合")
		void getPhotoList_with_null_parameter() throws JsonProcessingException, Exception {
			String photoAccountId = "aaaaaaaa";
			PhotoListRequest request = new PhotoListRequest();
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			MvcResult result = mockMvc.perform(
					post("/photo/" + photoAccountId + "/photo_list/get")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(request))
					.with(csrf())
				)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.isLast").value(false))
				.andReturn();
			
			String jsonResponse = result.getResponse().getContentAsString();
			JsonNode photoList = objectMapper.readTree(jsonResponse).get("photoList");
			assertEquals(5, photoList.size());
			
			assertEquals(1, photoList.get(0).get("accountNo").asInt());
			assertEquals(9, photoList.get(0).get("photoNo").asInt());
			assertFalse(photoList.get(0).get("isFavorite").asBoolean());
			assertEquals("https://www.xxx.com/aaaaaaaa/DSC19.jpg", photoList.get(0).get("imageFilePath").asText());
			assertEquals("caption19", photoList.get(0).get("caption").asText());
			assertEquals("horizontal", photoList.get(0).get("directionKbnCode").asText());
			
			assertEquals(1, photoList.get(1).get("accountNo").asInt());
			assertEquals(8, photoList.get(1).get("photoNo").asInt());
			assertFalse(photoList.get(1).get("isFavorite").asBoolean());
			assertEquals("https://www.xxx.com/aaaaaaaa/DSC18.jpg", photoList.get(1).get("imageFilePath").asText());
			assertEquals("caption18", photoList.get(1).get("caption").asText());
			assertEquals("vertical", photoList.get(1).get("directionKbnCode").asText());
			
			assertEquals(1, photoList.get(2).get("accountNo").asInt());
			assertEquals(7, photoList.get(2).get("photoNo").asInt());
			assertFalse(photoList.get(2).get("isFavorite").asBoolean());
			assertEquals("https://www.xxx.com/aaaaaaaa/DSC17.jpg", photoList.get(2).get("imageFilePath").asText());
			assertEquals("caption17", photoList.get(2).get("caption").asText());
			assertEquals("vertical", photoList.get(2).get("directionKbnCode").asText());
			
			assertEquals(1, photoList.get(3).get("accountNo").asInt());
			assertEquals(6, photoList.get(3).get("photoNo").asInt());
			assertFalse(photoList.get(3).get("isFavorite").asBoolean());
			assertEquals("https://www.xxx.com/aaaaaaaa/DSC16.jpg", photoList.get(3).get("imageFilePath").asText());
			assertEquals("caption16", photoList.get(3).get("caption").asText());
			assertEquals("horizontal", photoList.get(3).get("directionKbnCode").asText());
			
			assertEquals(1, photoList.get(4).get("accountNo").asInt());
			assertEquals(5, photoList.get(4).get("photoNo").asInt());
			assertFalse(photoList.get(4).get("isFavorite").asBoolean());
			assertEquals("https://www.xxx.com/aaaaaaaa/DSC15.jpg", photoList.get(4).get("imageFilePath").asText());
			assertEquals("caption15", photoList.get(4).get("caption").asText());
			assertEquals("vertical", photoList.get(4).get("directionKbnCode").asText());
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：タグに半角スペースが含まれている場合")
		void getPhotoList_with_halfspace_tag() throws JsonProcessingException, Exception {
			String photoAccountId = "aaaaaaaa";
			PhotoListRequest request = new PhotoListRequest();
			request.setTagList("太陽 青空");
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			MvcResult result = mockMvc.perform(
					post("/photo/" + photoAccountId + "/photo_list/get")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(request))
					.with(csrf())
				)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.isLast").value(true))
				.andReturn();
			
			String jsonResponse = result.getResponse().getContentAsString();
			JsonNode photoList = objectMapper.readTree(jsonResponse).get("photoList");
			assertEquals(1, photoList.size());
			
			assertEquals(1, photoList.get(0).get("accountNo").asInt());
			assertEquals(1, photoList.get(0).get("photoNo").asInt());
			assertFalse(photoList.get(0).get("isFavorite").asBoolean());
			assertEquals("https://www.xxx.com/aaaaaaaa/DSC11.jpg", photoList.get(0).get("imageFilePath").asText());
			assertEquals("caption11", photoList.get(0).get("caption").asText());
			assertEquals("horizontal", photoList.get(0).get("directionKbnCode").asText());
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：タグに全角スペースが含まれている場合")
		void getPhotoList_with_fullspace_tag() throws JsonProcessingException, Exception {
			String photoAccountId = "aaaaaaaa";
			
			PhotoListRequest request = new PhotoListRequest();
			request.setTagList("太陽　青空");
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			MvcResult result = mockMvc.perform(
					post("/photo/" + photoAccountId + "/photo_list/get")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(request))
					.with(csrf())
				)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.isLast").value(true))
				.andReturn();
			
			String jsonResponse = result.getResponse().getContentAsString();
			JsonNode photoList = objectMapper.readTree(jsonResponse).get("photoList");
			assertEquals(1, photoList.size());
			
			assertEquals(1, photoList.get(0).get("accountNo").asInt());
			assertEquals(1, photoList.get(0).get("photoNo").asInt());
			assertFalse(photoList.get(0).get("isFavorite").asBoolean());
			assertEquals("https://www.xxx.com/aaaaaaaa/DSC11.jpg", photoList.get(0).get("imageFilePath").asText());
			assertEquals("caption11", photoList.get(0).get("caption").asText());
			assertEquals("horizontal", photoList.get(0).get("directionKbnCode").asText());
		}
		
		@Test
		@Order(4)
		@DisplayName("正常系：写真が0件の場合")
		void getPhotoList_not_found_photo() throws JsonProcessingException, Exception {
			String photoAccountId = "aaaaaaaa";
			
			PhotoListRequest request = new PhotoListRequest();
			request.setTagList("太陽　海");
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			MvcResult result = mockMvc.perform(
					post("/photo/" + photoAccountId + "/photo_list/get")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(request))
					.with(csrf())
				)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.isLast").value(true))
				.andReturn();
			
			String jsonResponse = result.getResponse().getContentAsString();
			JsonNode photoList = objectMapper.readTree(jsonResponse).get("photoList");
			assertEquals(0, photoList.size());
		}
	}
	
	@Nested
	@Order(2)
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/controller/PhotoRestControllerIntegrationTest.sql")
	class savePhoto {
		@Test
		@Order(1)
		@DisplayName("正常系：新規登録。写真タグなし、撮影日時なし。Nullパラメータあり")
		void savePhoto_addPhoto_not_photoTag_and_photoAt() throws JsonProcessingException, Exception {
			String photoAccountId = "bbbbbbbb";
			MockMultipartFile multipartFile = new MockMultipartFile(
					"imageFile",
					"DSC111.jpg",
					MediaType.MULTIPART_FORM_DATA_VALUE,
					"sample image".getBytes());
			
			Account sessionAccount = Account.builder()
					.accountNo(2)
					.accountId("bbbbbbbb")
					.accountName("BBBBBBBB")
					.password("$2a$10$password2")
					.authorityKbnCode("administrator")
					.build();
			
			AccountPrincipal accountPrincipal = new AccountPrincipal(sessionAccount, 0);
			Authentication authentication = new UsernamePasswordAuthenticationToken(accountPrincipal, null);
			
			mockMvc.perform(
					multipart("/photo/" + photoAccountId + "/save")
					.file(multipartFile)
					.contentType(MediaType.MULTIPART_FORM_DATA)
					.param("accountNo", "2")
					.param("caption", "")
					.param("imageFilePath", "")
					.param("directionKbnCode", "vertical")
					.param("photoEnglishTitle", "")
					.param("photoJapaneseTitle", "")
					.with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
					.with(csrf())
				)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.httpStatus").value(HttpStatus.OK.value()))
				.andExpect(jsonPath("$.isSuccess").value(true))
				.andExpect(jsonPath("$.message").value("写真登録が完了しました。"));
			
			// photo_mst登録チェック
			List<PhotoMst> actualPhotoMst = jdbcTemplate.query(
					"SELECT * FROM photo.photo_mst where account_no = 2 and photo_no=4", (rs, rowNum) ->
					PhotoMst.builder()
						.accountNo(rs.getInt("account_no"))
						.photoNo(rs.getInt("photo_no"))
						.createdBy(rs.getInt("created_by"))
						.createdAt(rs.getObject("created_at", OffsetDateTime.class))
						.updatedBy(rs.getInt("updated_by"))
						.updatedAt(rs.getObject("updated_at", OffsetDateTime.class))
						.isDeleted(rs.getBoolean("is_deleted"))
						.photoAt(rs.getObject("photo_at", OffsetDateTime.class))
						.locationNo(rs.getInt("location_no"))
						.imageFilePath(rs.getString("image_file_path"))
						.photoJapaneseTitle(rs.getString("photo_japanese_title"))
						.photoEnglishTitle(rs.getString("photo_english_title"))
						.caption(rs.getString("caption"))
						.directionKbnCode(rs.getString("direction_kbn_code"))
						.focalLength(rs.getInt("focal_length"))
						.fValue(rs.getBigDecimal("f_value"))
						.shutterSpeed(rs.getBigDecimal("shutter_speed"))
						.iso(rs.getInt("iso"))
						.build());
			
			assertEquals(1, actualPhotoMst.size());
			assertEquals(2, actualPhotoMst.getFirst().getAccountNo());
			assertEquals(4, actualPhotoMst.getFirst().getPhotoNo());
			assertFalse(actualPhotoMst.getFirst().getIsDeleted());
			assertEquals(OffsetDateTime.of(1900, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)), actualPhotoMst.getFirst().getPhotoAt().plusHours(9));
			assertEquals(0, actualPhotoMst.getFirst().getLocationNo());
			assertEquals("https://www.xxx.com/bbbbbbbb/DSC111.jpg", actualPhotoMst.getFirst().getImageFilePath());
			assertEquals("", actualPhotoMst.getFirst().getPhotoJapaneseTitle());
			assertEquals("", actualPhotoMst.getFirst().getPhotoEnglishTitle());
			assertEquals("", actualPhotoMst.getFirst().getCaption());
			assertEquals("vertical", actualPhotoMst.getFirst().getDirectionKbnCode());
			assertEquals(0, actualPhotoMst.getFirst().getFocalLength());
			assertEquals(0, BigDecimal.ZERO.compareTo(actualPhotoMst.getFirst().getFValue()));
			assertEquals(0, BigDecimal.ZERO.compareTo(actualPhotoMst.getFirst().getShutterSpeed()));
			assertEquals(0, actualPhotoMst.getFirst().getIso());
			
			// photo_tag_mst登録チェック
			List<PhotoTagMst> actualPhotoTagMst = jdbcTemplate.query(
					"SELECT * FROM photo.photo_tag_mst WHERE account_no=2 and photo_no=4", (rs, rowNum) ->
							PhotoTagMst.builder()
								.accountNo(rs.getInt("account_no"))
								.photoNo(rs.getInt("photo_no"))
								.tagNo(rs.getInt("tag_no"))
								.createdBy(rs.getInt("created_by"))
								.createdAt(rs.getObject("created_at", OffsetDateTime.class))
								.tagJapaneseName(rs.getObject("tag_japanese_name").toString())
								.tagEnglishName(rs.getObject("tag_english_name").toString())
								.build());
			assertEquals(0, actualPhotoTagMst.size());
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：新規登録。写真タグあり、撮影日時あり。Nullパラメータなし")
		void savePhoto_addPhoto_with_photoTag_and_photoAt() throws Exception {
			String photoAccountId = "bbbbbbbb";
			MockMultipartFile multipartFile = new MockMultipartFile(
					"imageFile",
					"DSC111.jpg",
					MediaType.MULTIPART_FORM_DATA_VALUE,
					"sample image".getBytes());
			
			Account sessionAccount = Account.builder()
					.accountNo(2)
					.accountId("bbbbbbbb")
					.accountName("BBBBBBBB")
					.password("$2a$10$password2")
					.authorityKbnCode("administrator")
					.build();
			
			AccountPrincipal accountPrincipal = new AccountPrincipal(sessionAccount, 0);
			Authentication authentication = new UsernamePasswordAuthenticationToken(accountPrincipal, null);
			
			mockMvc.perform(
					multipart("/photo/" + photoAccountId + "/save")
					.file(multipartFile)
					.contentType(MediaType.MULTIPART_FORM_DATA)
					.param("accountNo", "2")
					.param("caption", "caption111")
					.param("imageFilePath", "")
					.param("directionKbnCode", "vertical")
					.param("photoEnglishTitle", "title111")
					.param("photoJapaneseTitle", "タイトル111")
					.param("photoAt", LocalDateTime.of(2000, 1, 1, 0, 0, 0).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
					.param("focalLength", "24")
					.param("fValue", "8.0")
					.param("shutterSpeed", "0.01")
					.param("iso", "100")
					.param("photoTagRegistRequestList[0].accountNo", "2")
					.param("photoTagRegistRequestList[0].tagJapaneseName", "太陽")
					.param("photoTagRegistRequestList[0].tagEnglishName", "sun")
					.param("photoTagRegistRequestList[1].accountNo", "2")
					.param("photoTagRegistRequestList[1].tagJapaneseName", "青空")
					.param("photoTagRegistRequestList[1].tagEnglishName", "bluesky")
					.with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
					.with(csrf())
				)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.httpStatus").value(HttpStatus.OK.value()))
				.andExpect(jsonPath("$.isSuccess").value(true))
				.andExpect(jsonPath("$.message").value("写真登録が完了しました。"));
			
			// photo_mst登録チェック
			List<PhotoMst> actualPhotoMst = jdbcTemplate.query(
					"SELECT * FROM photo.photo_mst where account_no = 2 and photo_no=4", (rs, rowNum) ->
					PhotoMst.builder()
						.accountNo(rs.getInt("account_no"))
						.photoNo(rs.getInt("photo_no"))
						.createdBy(rs.getInt("created_by"))
						.createdAt(rs.getObject("created_at", OffsetDateTime.class))
						.updatedBy(rs.getInt("updated_by"))
						.updatedAt(rs.getObject("updated_at", OffsetDateTime.class))
						.isDeleted(rs.getBoolean("is_deleted"))
						.photoAt(rs.getObject("photo_at", OffsetDateTime.class))
						.locationNo(rs.getInt("location_no"))
						.imageFilePath(rs.getString("image_file_path"))
						.photoJapaneseTitle(rs.getString("photo_japanese_title"))
						.photoEnglishTitle(rs.getString("photo_english_title"))
						.caption(rs.getString("caption"))
						.directionKbnCode(rs.getString("direction_kbn_code"))
						.focalLength(rs.getInt("focal_length"))
						.fValue(rs.getBigDecimal("f_value"))
						.shutterSpeed(rs.getBigDecimal("shutter_speed"))
						.iso(rs.getInt("iso"))
						.build());
			
			assertEquals(1, actualPhotoMst.size());
			assertEquals(2, actualPhotoMst.getFirst().getAccountNo());
			assertEquals(4, actualPhotoMst.getFirst().getPhotoNo());
			assertFalse(actualPhotoMst.getFirst().getIsDeleted());
			assertEquals(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)), actualPhotoMst.getFirst().getPhotoAt().plusHours(9));
			assertEquals(0, actualPhotoMst.getFirst().getLocationNo());
			assertEquals("https://www.xxx.com/bbbbbbbb/DSC111.jpg", actualPhotoMst.getFirst().getImageFilePath());
			assertEquals("タイトル111", actualPhotoMst.getFirst().getPhotoJapaneseTitle());
			assertEquals("title111", actualPhotoMst.getFirst().getPhotoEnglishTitle());
			assertEquals("caption111", actualPhotoMst.getFirst().getCaption());
			assertEquals("vertical", actualPhotoMst.getFirst().getDirectionKbnCode());
			assertEquals(24, actualPhotoMst.getFirst().getFocalLength());
			assertEquals(0, BigDecimal.valueOf(8.0).compareTo(actualPhotoMst.getFirst().getFValue()));
			assertEquals(0, BigDecimal.valueOf(0.01).compareTo(actualPhotoMst.getFirst().getShutterSpeed()));
			assertEquals(100, actualPhotoMst.getFirst().getIso());
			
			// photo_tag_mst登録チェック
			List<PhotoTagMst> actualPhotoTagMst = jdbcTemplate.query(
					"SELECT * FROM photo.photo_tag_mst WHERE account_no=2 and photo_no=4", (rs, rowNum) ->
							PhotoTagMst.builder()
								.accountNo(rs.getInt("account_no"))
								.photoNo(rs.getInt("photo_no"))
								.tagNo(rs.getInt("tag_no"))
								.createdBy(rs.getInt("created_by"))
								.createdAt(rs.getObject("created_at", OffsetDateTime.class))
								.tagJapaneseName(rs.getObject("tag_japanese_name").toString())
								.tagEnglishName(rs.getObject("tag_english_name").toString())
								.build());
			assertEquals(2, actualPhotoTagMst.size());
			
			assertEquals(2, actualPhotoTagMst.get(0).getAccountNo());
			assertEquals(4, actualPhotoTagMst.get(0).getPhotoNo());
			assertEquals(1, actualPhotoTagMst.get(0).getTagNo());
			assertEquals("太陽", actualPhotoTagMst.get(0).getTagJapaneseName());
			assertEquals("sun", actualPhotoTagMst.get(0).getTagEnglishName());
			assertEquals(2, actualPhotoTagMst.get(1).getAccountNo());
			assertEquals(4, actualPhotoTagMst.get(1).getPhotoNo());
			assertEquals(2, actualPhotoTagMst.get(1).getTagNo());
			assertEquals("青空", actualPhotoTagMst.get(1).getTagJapaneseName());
			assertEquals("bluesky", actualPhotoTagMst.get(1).getTagEnglishName());
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：更新。写真タグあり、撮影日時あり。Nullパラメータなし")
		void savePhoto_updatePhoto_with_photoTag_and_photoAt() throws Exception {
			String photoAccountId = "bbbbbbbb";
			
			Account sessionAccount = Account.builder()
					.accountNo(2)
					.accountId("bbbbbbbb")
					.accountName("BBBBBBBB")
					.password("$2a$10$password2")
					.authorityKbnCode("administrator")
					.build();
			
			AccountPrincipal accountPrincipal = new AccountPrincipal(sessionAccount, 0);
			Authentication authentication = new UsernamePasswordAuthenticationToken(accountPrincipal, null);
			
			mockMvc.perform(
					multipart("/photo/" + photoAccountId + "/save")
					.contentType(MediaType.MULTIPART_FORM_DATA)
					.param("accountNo", "2")
					.param("photoNo", "1")
					.param("caption", "caption111")
					.param("imageFilePath", "https://www.xxx.com/bbbbbbbb/DSC21.jpg")
					.param("directionKbnCode", "vertical")
					.param("photoEnglishTitle", "title111")
					.param("photoJapaneseTitle", "タイトル111")
					.param("photoAt", LocalDateTime.of(2000, 1, 1, 0, 0, 0).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
					.param("focalLength", "24")
					.param("fValue", "8.0")
					.param("shutterSpeed", "0.01")
					.param("iso", "100")
					.param("photoTagRegistRequestList[0].accountNo", "2")
					.param("photoTagRegistRequestList[0].photoNo", "1")
					.param("photoTagRegistRequestList[0].tagJapaneseName", "太陽")
					.param("photoTagRegistRequestList[0].tagEnglishName", "sun")
					.param("photoTagRegistRequestList[1].accountNo", "2")
					.param("photoTagRegistRequestList[1].photoNo", "1")
					.param("photoTagRegistRequestList[1].tagJapaneseName", "青空")
					.param("photoTagRegistRequestList[1].tagEnglishName", "bluesky")
					.with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
					.with(csrf())
				)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.httpStatus").value(HttpStatus.OK.value()))
				.andExpect(jsonPath("$.isSuccess").value(true))
				.andExpect(jsonPath("$.message").value("写真登録が完了しました。"));
			
			// photo_mst登録チェック
			List<PhotoMst> actualPhotoMst = jdbcTemplate.query(
					"SELECT * FROM photo.photo_mst where account_no = 2 and photo_no=1", (rs, rowNum) ->
					PhotoMst.builder()
						.accountNo(rs.getInt("account_no"))
						.photoNo(rs.getInt("photo_no"))
						.createdBy(rs.getInt("created_by"))
						.createdAt(rs.getObject("created_at", OffsetDateTime.class))
						.updatedBy(rs.getInt("updated_by"))
						.updatedAt(rs.getObject("updated_at", OffsetDateTime.class))
						.isDeleted(rs.getBoolean("is_deleted"))
						.photoAt(rs.getObject("photo_at", OffsetDateTime.class))
						.locationNo(rs.getInt("location_no"))
						.imageFilePath(rs.getString("image_file_path"))
						.photoJapaneseTitle(rs.getString("photo_japanese_title"))
						.photoEnglishTitle(rs.getString("photo_english_title"))
						.caption(rs.getString("caption"))
						.directionKbnCode(rs.getString("direction_kbn_code"))
						.focalLength(rs.getInt("focal_length"))
						.fValue(rs.getBigDecimal("f_value"))
						.shutterSpeed(rs.getBigDecimal("shutter_speed"))
						.iso(rs.getInt("iso"))
						.build());
			
			assertEquals(1, actualPhotoMst.size());
			assertEquals(2, actualPhotoMst.getFirst().getAccountNo());
			assertEquals(1, actualPhotoMst.getFirst().getPhotoNo());
			assertFalse(actualPhotoMst.getFirst().getIsDeleted());
			assertEquals(OffsetDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(0)), actualPhotoMst.getFirst().getPhotoAt().plusHours(9));
			assertEquals(0, actualPhotoMst.getFirst().getLocationNo());
			assertEquals("https://www.xxx.com/bbbbbbbb/DSC21.jpg", actualPhotoMst.getFirst().getImageFilePath());
			assertEquals("タイトル111", actualPhotoMst.getFirst().getPhotoJapaneseTitle());
			assertEquals("title111", actualPhotoMst.getFirst().getPhotoEnglishTitle());
			assertEquals("caption111", actualPhotoMst.getFirst().getCaption());
			assertEquals("vertical", actualPhotoMst.getFirst().getDirectionKbnCode());
			assertEquals(24, actualPhotoMst.getFirst().getFocalLength());
			assertEquals(0, BigDecimal.valueOf(8.0).compareTo(actualPhotoMst.getFirst().getFValue()));
			assertEquals(0, BigDecimal.valueOf(0.01).compareTo(actualPhotoMst.getFirst().getShutterSpeed()));
			assertEquals(100, actualPhotoMst.getFirst().getIso());
			
			// photo_tag_mst登録チェック
			List<PhotoTagMst> actualPhotoTagMst = jdbcTemplate.query(
					"SELECT * FROM photo.photo_tag_mst WHERE account_no=2 and photo_no=1", (rs, rowNum) ->
							PhotoTagMst.builder()
								.accountNo(rs.getInt("account_no"))
								.photoNo(rs.getInt("photo_no"))
								.tagNo(rs.getInt("tag_no"))
								.createdBy(rs.getInt("created_by"))
								.createdAt(rs.getObject("created_at", OffsetDateTime.class))
								.tagJapaneseName(rs.getObject("tag_japanese_name").toString())
								.tagEnglishName(rs.getObject("tag_english_name").toString())
								.build());
			assertEquals(2, actualPhotoTagMst.size());
			
			assertEquals(2, actualPhotoTagMst.get(0).getAccountNo());
			assertEquals(1, actualPhotoTagMst.get(0).getPhotoNo());
			assertEquals(1, actualPhotoTagMst.get(0).getTagNo());
			assertEquals("太陽", actualPhotoTagMst.get(0).getTagJapaneseName());
			assertEquals("sun", actualPhotoTagMst.get(0).getTagEnglishName());
			assertEquals(2, actualPhotoTagMst.get(1).getAccountNo());
			assertEquals(1, actualPhotoTagMst.get(1).getPhotoNo());
			assertEquals(2, actualPhotoTagMst.get(1).getTagNo());
			assertEquals("青空", actualPhotoTagMst.get(1).getTagJapaneseName());
			assertEquals("bluesky", actualPhotoTagMst.get(1).getTagEnglishName());
		}
		
		@Test
		@Order(4)
		@DisplayName("異常系：アクセス不正。ForbiddenAccountExceptionをthrowする")
		void savePhoto_ForbiddenAccountException() throws Exception {
			String photoAccountId = "bbbbbbbb";
			MockMultipartFile multipartFile = new MockMultipartFile(
					"imageFile",
					"DSC111.jpg",
					MediaType.MULTIPART_FORM_DATA_VALUE,
					"sample image".getBytes());
			
			Account sessionAccount = Account.builder()
					.accountNo(1)
					.accountId("aaaaaaaa")
					.accountName("AAAAAAAA")
					.password("$2a$10$password1")
					.authorityKbnCode("administrator")
					.build();
			
			AccountPrincipal accountPrincipal = new AccountPrincipal(sessionAccount, 0);
			Authentication authentication = new UsernamePasswordAuthenticationToken(accountPrincipal, null);
			
			mockMvc.perform(
					multipart("/photo/" + photoAccountId + "/save")
					.file(multipartFile)
					.contentType(MediaType.MULTIPART_FORM_DATA)
					.param("accountNo", "1")
					.param("imageFilePath", "")
					.with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
					.with(csrf())
				)
				.andExpect(status().isForbidden())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.httpStatus").value(HttpStatus.FORBIDDEN.value()))
				.andExpect(jsonPath("$.errorCode").value(ErrorValues.EP0009.getErrorCode()))
				.andExpect(jsonPath("$.errorMessage").value(ErrorValues.EP0009.getErrorMessage()))
				.andExpect(jsonPath("$.goBackPageUrl").value("/photo/aaaaaaaa/photo_list"));
		}
		
		@Test
		@Order(5)
		@DisplayName("異常系：登録上限に達している。PhotoNotAdditableExceptionをthrowする")
		void savePhoto_PhotoNotAdditableException() throws Exception {
			String photoAccountId = "aaaaaaaa";
			MockMultipartFile multipartFile = new MockMultipartFile(
					"imageFile",
					"DSC111.jpg",
					MediaType.MULTIPART_FORM_DATA_VALUE,
					"sample image".getBytes());
			
			Account sessionAccount = Account.builder()
					.accountNo(1)
					.accountId("aaaaaaaa")
					.accountName("AAAAAAAA")
					.password("$2a$10$password1")
					.authorityKbnCode("mini-user")
					.build();
			
			AccountPrincipal accountPrincipal = new AccountPrincipal(sessionAccount, 0);
			Authentication authentication = new UsernamePasswordAuthenticationToken(accountPrincipal, null);
			
			mockMvc.perform(
					multipart("/photo/" + photoAccountId + "/save")
					.file(multipartFile)
					.contentType(MediaType.MULTIPART_FORM_DATA)
					.param("accountNo", "1")
					.param("imageFilePath", "")
					.with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
					.with(csrf())
				)
				.andExpect(status().isBadRequest())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.httpStatus").value(HttpStatus.BAD_REQUEST.value()))
				.andExpect(jsonPath("$.errorCode").value(ErrorValues.EP0011.getErrorCode()))
				.andExpect(jsonPath("$.errorMessage").value(ErrorValues.EP0011.getErrorMessage()))
				.andExpect(jsonPath("$.goBackPageUrl").value("/photo/aaaaaaaa/photo_list"));
		}
		
		@Test
		@Order(6)
		@DisplayName("異常系：画像ファイル、ファイルパスともにnull。BadRequestExceptionをthrowする")
		void savePhoto_BadRequestException_file_and_filepath_is_null() throws Exception {
			String photoAccountId = "bbbbbbbb";
			
			Account sessionAccount = Account.builder()
					.accountNo(2)
					.accountId("bbbbbbbb")
					.accountName("BBBBBBBB")
					.password("$2a$10$password2")
					.authorityKbnCode("mini-user")
					.build();
			
			AccountPrincipal accountPrincipal = new AccountPrincipal(sessionAccount, 0);
			Authentication authentication = new UsernamePasswordAuthenticationToken(accountPrincipal, null);
			
			mockMvc.perform(
					multipart("/photo/" + photoAccountId + "/save")
					.contentType(MediaType.MULTIPART_FORM_DATA)
					.param("accountNo", "2")
					.with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
					.with(csrf())
				)
				.andExpect(status().isBadRequest())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.httpStatus").value(HttpStatus.BAD_REQUEST.value()))
				.andExpect(jsonPath("$.isSuccess").value(false))
				.andExpect(jsonPath("$.message").value(ErrorValues.EC0000.getErrorMessage()));
		}
		
		@Test
		@Order(7)
		@DisplayName("異常系：画像ファイルがnull、ファイルパスがblank。BadRequestExceptionをthrowする")
		void savePhoto_BadRequestException_file_and_filepath_is_blank() throws Exception {
			String photoAccountId = "bbbbbbbb";
			
			Account sessionAccount = Account.builder()
					.accountNo(2)
					.accountId("bbbbbbbb")
					.accountName("BBBBBBBB")
					.password("$2a$10$password2")
					.authorityKbnCode("mini-user")
					.build();
			
			AccountPrincipal accountPrincipal = new AccountPrincipal(sessionAccount, 0);
			Authentication authentication = new UsernamePasswordAuthenticationToken(accountPrincipal, null);
			
			mockMvc.perform(
					multipart("/photo/" + photoAccountId + "/save")
					.contentType(MediaType.MULTIPART_FORM_DATA)
					.param("accountNo", "2")
					.param("imageFilePath", "")
					.with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
					.with(csrf())
				)
				.andExpect(status().isBadRequest())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.httpStatus").value(HttpStatus.BAD_REQUEST.value()))
				.andExpect(jsonPath("$.isSuccess").value(false))
				.andExpect(jsonPath("$.message").value(ErrorValues.EC0000.getErrorMessage()));
		}
		
		@Test
		@Order(8)
		@DisplayName("異常系：画像ファイル、ファイルパス以外のパラメータ不正。BadRequestExceptionをthrowする")
		void savePhoto_BadRequestException_others() throws Exception {
			String photoAccountId = "bbbbbbbb";
			MockMultipartFile multipartFile = new MockMultipartFile(
					"imageFile",
					"DSC111.jpg",
					MediaType.MULTIPART_FORM_DATA_VALUE,
					"sample image".getBytes());
			
			Account sessionAccount = Account.builder()
					.accountNo(2)
					.accountId("bbbbbbbb")
					.accountName("BBBBBBBB")
					.password("$2a$10$password2")
					.authorityKbnCode("administrator")
					.build();
			
			AccountPrincipal accountPrincipal = new AccountPrincipal(sessionAccount, 0);
			Authentication authentication = new UsernamePasswordAuthenticationToken(accountPrincipal, null);
			
			mockMvc.perform(
					multipart("/photo/" + photoAccountId + "/save")
					.file(multipartFile)
					.contentType(MediaType.MULTIPART_FORM_DATA)
					.param("accountNo", "")
					.param("imageFilePath", "")
					.with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
					.with(csrf())
				)
				.andExpect(status().isBadRequest())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.httpStatus").value(HttpStatus.BAD_REQUEST.value()))
				.andExpect(jsonPath("$.isSuccess").value(false))
				.andExpect(jsonPath("$.message").value(ErrorValues.EC0000.getErrorMessage()));
		}
		
		@Test
		@Order(9)
		@DisplayName("異常系：FileDuplicateExceptionをthrowする")
		void savePhoto_FileDuplicateException() throws Exception {
			String photoAccountId = "bbbbbbbb";
			MockMultipartFile multipartFile = new MockMultipartFile(
					"imageFile",
					"DSC21.jpg",
					MediaType.MULTIPART_FORM_DATA_VALUE,
					"sample image".getBytes());
			
			Account sessionAccount = Account.builder()
					.accountNo(2)
					.accountId("bbbbbbbb")
					.accountName("BBBBBBBB")
					.password("$2a$10$password2")
					.authorityKbnCode("administrator")
					.build();
			
			AccountPrincipal accountPrincipal = new AccountPrincipal(sessionAccount, 0);
			Authentication authentication = new UsernamePasswordAuthenticationToken(accountPrincipal, null);
			
			mockMvc.perform(
					multipart("/photo/" + photoAccountId + "/save")
					.file(multipartFile)
					.contentType(MediaType.MULTIPART_FORM_DATA)
					.param("accountNo", "2")
					.param("caption", "")
					.param("imageFilePath", "")
					.param("directionKbnCode", "vertical")
					.param("photoEnglishTitle", "")
					.param("photoJapaneseTitle", "")
					.with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
					.with(csrf())
				)
				.andExpect(status().isConflict())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.httpStatus").value(HttpStatus.CONFLICT.value()))
				.andExpect(jsonPath("$.errorCode").value(ErrorValues.EP0008.getErrorCode()))
				.andExpect(jsonPath("$.errorMessage").value(ErrorValues.EP0008.getErrorMessage()))
				.andExpect(jsonPath("$.goBackPageUrl").value("/photo/bbbbbbbb/photo_list"));
		}
		
		@Test
		@Order(10)
		@DisplayName("異常系：UpdateFailureExceptionをthrowする")
		void savePhoto_UpdateFailureException() throws Exception {
			String photoAccountId = "bbbbbbbb";
			
			Account sessionAccount = Account.builder()
					.accountNo(2)
					.accountId("bbbbbbbb")
					.accountName("BBBBBBBB")
					.password("$2a$10$password2")
					.authorityKbnCode("administrator")
					.build();
			
			AccountPrincipal accountPrincipal = new AccountPrincipal(sessionAccount, 0);
			Authentication authentication = new UsernamePasswordAuthenticationToken(accountPrincipal, null);
			
			mockMvc.perform(
					multipart("/photo/" + photoAccountId + "/save")
					.contentType(MediaType.MULTIPART_FORM_DATA)
					.param("accountNo", "2")
					.param("photoNo", "99")
					.param("caption", "caption21")
					.param("imageFilePath", "https://www.xxx.com/DSC99.jpg")
					.param("directionKbnCode", "vertical")
					.param("photoEnglishTitle", "")
					.param("photoJapaneseTitle", "")
					.with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
					.with(csrf())
				)
				.andExpect(status().isConflict())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.httpStatus").value(HttpStatus.CONFLICT.value()))
				.andExpect(jsonPath("$.errorCode").value(ErrorValues.EP0002.getErrorCode()))
				.andExpect(jsonPath("$.errorMessage").value(ErrorValues.EP0002.getErrorMessage()))
				.andExpect(jsonPath("$.goBackPageUrl").value("/photo/bbbbbbbb/photo_list"));
		}
	}
	
	@Nested
	@Order(3)
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	@Sql("/sql/controller/PhotoRestControllerIntegrationTest.sql")
	class deletePhoto {
		@Test
		@Order(1)
		@DisplayName("正常系")
		void deletePhoto_success() throws JsonProcessingException, Exception {
			String photoAccountId = "aaaaaaaa";
			String loginAccountId = "aaaaaaaa";
			String imageFilePath = "https://www.xxx.com/aaaaaaaa/DSC11.jpg";
			
			PhotoDeleteRequest request = new PhotoDeleteRequest();
			request.setAccountNo(1);
			request.setPhotoNo(1);
			request.setImageFilePath(imageFilePath);
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			Account sessionAccount = Account.builder()
					.accountNo(1)
					.accountId(loginAccountId)
					.accountName("AAAAAAAA")
					.password("$2a$10$password1")
					.authorityKbnCode("administrator")
					.build();
			
			AccountPrincipal accountPrincipal = new AccountPrincipal(sessionAccount, 0);
			Authentication authentication = new UsernamePasswordAuthenticationToken(accountPrincipal, null);
			
			mockMvc.perform(
					post("/photo/" + photoAccountId + "/delete")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(request))
					.with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
					.with(csrf())
				)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.httpStatus").value(200))
				.andExpect(jsonPath("$.isSuccess").value(true))
				.andExpect(jsonPath("$.message").value("写真削除が完了しました。"));
			
			// photo_mst削除チェック
			List<PhotoMst> actualPhotoMst = jdbcTemplate.query(
					"SELECT * FROM photo.photo_mst where account_no = 1 and photo_no=1", (rs, rowNum) ->
					PhotoMst.builder()
						.accountNo(rs.getInt("account_no"))
						.photoNo(rs.getInt("photo_no"))
						.createdBy(rs.getInt("created_by"))
						.createdAt(rs.getObject("created_at", OffsetDateTime.class))
						.updatedBy(rs.getInt("updated_by"))
						.updatedAt(rs.getObject("updated_at", OffsetDateTime.class))
						.isDeleted(rs.getBoolean("is_deleted"))
						.photoAt(rs.getObject("photo_at", OffsetDateTime.class))
						.locationNo(rs.getInt("location_no"))
						.imageFilePath(rs.getString("image_file_path"))
						.photoJapaneseTitle(rs.getString("photo_japanese_title"))
						.photoEnglishTitle(rs.getString("photo_english_title"))
						.caption(rs.getString("caption"))
						.directionKbnCode(rs.getString("direction_kbn_code"))
						.focalLength(rs.getInt("focal_length"))
						.fValue(rs.getBigDecimal("f_value"))
						.shutterSpeed(rs.getBigDecimal("shutter_speed"))
						.iso(rs.getInt("iso"))
						.build());
			assertTrue(actualPhotoMst.getFirst().getIsDeleted());
			
			// photo_tag_mst削除チェック
			List<PhotoTagMst> actualPhotoTagMst = jdbcTemplate.query(
						"SELECT * FROM photo.photo_tag_mst WHERE account_no=1 and photo_no=1", (rs, rowNum) ->
							PhotoTagMst.builder()
								.accountNo(rs.getInt("account_no"))
								.photoNo(rs.getInt("photo_no"))
								.tagNo(rs.getInt("tag_no"))
								.createdBy(rs.getInt("created_by"))
								.createdAt(rs.getObject("created_at", OffsetDateTime.class))
								.tagJapaneseName(rs.getObject("tag_japanese_name").toString())
								.tagEnglishName(rs.getObject("tag_english_name").toString())
								.build());
			assertEquals(0, actualPhotoTagMst.size());
			
			// photo_favorite削除チェック
			List<PhotoFavorite> actualPhotoFavoriteData = jdbcTemplate.query(
					"SELECT * FROM photo.photo_favorite WHERE favorite_photo_account_no=1 and favorite_photo_no=1", (rs, rowNum) ->
						PhotoFavorite.builder()
							.accountNo(rs.getInt("account_no"))
							.favoritePhotoAccountNo(rs.getInt("favorite_photo_account_no"))
							.favoritePhotoNo(rs.getInt("favorite_photo_no"))
							.createdBy(rs.getInt("created_by"))
							.createdAt(rs.getObject("created_at", OffsetDateTime.class))
							.build());
			assertEquals(0, actualPhotoFavoriteData.size());
		}
		
		@Test
		@Order(2)
		@DisplayName("異常系：不正アクセス。ForbiddenAccountExceptionをthrowする")
		void deletePhoto_ForbiddenAccountException() throws JsonProcessingException, Exception {
			String photoAccountId = "aaaaaaaa";
			String loginAccountId = "eeeeeeee";
			String imageFilePath = "https://www.xxx.com/aaaaaaaa/DSC11.jpg";
			
			PhotoDeleteRequest request = new PhotoDeleteRequest();
			request.setAccountNo(1);
			request.setPhotoNo(1);
			request.setImageFilePath(imageFilePath);
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			Account sessionAccount = Account.builder()
					.accountNo(1)
					.accountId(loginAccountId)
					.accountName("EEEEEEEE")
					.password("$2a$10$password5")
					.authorityKbnCode("administrator")
					.build();
			
			AccountPrincipal accountPrincipal = new AccountPrincipal(sessionAccount, 0);
			Authentication authentication = new UsernamePasswordAuthenticationToken(accountPrincipal, null);
			
			mockMvc.perform(
					post("/photo/" + photoAccountId + "/delete")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(request))
					.with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
					.with(csrf())
				)
				.andExpect(status().isForbidden())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.httpStatus").value(HttpStatus.FORBIDDEN.value()))
				.andExpect(jsonPath("$.errorCode").value(ErrorValues.EP0009.getErrorCode()))
				.andExpect(jsonPath("$.errorMessage").value(ErrorValues.EP0009.getErrorMessage()))
				.andExpect(jsonPath("$.goBackPageUrl").value("/photo/" + loginAccountId + "/photo_list"));
			
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：パラメータ不正。BadRequestExceptionをthrowする")
		void deletePhoto_BadRequestException() throws JsonProcessingException, Exception {
			String photoAccountId = "aaaaaaaa";
			String loginAccountId = "aaaaaaaa";
			
			PhotoDeleteRequest request = new PhotoDeleteRequest();
			request.setAccountNo(1);
			request.setPhotoNo(1);
			request.setImageFilePath("");
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			Account sessionAccount = Account.builder()
					.accountNo(1)
					.accountId(loginAccountId)
					.accountName("AAAAAAAA")
					.password("$2a$10$password1")
					.authorityKbnCode("administrator")
					.build();
			
			AccountPrincipal accountPrincipal = new AccountPrincipal(sessionAccount, 0);
			Authentication authentication = new UsernamePasswordAuthenticationToken(accountPrincipal, null);
			
			mockMvc.perform(
					post("/photo/" + photoAccountId + "/delete")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(request))
					.with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
					.with(csrf())
				)
				.andExpect(status().isBadRequest())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.httpStatus").value(HttpStatus.BAD_REQUEST.value()))
				.andExpect(jsonPath("$.isSuccess").value(false))
				.andExpect(jsonPath("$.message").value(ErrorValues.EC0000.getErrorMessage()));
		}
		
		@Test
		@Order(4)
		@DisplayName("異常系：UpdateFailureExceptionをthrowする")
		void deletePhoto_UpdateFailureException() throws JsonProcessingException, Exception {
			String photoAccountId = "aaaaaaaa";
			String loginAccountId = "aaaaaaaa";
			String imageFilePath = "https://www.xxx.com/aaaaaaaa/DSC11.jpg";
			
			PhotoDeleteRequest request = new PhotoDeleteRequest();
			request.setAccountNo(1);
			request.setPhotoNo(99);
			request.setImageFilePath(imageFilePath);
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			Account sessionAccount = Account.builder()
					.accountNo(1)
					.accountId(loginAccountId)
					.accountName("AAAAAAAA")
					.password("$2a$10$password1")
					.authorityKbnCode("administrator")
					.build();
			
			AccountPrincipal accountPrincipal = new AccountPrincipal(sessionAccount, 0);
			Authentication authentication = new UsernamePasswordAuthenticationToken(accountPrincipal, null);
			
			mockMvc.perform(
					post("/photo/" + photoAccountId + "/delete")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(request))
					.with(SecurityMockMvcRequestPostProcessors.authentication(authentication))
					.with(csrf())
				)
				.andExpect(status().isConflict())
				.andExpect(jsonPath("$.httpStatus").value(HttpStatus.CONFLICT.value()))
				.andExpect(jsonPath("$.errorCode").value(ErrorValues.EP0003.getErrorCode()))
				.andExpect(jsonPath("$.errorMessage").value(ErrorValues.EP0003.getErrorMessage()))
				.andExpect(jsonPath("$.goBackPageUrl").value("/photo/" + loginAccountId + "/photo_list"));
		}
	}
}