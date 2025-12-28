erDiagram
    ACCOUNT {
        int account_no PK
        int created_by
        timestamptz created_at
        int updated_by
        timestamptz updated_at
        boolean is_deleted
        varchar account_id "unique"
        varchar account_name
        text password
        date birthdate
        varchar sex_kbn_code
        varchar birthplace_prefecture_kbn_code
        varchar resident_prefecture_kbn_code
        text free_memo
        varchar authority_kbn_code
        timestamptz last_login_datetime
        smallint login_failure_count
    }

    KBN_MST {
        varchar kbn_class_code PK
        varchar kbn_code PK
        int created_by
        timestamptz created_at
        int sort_order
        varchar kbn_group_code
        varchar kbn_class_japanese_name
        varchar kbn_group_japanese_name
        varchar kbn_japanese_name
        varchar kbn_class_english_name
        varchar kbn_group_english_name
        varchar kbn_english_name
        text explanation
    }

    PHOTO_MST {
        int account_no PK FK "refs common.account(account_no)"
        int photo_no PK
        int created_by
        timestamptz created_at
        int updated_by
        timestamptz updated_at
        boolean is_deleted
        timestamptz photo_at
        int location_no
        text image_file_path
        varchar photo_japanese_title
        varchar photo_english_title
        text caption
        varchar direction_kbn_code
        int focal_length
        decimal f_value
        decimal shutter_speed
        int iso
    }

    PHOTO_TAG_MST {
        int account_no PK FK "refs photo.photo_mst(account_no,photo_no)"
        int photo_no PK FK "refs photo.photo_mst(account_no,photo_no)"
        int tag_no PK
        int created_by
        timestamptz created_at
        varchar tag_japanese_name
        varchar tag_english_name
    }

    PHOTO_FAVORITE {
        int account_no PK FK "refs common.account(account_no)"
        int favorite_photo_account_no PK FK "refs photo.photo_mst(account_no,photo_no)"
        int favorite_photo_no PK FK "refs photo.photo_mst(account_no,photo_no)"
        int created_by
        timestamptz created_at
    }

    %% Relationships
    ACCOUNT ||--o{ PHOTO_MST : "投稿 (1アカウント 多写真)"
    PHOTO_MST ||--o{ PHOTO_TAG_MST : "タグ (1写真 多タグ)"
    PHOTO_MST ||--o{ PHOTO_FAVORITE : "お気に入り対象 (1写真 多お気に入り)"
    ACCOUNT ||--o{ PHOTO_FAVORITE : "お気に入りした人 (1アカウント 多お気に入り)"

    %% Logical lookups (no FK declared in DDL)
    KBN_MST ..> ACCOUNT : "lookup (sex/authority/residentなど)"
    KBN_MST ..> PHOTO_MST : "lookup (direction_kbn_codeなど)"