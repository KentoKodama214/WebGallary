package com.web.gallary.mapper;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.web.gallary.entity.PhotoTagMst;

@MybatisTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PhotoTagMstMapperTest {
	@Autowired
	private PhotoTagMstMapper photoTagMstMapper;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Nested
	@Order(1)
	@Sql("/sql/PhotoTagMstMapperTest.sql")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class select {
		@Test
		@Order(1)
		@DisplayName("正常系：アカウント番号でのselectで1件以上の場合")
		void select_by_accountNo() {
			PhotoTagMst photoTagMst = PhotoTagMst.builder().accountNo(1).build();
			List<PhotoTagMst> selectedPhotoTagMstList = photoTagMstMapper.select(photoTagMst);
			
			PhotoTagMst expectedPhotoTagMst1 = PhotoTagMst.builder()
					.accountNo(1)
					.photoNo(1)
					.tagNo(1)
					.createdBy(1)
					.createdAt(OffsetDateTime.of(2000, 1, 1, 1, 0, 0, 0, ZoneOffset.ofHours(0)))
					.tagJapaneseName("太陽")
					.tagEnglishName("sun")
					.build();
			PhotoTagMst expectedPhotoTagMst2 = PhotoTagMst.builder()
					.accountNo(1)
					.photoNo(1)
					.tagNo(2)
					.createdBy(1)
					.createdAt(OffsetDateTime.of(2000, 1, 1, 2, 0, 0, 0, ZoneOffset.ofHours(0)))
					.tagJapaneseName("青空")
					.tagEnglishName("bluesky")
					.build();
			PhotoTagMst expectedPhotoTagMst3 = PhotoTagMst.builder()
					.accountNo(1)
					.photoNo(2)
					.tagNo(1)
					.createdBy(1)
					.createdAt(OffsetDateTime.of(2000, 2, 1, 1, 0, 0, 0, ZoneOffset.ofHours(0)))
					.tagJapaneseName("太陽")
					.tagEnglishName("sun")
					.build();
			PhotoTagMst expectedPhotoTagMst4 = PhotoTagMst.builder()
					.accountNo(1)
					.photoNo(2)
					.tagNo(2)
					.createdBy(1)
					.createdAt(OffsetDateTime.of(2000, 2, 1, 2, 0, 0, 0, ZoneOffset.ofHours(0)))
					.tagJapaneseName("曇天")
					.tagEnglishName("cloudy")
					.build();
			PhotoTagMst expectedPhotoTagMst5 = PhotoTagMst.builder()
					.accountNo(1)
					.photoNo(2)
					.tagNo(3)
					.createdBy(1)
					.createdAt(OffsetDateTime.of(2000, 2, 1, 3, 0, 0, 0, ZoneOffset.ofHours(0)))
					.tagJapaneseName("花")
					.tagEnglishName("flower")
					.build();
			List<PhotoTagMst> expectedPhotoTagMstList = new ArrayList<PhotoTagMst>();
			expectedPhotoTagMstList.add(expectedPhotoTagMst1);
			expectedPhotoTagMstList.add(expectedPhotoTagMst2);
			expectedPhotoTagMstList.add(expectedPhotoTagMst3);
			expectedPhotoTagMstList.add(expectedPhotoTagMst4);
			expectedPhotoTagMstList.add(expectedPhotoTagMst5);
			
			assertEquals(selectedPhotoTagMstList.size(), 5);
			assertEquals(selectedPhotoTagMstList.stream().sorted(Comparator.comparing(PhotoTagMst::getCreatedAt)).toList(),
					expectedPhotoTagMstList.stream().sorted(Comparator.comparing(PhotoTagMst::getCreatedAt)).toList());
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：写真番号でのselectで1件の場合")
		void select_by_photoNo() {
			PhotoTagMst photoTagMst = PhotoTagMst.builder().photoNo(1).build();
			List<PhotoTagMst> selectedPhotoTagMstList = photoTagMstMapper.select(photoTagMst);
			
			PhotoTagMst expectedPhotoTagMst1 = PhotoTagMst.builder()
					.accountNo(1)
					.photoNo(1)
					.tagNo(1)
					.createdBy(1)
					.createdAt(OffsetDateTime.of(2000, 1, 1, 1, 0, 0, 0, ZoneOffset.ofHours(0)))
					.tagJapaneseName("太陽")
					.tagEnglishName("sun")
					.build();
			PhotoTagMst expectedPhotoTagMst2 = PhotoTagMst.builder()
					.accountNo(1)
					.photoNo(1)
					.tagNo(2)
					.createdBy(1)
					.createdAt(OffsetDateTime.of(2000, 1, 1, 2, 0, 0, 0, ZoneOffset.ofHours(0)))
					.tagJapaneseName("青空")
					.tagEnglishName("bluesky")
					.build();
			List<PhotoTagMst> expectedPhotoTagMstList = new ArrayList<PhotoTagMst>();
			expectedPhotoTagMstList.add(expectedPhotoTagMst1);
			expectedPhotoTagMstList.add(expectedPhotoTagMst2);
			
			assertEquals(selectedPhotoTagMstList.size(), 2);
			assertEquals(selectedPhotoTagMstList.stream().sorted(Comparator.comparing(PhotoTagMst::getCreatedAt)).toList(),
					expectedPhotoTagMstList.stream().sorted(Comparator.comparing(PhotoTagMst::getCreatedAt)).toList());
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：タグ番号でのselectで1件の場合")
		void select_by_tagNo() {
			PhotoTagMst photoTagMst = PhotoTagMst.builder().tagNo(1).build();
			List<PhotoTagMst> selectedPhotoTagMstList = photoTagMstMapper.select(photoTagMst);
			
			PhotoTagMst expectedPhotoTagMst1 = PhotoTagMst.builder()
					.accountNo(1)
					.photoNo(1)
					.tagNo(1)
					.createdBy(1)
					.createdAt(OffsetDateTime.of(2000, 1, 1, 1, 0, 0, 0, ZoneOffset.ofHours(0)))
					.tagJapaneseName("太陽")
					.tagEnglishName("sun")
					.build();
			PhotoTagMst expectedPhotoTagMst2 = PhotoTagMst.builder()
					.accountNo(1)
					.photoNo(2)
					.tagNo(1)
					.createdBy(1)
					.createdAt(OffsetDateTime.of(2000, 2, 1, 1, 0, 0, 0, ZoneOffset.ofHours(0)))
					.tagJapaneseName("太陽")
					.tagEnglishName("sun")
					.build();
			List<PhotoTagMst> expectedPhotoTagMstList = new ArrayList<PhotoTagMst>();
			expectedPhotoTagMstList.add(expectedPhotoTagMst1);
			expectedPhotoTagMstList.add(expectedPhotoTagMst2);
			
			assertEquals(selectedPhotoTagMstList.size(), 2);
			assertEquals(selectedPhotoTagMstList.stream().sorted(Comparator.comparing(PhotoTagMst::getCreatedAt)).toList(),
					expectedPhotoTagMstList.stream().sorted(Comparator.comparing(PhotoTagMst::getCreatedAt)).toList());
		}
		
		@Test
		@Order(4)
		@DisplayName("正常系：タグ日本語名でのselectで1件の場合")
		void select_by_tagJapaneseName() {
			PhotoTagMst photoTagMst = PhotoTagMst.builder().tagJapaneseName("太陽").build();
			List<PhotoTagMst> selectedPhotoTagMstList = photoTagMstMapper.select(photoTagMst);
			
			PhotoTagMst expectedPhotoTagMst1 = PhotoTagMst.builder()
					.accountNo(1)
					.photoNo(1)
					.tagNo(1)
					.createdBy(1)
					.createdAt(OffsetDateTime.of(2000, 1, 1, 1, 0, 0, 0, ZoneOffset.ofHours(0)))
					.tagJapaneseName("太陽")
					.tagEnglishName("sun")
					.build();
			PhotoTagMst expectedPhotoTagMst2 = PhotoTagMst.builder()
					.accountNo(1)
					.photoNo(2)
					.tagNo(1)
					.createdBy(1)
					.createdAt(OffsetDateTime.of(2000, 2, 1, 1, 0, 0, 0, ZoneOffset.ofHours(0)))
					.tagJapaneseName("太陽")
					.tagEnglishName("sun")
					.build();
			List<PhotoTagMst> expectedPhotoTagMstList = new ArrayList<PhotoTagMst>();
			expectedPhotoTagMstList.add(expectedPhotoTagMst1);
			expectedPhotoTagMstList.add(expectedPhotoTagMst2);
			
			assertEquals(selectedPhotoTagMstList.size(), 2);
			assertEquals(selectedPhotoTagMstList.stream().sorted(Comparator.comparing(PhotoTagMst::getCreatedAt)).toList(),
					expectedPhotoTagMstList.stream().sorted(Comparator.comparing(PhotoTagMst::getCreatedAt)).toList());
		}
		
		@Test
		@Order(5)
		@DisplayName("正常系：タグ英語名でのselectで1件の場合")
		void select_by_tagEnglishName() {
			PhotoTagMst photoTagMst = PhotoTagMst.builder().tagEnglishName("sun").build();
			List<PhotoTagMst> selectedPhotoTagMstList = photoTagMstMapper.select(photoTagMst);
			
			PhotoTagMst expectedPhotoTagMst1 = PhotoTagMst.builder()
					.accountNo(1)
					.photoNo(1)
					.tagNo(1)
					.createdBy(1)
					.createdAt(OffsetDateTime.of(2000, 1, 1, 1, 0, 0, 0, ZoneOffset.ofHours(0)))
					.tagJapaneseName("太陽")
					.tagEnglishName("sun")
					.build();
			PhotoTagMst expectedPhotoTagMst2 = PhotoTagMst.builder()
					.accountNo(1)
					.photoNo(2)
					.tagNo(1)
					.createdBy(1)
					.createdAt(OffsetDateTime.of(2000, 2, 1, 1, 0, 0, 0, ZoneOffset.ofHours(0)))
					.tagJapaneseName("太陽")
					.tagEnglishName("sun")
					.build();
			List<PhotoTagMst> expectedPhotoTagMstList = new ArrayList<PhotoTagMst>();
			expectedPhotoTagMstList.add(expectedPhotoTagMst1);
			expectedPhotoTagMstList.add(expectedPhotoTagMst2);
			
			assertEquals(selectedPhotoTagMstList.size(), 2);
			assertEquals(selectedPhotoTagMstList.stream().sorted(Comparator.comparing(PhotoTagMst::getCreatedAt)).toList(),
					expectedPhotoTagMstList.stream().sorted(Comparator.comparing(PhotoTagMst::getCreatedAt)).toList());
		}
		
		@Test
		@Order(6)
		@DisplayName("正常系：selectで0件の場合")
		void select_not_found() {
			PhotoTagMst photoTagMst = PhotoTagMst.builder().accountNo(3).build();
			List<PhotoTagMst> selectedPhotoTagMstList = photoTagMstMapper.select(photoTagMst);
			List<PhotoTagMst> expectedPhotoTagMstList = new ArrayList<PhotoTagMst>();
			assertEquals(selectedPhotoTagMstList.size(), 0);
			assertEquals(selectedPhotoTagMstList, expectedPhotoTagMstList);
		}
		
		@Test
		@Order(7)
		@DisplayName("正常系：複数の条件でselectする場合")
		void select_some_conditions() {
			PhotoTagMst photoTagMst = PhotoTagMst.builder()
					.accountNo(1)
					.photoNo(1)
					.tagNo(1)
					.build();
			List<PhotoTagMst> selectedPhotoTagMstList = photoTagMstMapper.select(photoTagMst);
			
			PhotoTagMst expectedPhotoTagMst1 = PhotoTagMst.builder()
					.accountNo(1)
					.photoNo(1)
					.tagNo(1)
					.createdBy(1)
					.createdAt(OffsetDateTime.of(2000, 1, 1, 1, 0, 0, 0, ZoneOffset.ofHours(0)))
					.tagJapaneseName("太陽")
					.tagEnglishName("sun")
					.build();
			List<PhotoTagMst> expectedPhotoTagMstList = new ArrayList<PhotoTagMst>();
			expectedPhotoTagMstList.add(expectedPhotoTagMst1);
			
			assertEquals(selectedPhotoTagMstList.size(), 1);
			assertEquals(selectedPhotoTagMstList, expectedPhotoTagMstList);
		}
	}
	
	@Nested
	@Order(2)
	@Sql("/sql/PhotoTagMstMapperTest.sql")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class insert {
		@Test
		@Order(1)
		@DisplayName("正常系：登録成功")
		void insert_success() {
			PhotoTagMst photoTagMst = PhotoTagMst.builder()
					.accountNo(1)
					.photoNo(1)
					.tagNo(3)
					.createdBy(1)
					.tagJapaneseName("春")
					.tagEnglishName("spring")
					.build();
			
			Integer insertCount = photoTagMstMapper.insert(photoTagMst);
			assertThat(insertCount).isEqualTo(1);
			
			List<PhotoTagMst> expectedPhotoTagMstList = jdbcTemplate.query(
					"SELECT * FROM photo.photo_tag_mst WHERE account_no=1 and photo_no=1 and tag_no=1", (rs, rowNum) ->
						PhotoTagMst.builder()
							.accountNo(rs.getInt("account_no"))
							.photoNo(rs.getInt("photo_no"))
							.tagNo(rs.getInt("tag_no"))
							.createdBy(rs.getInt("created_by"))
							.createdAt(rs.getObject("created_at", OffsetDateTime.class))
							.tagJapaneseName("春")
							.tagEnglishName("spring")
							.build());
			assertEquals(1, expectedPhotoTagMstList.size());
		}
	}
	
	@Nested
	@Order(3)
	@Sql("/sql/PhotoTagMstMapperTest.sql")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class delete {
		@Test
		@Order(1)
		@DisplayName("正常系：アカウント番号でのdelete")
		void delete_by_accountNo() {
			PhotoTagMst deletePhotoTagMst = PhotoTagMst.builder().accountNo(1).build();
			Integer deleteCount = photoTagMstMapper.delete(deletePhotoTagMst);
			assertEquals(deleteCount, 5);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：写真番号でのdelete")
		void delete_by_photoNo() {
			PhotoTagMst deletePhotoTagMst = PhotoTagMst.builder().photoNo(1).build();
			Integer deleteCount = photoTagMstMapper.delete(deletePhotoTagMst);
			assertEquals(deleteCount, 2);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：タグ番号でのdelete")
		void delete_by_tagNo() {
			PhotoTagMst deletePhotoTagMst = PhotoTagMst.builder().tagNo(1).build();
			Integer deleteCount = photoTagMstMapper.delete(deletePhotoTagMst);
			assertEquals(deleteCount, 2);
		}
		
		@Test
		@Order(4)
		@DisplayName("正常系：タグ日本語名でのdelete")
		void delete_by_tagJapaneseName() {
			PhotoTagMst deletePhotoTagMst = PhotoTagMst.builder().tagJapaneseName("太陽").build();
			Integer deleteCount = photoTagMstMapper.delete(deletePhotoTagMst);
			assertEquals(deleteCount, 2);
		}
		
		@Test
		@Order(5)
		@DisplayName("正常系：タグ英語名でのdelete")
		void delete_by_tagEnglishName() {
			PhotoTagMst deletePhotoTagMst = PhotoTagMst.builder().tagEnglishName("sun").build();
			Integer deleteCount = photoTagMstMapper.delete(deletePhotoTagMst);
			assertEquals(deleteCount, 2);
		}
		
		@Test
		@Order(6)
		@DisplayName("正常系：deleteで0件の場合")
		void delete_not_found() {
			PhotoTagMst deletePhotoTagMst = PhotoTagMst.builder().accountNo(3).build();
			Integer deleteCount = photoTagMstMapper.delete(deletePhotoTagMst);
			assertEquals(deleteCount, 0);
		}
		
		@Test
		@Order(7)
		@DisplayName("正常系：複数の条件でdeleteする場合")
		void delete_some_conditions() {
			PhotoTagMst deletePhotoTagMst = PhotoTagMst.builder()
					.accountNo(1)
					.photoNo(1)
					.tagNo(1)
					.build();
			Integer deleteCount = photoTagMstMapper.delete(deletePhotoTagMst);
			assertEquals(deleteCount, 1);
		}
	}
}