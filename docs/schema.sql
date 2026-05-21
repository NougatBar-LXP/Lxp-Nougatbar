CREATE TABLE `instructor_profiles` (
                                       `instructor_profile_id`	BIGINT	NOT NULL	COMMENT 'PK',
                                       `member_id`	BIGINT	NOT NULL	COMMENT 'FK',
                                       `bio`	TEXT	NULL,
                                       `expertise`	VARCHAR(255)	NULL,
                                       `portfolio_url`	VARCHAR(255)	NULL,
                                       `approval_status`	ENUM	NULL	DEFAULT PENDING	COMMENT 'PENDING, APPROVED, REJECTED',
                                       `created_at`	TIMESTAMP	NOT NULL	DEFAULT NOW()	COMMENT '승인 신청 날짜',
                                       `updated_at`	TIMESTAMP	NULL,
                                       `reviewed_at`	TIMESTAMP	NULL
);

CREATE TABLE `courses` (
                           `course_id`	BIGINT	NOT NULL	COMMENT 'PK',
                           `member_id`	BIGINT	NOT NULL	COMMENT 'FK',
                           `title`	VARCHAR(255)	NOT NULL,
                           `description`	TEXT	NULL,
                           `price`	INT	NOT NULL	DEFAULT 0,
                           `level`	ENUM	NOT NULL	DEFAULT EASY	COMMENT 'EASY, NORMAL, HARD',
                           `status`	ENUM	NOT NULL	DEFAULT DRAFT	COMMENT 'DRAFT, PUBLISHED, ARCHIVED',
                           `thumbnail_url`	VARCHAR(255)	NULL,
                           `created_at`	TIMESTAMP	NOT NULL	DEFAULT NOW(),
                           `deleted_at`	TIMESTAMP	NULL	COMMENT 'Soft Delete ( 값이 있으면 탈퇴 )',
                           `approved_at`	TIMESTAMP	NULL
);

CREATE TABLE `community` (
                             `community_id`	BIGINT	NOT NULL	COMMENT 'PK',
                             `course_id`	BIGINT	NOT NULL	COMMENT 'FK',
                             `member_id`	BIGINT	NOT NULL	COMMENT 'FK',
                             `type`	ENUM	NOT NULL	COMMENT 'COURSE, LECTURE, MISSION',
                             `title`	VARCHAR(255)	NOT NULL,
                             `content`	TEXT	NOT NULL	COMMENT '질문 내용 / 미션 답안을 저장',
                             `created_at`	TIMESTAMP	NOT NULL	DEFAULT NOW(),
                             `updated_at`	TIMESTAMP	NULL
);

CREATE TABLE `enrollments` (
                               `enrollment_id`	BIGINT	NOT NULL	COMMENT 'PK',
                               `member_id`	BIGINT	NOT NULL	COMMENT 'FK',
                               `course_id`	BIGINT	NOT NULL	COMMENT 'FK',
                               `status`	boolean	NOT NULL	DEFAULT false	COMMENT 'NOT_COMPLETED, COMPLETED',
                               `progress_rate`	INT	NULL	DEFAULT 0	COMMENT '0~100 사이',
                               `created_at`	TIMESTAMP	NOT NULL	DEFAULT NOW()	COMMENT '수강권 생성 일시'
);

CREATE TABLE `sections` (
                            `section_id`	BIGINT	NOT NULL	COMMENT 'PK',
                            `course_id`	BIGINT	NOT NULL	COMMENT 'FK',
                            `title`	VARCHAR(255)	NOT NULL,
                            `sequence`	INT	NOT NULL,
                            `created_at`	TIMESTAMP	NOT NULL	DEFAULT NOW(),
                            `updated_at`	TIMESTAMP	NULL
);

CREATE TABLE `members` (
                           `member_id`	BIGINT	NOT NULL	COMMENT 'PK',
                           `email`	VARCHAR(255)	NOT NULL	COMMENT 'Unique',
                           `password`	VARCHAR(255)	NOT NULL,
                           `name`	VARCHAR(255)	NOT NULL,
                           `nickname`	VARCHAR(255)	NOT NULL	COMMENT 'Unique',
                           `status`	ENUM	NOT NULL	DEFAULT ACTIVE	COMMENT 'ACTIVE, WITHDRAWN',
                           `role`	ENUM	NOT NULL	COMMENT 'MEMBER, INSTRUCTOR, ADMIN',
                           `created_at`	TIMESTAMP	NOT NULL	DEFAULT NOW()	COMMENT '가입 일시',
                           `updated_at`	TIMESTAMP	NULL	COMMENT '회원정보 변경 일시',
                           `deleted_at`	TIMESTAMP	NULL	COMMENT 'Soft Delete ( 값이 있으면 탈퇴 )'
);

CREATE TABLE `lectures` (
                            `lecture_id`	BIGINT	NOT NULL	COMMENT 'PK',
                            `section_id`	BIGINT	NOT NULL	COMMENT 'FK',
                            `type`	ENUM	NOT NULL	COMMENT 'VIDEO, PDF등',
                            `title`	VARCHAR(255)	NOT NULL,
                            `sequence`	INT	NOT NULL,
                            `video_url`	VARCHAR(255)	NULL,
                            `created_at`	TIMESTAMP	NOT NULL	DEFAULT NOW(),
                            `updated_at`	TIMESTAMP	NULL
);

CREATE TABLE `comments` (
                            `comment_id`	BIGINT	NOT NULL	COMMENT 'PK',
                            `community_id`	BIGINT	NOT NULL	COMMENT 'FK',
                            `member_id`	BIGINT	NOT NULL	COMMENT 'FK',
                            `content`	TEXT	NOT NULL	COMMENT '댓글 본문',
                            `created_at`	TIMESTAMP	NOT NULL,
                            `updated_at`	TIMESTAMP	NULL
);

CREATE TABLE `carts` (
                         `cart_id`	BIGINT	NOT NULL	COMMENT 'PK',
                         `member_id`	BIGINT	NOT NULL	COMMENT 'FK',
                         `course_id`	BIGINT	NOT NULL	COMMENT 'FK'
);

CREATE TABLE `orders` (
                          `order_id`	BIGINT	NOT NULL	COMMENT 'PK',
                          `member_id`	BIGINT	NOT NULL	COMMENT 'FK',
                          `total`	BIGINT	NOT NULL	COMMENT '장바구니 합산 금액',
                          `status`	ENUM	NOT NULL	DEFAULT PENDING	COMMENT 'PENDING, COMPLETED, FAILED',
                          `created_at`	TIMESTAMP	NOT NULL	DEFAULT NOW()	COMMENT '결제 시간'
);

CREATE TABLE `order_lines` (
                               `order_line_id`	BIGINT	NOT NULL	COMMENT 'PK',
                               `course_id`	BIGINT	NOT NULL	COMMENT 'FK',
                               `order_id`	BIGINT	NOT NULL	COMMENT 'FK',
                               `price`	INT	NOT NULL	COMMENT '결제 시점 스냅샷'
);

CREATE TABLE `missions` (
                            `mission_id`	BIGINT	NOT NULL	COMMENT 'PK',
                            `section_id`	BIGINT	NOT NULL	COMMENT 'FK',
                            `title`	varchar(255)	NOT NULL,
                            `contents`	text	NOT NULL,
                            `created_at`	TIMESTAMP	NOT NULL	DEFAULT NOW(),
                            `updated_at`	TIMESTAMP	NULL
);

CREATE TABLE `ref_courses_tags` (
                                    `course_id`	BIGINT	NOT NULL,
                                    `tag_id`	VARCHAR(255)	NOT NULL
);

CREATE TABLE `tags` (
                        `tag_id`	VARCHAR(255)	NOT NULL,
                        `tag_name`	VARCHAR(255)	NOT NULL
);

