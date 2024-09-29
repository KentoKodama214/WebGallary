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
public class PhotoServiceImplTest {
	
	
	@Nested
	@Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class getPhotoList {
		@Test
		@Order(1)
		@DisplayName("正常系：写真が存在しなかった場合")
		void getPhotoList_not_found() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：写真が存在した場合で、撮影日順に並び替え")
		void getPhotoList_sortBy_photoAt() {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：写真が存在した場合で、お気に入り数順に並び替え")
		void getPhotoList_sortBy_Favorite() {
			assertTrue(false);
		}
		
		@Test
		@Order(4)
		@DisplayName("正常系：写真が存在した場合で、季節・時期順に並び替え")
		void getPhotoList_sortBy_season() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(2)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class getPhotoDetail {
		@Test
		@Order(1)
		@DisplayName("正常系")
		void getPhotoDetail_success() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("異常系：PhotoNotFoundExceptionをthrowする")
		void getPhotoDetail_PhotoNotFoundException() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(3)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class savePhotos {
		@Test
		@Order(1)
		@DisplayName("正常系：photoDetailModelListがnullの場合、終了")
		void savePhotos_photoDetailModelList_is_null() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：photoDetailModelListがemptyの場合、終了")
		void savePhotos_photoDetailModelList_is_empty() {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：新規登録のみ")
		void savePhotos_newPhoto() {
			assertTrue(false);
		}
		
		@Test
		@Order(4)
		@DisplayName("正常系：更新のみ")
		void savePhotos_updatePhoto() {
			assertTrue(false);
		}
		
		@Test
		@Order(5)
		@DisplayName("正常系：新規登録＋更新")
		void savePhotos_newPhoto_and_updatePhoto() {
			assertTrue(false);
		}
		
		@Test
		@Order(6)
		@DisplayName("異常系：FileDuplicateExceptionをthrowする（写真は複数枚）")
		void savePhotos_FileDuplicateException() {
			assertTrue(false);
		}
		
		@Test
		@Order(7)
		@DisplayName("異常系：写真登録でRegistFailureExceptionをthrowする（写真は複数枚）")
		void savePhotos_registPhoto_RegistFailureException() {
			assertTrue(false);
		}
		
		@Test
		@Order(8)
		@DisplayName("異常系：新規登録時、写真タグ登録でRegistFailureExceptionをthrowする（写真は複数枚）")
		void savePhotos_newPhoto_registPhotoTag_RegistFailureException() {
			assertTrue(false);
		}
		
		@Test
		@Order(9)
		@DisplayName("異常系：更新時、写真タグ登録でRegistFailureExceptionをthrowする（写真は複数枚）")
		void savePhotos_updatePhoto_registPhotoTag_RegistFailureException() {
			assertTrue(false);
		}
		
		@Test
		@Order(10)
		@DisplayName("異常系：UpdateFailureExceptionをthrowする（写真は複数枚）")
		void savePhotos_UpdateFailureException() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(4)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class deletePhotos {
		@Test
		@Order(1)
		@DisplayName("正常系：photoDeleteModelListが0件の場合、終了")
		void deletePhotos_photoDeleteModelList_empty() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：photoDetailModelListが2件以上の場合")
		void deletePhotos_success() {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("異常系：UpdateFailureExceptionをthrowする")
		void deletePhotos_UpdateFailureException() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(5)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class isReachedUpperLimit {
		@Test
		@Order(1)
		@DisplayName("正常系：アカウント番号がnullの場合")
		void isReachedUpperLimit_accountNo_is_null() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：mini-userで、10枚登録済みの場合")
		void isReachedUpperLimit_mini_user_10_photos() {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：mini-userで、9枚登録済みの場合")
		void isReachedUpperLimit_mini_user_9_photos() {
			assertTrue(false);
		}
		
		@Test
		@Order(4)
		@DisplayName("正常系：normal-userで、1000枚登録済みの場合")
		void isReachedUpperLimit_normal_user_1000_photos() {
			assertTrue(false);
		}
		
		@Test
		@Order(5)
		@DisplayName("正常系：normal-userで、999枚登録済みの場合")
		void isReachedUpperLimit_normal_user_999_photos() {
			assertTrue(false);
		}
		
		@Test
		@Order(6)
		@DisplayName("正常系：special-userの場合")
		void isReachedUpperLimit_special_user() {
			assertTrue(false);
		}
		
		@Test
		@Order(7)
		@DisplayName("正常系：administratorの場合")
		void isReachedUpperLimit_administrator() {
			assertTrue(false);
		}
		
		@Test
		@Order(8)
		@DisplayName("正常系：それ以外の場合")
		void isReachedUpperLimit_others() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(6)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class getComparator {
		@Test
		@Order(1)
		@DisplayName("正常系：photoAtの場合")
		void getComparator_photoAt() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：favoriteの場合")
		void getComparator_favorite() {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：seasonの場合")
		void getComparator_season() {
			assertTrue(false);
		}
		
		@Test
		@Order(4)
		@DisplayName("正常系：それ以外の場合")
		void getComparator_others() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(7)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class filteringByDirectionKbnCode {
		@Test
		@Order(1)
		@DisplayName("正常系：抽出条件が未指定の場合")
		void filteringByDirectionKbnCode_not_condition() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：抽出条件が指定されていて、条件と一致の場合")
		void filteringByDirectionKbnCode_match_condition() {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：抽出条件が指定されていて、条件と不一致の場合")
		void filteringByDirectionKbnCode_mismatch_condition() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(8)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class filteringByIsFavorite {
		@Test
		@Order(1)
		@DisplayName("正常系：お気に入りのみが未指定の場合")
		void filteringByIsFavorite_not_condition() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：お気に入りのみが指定されていて、写真がお気に入りの場合")
		void filteringByIsFavorite_match_condition() {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：お気に入りのみが指定されていて、写真がお気に入りでない場合")
		void filteringByIsFavorite_mismatch_condition() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(9)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class filteringByTag {
		@Test
		@Order(1)
		@DisplayName("正常系：tagsが0件の場合")
		void filteringByTag_tags_empty() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：tagsの1件目が''の場合")
		void filteringByTag_tags_blank() {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：tagsのすべてが含まれる場合")
		void filteringByTag_contain_all_tag() {
			assertTrue(false);
		}
		
		@Test
		@Order(4)
		@DisplayName("正常系：tagsのすべてが含まれない場合")
		void filteringByTag_not_contain_all_tag() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(10)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class registPhotoTags {
		@Test
		@Order(1)
		@DisplayName("正常系：photoTagModelListがnullの場合")
		void registPhotoTags_photoTagModelList_is_null() {
			assertTrue(false);
		}
		
		@Test
		@Order(2)
		@DisplayName("正常系：newPhotoNoがnullの場合")
		void registPhotoTags_newPhotoNo_is_null() {
			assertTrue(false);
		}
		
		@Test
		@Order(3)
		@DisplayName("正常系：newPhotoNoがnullでない場合")
		void registPhotoTags_newPhotoNo_is_not_null() {
			assertTrue(false);
		}
		
		@Test
		@Order(4)
		@DisplayName("異常系：RegistFailureExceptionをthrowする")
		void registPhotoTags_RegistFailureException() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(11)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class uploadFile {
		@Test
		@Order(1)
		@DisplayName("正常系")
		void uploadFile_success() {
			assertTrue(false);
		}
	}
	
	@Nested
	@Order(12)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class deletePhotoTags {
		@Test
		@Order(1)
		@DisplayName("正常系")
		void deletePhotoTags_success() {
			assertTrue(false);
		}
	}
}