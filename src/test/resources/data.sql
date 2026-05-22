-- ============================================
-- 더미 데이터 INSERT (자동 로드용)
-- 생성 기준: 2026-05-22
-- 구조: 강좌 20개 → 섹션 66개 → 강의 131개 → 미션 20개 → 태그 + 매핑
-- ============================================

-- 1. COURSES 테이블 삽입 (20개 강좌)
-- 모두 member_id=9999, status=PUBLISHED 고정

INSERT INTO courses (member_id, title, description, price, level, status, thumbnail_uri, created_at, deleted_at, approved_at) VALUES
(9999, 'Python 기초 완성', 'Python 기본 문법 및 핵심 라이브러리를 다루는 입문 과정입니다.', 29900, 'EASY', 'PUBLISHED', 'thumb/python.jpg', '2026-01-15 10:00:00', NULL, '2026-01-20 10:00:00'),
(9999, 'JavaScript 기초 완성', 'JavaScript 문법, DOM 조작, 비동기 처리의 기본기를 익힙니다.', 29900, 'EASY', 'PUBLISHED', 'thumb/javascript.jpg', '2026-01-20 10:00:00', NULL, '2026-01-25 10:00:00'),
(9999, 'SQL 기초 완성', '데이터베이스 설계 및 SQL 쿼리 작성의 기초를 학습합니다.', 24900, 'EASY', 'PUBLISHED', 'thumb/sql.jpg', '2026-01-25 10:00:00', NULL, '2026-02-01 10:00:00'),
(9999, 'Git 버전 관리 완성', 'Git의 기본 커맨드와 GitHub를 활용한 협업 워크플로우를 배웁니다.', 19900, 'EASY', 'PUBLISHED', 'thumb/git.jpg', '2026-02-01 10:00:00', NULL, '2026-02-05 10:00:00'),
(9999, 'HTML/CSS 기초 완성', '웹 페이지 마크업 및 스타일링의 기본을 다룹니다.', 24900, 'EASY', 'PUBLISHED', 'thumb/htmlcss.jpg', '2026-02-05 10:00:00', NULL, '2026-02-10 10:00:00'),
(9999, 'Linux 명령어 완성', 'Linux 터미널 조작과 시스템 관리의 기본기를 익힙니다.', 19900, 'EASY', 'PUBLISHED', 'thumb/linux.jpg', '2026-02-10 10:00:00', NULL, '2026-02-15 10:00:00'),
(9999, 'REST API 기초 완성', 'HTTP 프로토콜과 RESTful 아키텍처 원리를 학습합니다.', 29900, 'EASY', 'PUBLISHED', 'thumb/restapi.jpg', '2026-02-15 10:00:00', NULL, '2026-02-20 10:00:00'),
(9999, '데이터베이스 기초 완성', '데이터모델링, 정규화, 트랜잭션의 기본 개념을 다룹니다.', 34900, 'EASY', 'PUBLISHED', 'thumb/database.jpg', '2026-02-20 10:00:00', NULL, '2026-02-25 10:00:00'),
(9999, 'React 심화 완성', 'React Hooks, Context API, 상태 관리 라이브러리를 심화합니다.', 49900, 'NORMAL', 'PUBLISHED', 'thumb/react.jpg', '2026-03-01 10:00:00', NULL, '2026-03-05 10:00:00'),
(9999, 'Spring Boot 심화 완성', 'IoC/DI, AOP, 데이터 접근 계층, 보안 설정을 다룹니다.', 59900, 'NORMAL', 'PUBLISHED', 'thumb/springboot.jpg', '2026-03-05 10:00:00', NULL, '2026-03-10 10:00:00'),
(9999, 'Node.js 심화 완성', 'Express, 미들웨어, 비동기 패턴, 성능 최적화를 학습합니다.', 49900, 'NORMAL', 'PUBLISHED', 'thumb/nodejs.jpg', '2026-03-10 10:00:00', NULL, '2026-03-15 10:00:00'),
(9999, 'Vue 심화 완성', 'Vue 3 Composition API, 상태 관리, 플러그인 개발을 다룹니다.', 49900, 'NORMAL', 'PUBLISHED', 'thumb/vue.jpg', '2026-03-15 10:00:00', NULL, '2026-03-20 10:00:00'),
(9999, 'PostgreSQL 심화 완성', 'JSON 지원, 윈도우 함수, 쿼리 최적화를 학습합니다.', 44900, 'NORMAL', 'PUBLISHED', 'thumb/postgresql.jpg', '2026-03-20 10:00:00', NULL, '2026-03-25 10:00:00'),
(9999, 'MongoDB 심화 완성', 'Document 모델, 인덱싱, 트랜잭션, 레플리케이션을 다룹니다.', 44900, 'NORMAL', 'PUBLISHED', 'thumb/mongodb.jpg', '2026-03-25 10:00:00', NULL, '2026-03-30 10:00:00'),
(9999, 'Docker 심화 완성', '컨테이너 아키텍처, 이미지 최적화, 멀티 스테이지 빌드를 학습합니다.', 44900, 'NORMAL', 'PUBLISHED', 'thumb/docker.jpg', '2026-03-30 10:00:00', NULL, '2026-04-05 10:00:00'),
(9999, 'Kubernetes 실전 완성', 'Pod, Service, Deployment, StatefulSet 운영 실전 과정입니다.', 79900, 'HARD', 'PUBLISHED', 'thumb/kubernetes.jpg', '2026-04-05 10:00:00', NULL, '2026-04-10 10:00:00'),
(9999, 'AWS 클라우드 아키텍처', 'EC2, RDS, S3, CloudFront, IAM을 활용한 확장 가능한 설계를 다룹니다.', 79900, 'HARD', 'PUBLISHED', 'thumb/aws.jpg', '2026-04-10 10:00:00', NULL, '2026-04-15 10:00:00'),
(9999, '마이크로서비스 설계 및 구현', '서비스 분리, API Gateway, 이벤트 기반 아키텍처를 구현합니다.', 89900, 'HARD', 'PUBLISHED', 'thumb/microservices.jpg', '2026-04-15 10:00:00', NULL, '2026-04-20 10:00:00'),
(9999, 'Redis 캐싱 전략', '데이터 구조, 캐싱 패턴, 클러스터링, 성능 튜닝을 학습합니다.', 74900, 'HARD', 'PUBLISHED', 'thumb/redis.jpg', '2026-04-20 10:00:00', NULL, '2026-04-25 10:00:00'),
(9999, 'GraphQL API 개발', 'Schema 설계, Query/Mutation, DataLoader, 성능 최적화를 다룹니다.', 69900, 'HARD', 'PUBLISHED', 'thumb/graphql.jpg', '2026-04-25 10:00:00', NULL, '2026-05-01 10:00:00');

-- 2. SECTIONS 테이블 삽입 (66개 섹션)
-- EASY 강좌 1~8: 각각 2개, 3개, 2개, 3개, 2개, 3개, 2개, 3개
-- NORMAL 강좌 9~15: 각각 3개, 4개, 3개, 4개, 3개, 4개, 3개
-- HARD 강좌 16~20: 각각 4개, 5개, 4개, 5개, 4개

INSERT INTO sections (course_id, title, sequence, created_at, updated_at) VALUES
-- Course 1 (Python): 2 sections
(1, '환경 설정 및 기초 문법', 1, '2026-01-15 10:30:00', NULL),
(1, '핵심 라이브러리 및 실전', 2, '2026-01-15 10:31:00', NULL),
-- Course 2 (JavaScript): 3 sections
(2, '변수, 자료형, 연산자 기초', 1, '2026-01-20 10:30:00', NULL),
(2, 'DOM 조작 및 이벤트 처리', 2, '2026-01-20 10:31:00', NULL),
(2, '비동기 처리 및 고급 주제', 3, '2026-01-20 10:32:00', NULL),
-- Course 3 (SQL): 2 sections
(3, 'DDL, DML, 테이블 설계', 1, '2026-01-25 10:30:00', NULL),
(3, 'JOIN, 서브쿼리, 최적화', 2, '2026-01-25 10:31:00', NULL),
-- Course 4 (Git): 3 sections
(4, 'Git 기본 커맨드', 1, '2026-02-01 10:30:00', NULL),
(4, 'GitHub 협업 워크플로우', 2, '2026-02-01 10:31:00', NULL),
(4, 'Cherry-pick, Rebase, 충돌 해결', 3, '2026-02-01 10:32:00', NULL),
-- Course 5 (HTML/CSS): 2 sections
(5, 'HTML 마크업 및 시맨틱', 1, '2026-02-05 10:30:00', NULL),
(5, 'CSS 레이아웃 및 반응형 디자인', 2, '2026-02-05 10:31:00', NULL),
-- Course 6 (Linux): 3 sections
(6, 'Linux 파일 시스템 및 권한', 1, '2026-02-10 10:30:00', NULL),
(6, '프로세스, 패키지 관리', 2, '2026-02-10 10:31:00', NULL),
(6, '네트워크 설정 및 보안', 3, '2026-02-10 10:32:00', NULL),
-- Course 7 (REST API): 2 sections
(7, 'HTTP 프로토콜 및 상태 코드', 1, '2026-02-15 10:30:00', NULL),
(7, 'RESTful 아키텍처 설계', 2, '2026-02-15 10:31:00', NULL),
-- Course 8 (Database): 3 sections
(8, '데이터모델링 및 정규화', 1, '2026-02-20 10:30:00', NULL),
(8, '트랜잭션 및 격리 수준', 2, '2026-02-20 10:31:00', NULL),
(8, '인덱싱 및 성능 최적화', 3, '2026-02-20 10:32:00', NULL),
-- Course 9 (React): 3 sections
(9, 'React Hooks 심화 학습', 1, '2026-03-01 10:30:00', NULL),
(9, 'Context API와 상태 관리', 2, '2026-03-01 10:31:00', NULL),
(9, 'Redux/Zustand 라이브러리', 3, '2026-03-01 10:32:00', NULL),
-- Course 10 (Spring Boot): 4 sections
(10, 'IoC/DI 컨테이너 원리', 1, '2026-03-05 10:30:00', NULL),
(10, 'AOP 및 트랜잭션 관리', 2, '2026-03-05 10:31:00', NULL),
(10, 'JPA와 데이터 접근 계층', 3, '2026-03-05 10:32:00', NULL),
(10, 'Spring Security 설정', 4, '2026-03-05 10:33:00', NULL),
-- Course 11 (Node.js): 3 sections
(11, 'Express 프레임워크 및 라우팅', 1, '2026-03-10 10:30:00', NULL),
(11, '미들웨어 및 요청 처리', 2, '2026-03-10 10:31:00', NULL),
(11, '성능 최적화 및 모니터링', 3, '2026-03-10 10:32:00', NULL),
-- Course 12 (Vue): 4 sections
(12, 'Vue 3 기본 문법', 1, '2026-03-15 10:30:00', NULL),
(12, 'Composition API 활용', 2, '2026-03-15 10:31:00', NULL),
(12, '상태 관리 라이브러리', 3, '2026-03-15 10:32:00', NULL),
(12, 'Vue 플러그인 개발', 4, '2026-03-15 10:33:00', NULL),
-- Course 13 (PostgreSQL): 3 sections
(13, 'JSON 데이터 타입 활용', 1, '2026-03-20 10:30:00', NULL),
(13, '윈도우 함수 및 고급 쿼리', 2, '2026-03-20 10:31:00', NULL),
(13, 'EXPLAIN와 쿼리 최적화', 3, '2026-03-20 10:32:00', NULL),
-- Course 14 (MongoDB): 4 sections
(14, 'Document 모델 설계', 1, '2026-03-25 10:30:00', NULL),
(14, '인덱싱 및 집계 파이프라인', 2, '2026-03-25 10:31:00', NULL),
(14, '트랜잭션 및 레플리케이션', 3, '2026-03-25 10:32:00', NULL),
(14, 'Mongoose ODM 활용', 4, '2026-03-25 10:33:00', NULL),
-- Course 15 (Docker): 3 sections
(15, 'Docker 이미지 및 컨테이너', 1, '2026-03-30 10:30:00', NULL),
(15, '이미지 최적화 및 멀티 스테이지', 2, '2026-03-30 10:31:00', NULL),
(15, 'Docker Compose 운영', 3, '2026-03-30 10:32:00', NULL),
-- Course 16 (Kubernetes): 4 sections
(16, 'Pod, Service, Deployment', 1, '2026-04-05 10:30:00', NULL),
(16, 'ConfigMap, Secret, Volume', 2, '2026-04-05 10:31:00', NULL),
(16, 'StatefulSet 및 네트워크 정책', 3, '2026-04-05 10:32:00', NULL),
(16, '헬스 체크 및 자동 스케일링', 4, '2026-04-05 10:33:00', NULL),
-- Course 17 (AWS): 5 sections
(17, 'EC2 및 네트워크 설정', 1, '2026-04-10 10:30:00', NULL),
(17, 'RDS 및 데이터베이스 관리', 2, '2026-04-10 10:31:00', NULL),
(17, 'S3 및 스토리지 솔루션', 3, '2026-04-10 10:32:00', NULL),
(17, 'CloudFront CDN 설정', 4, '2026-04-10 10:33:00', NULL),
(17, 'IAM 보안 정책', 5, '2026-04-10 10:34:00', NULL),
-- Course 18 (Microservices): 4 sections
(18, '마이크로서비스 아키텍처', 1, '2026-04-15 10:30:00', NULL),
(18, 'API Gateway 패턴', 2, '2026-04-15 10:31:00', NULL),
(18, '이벤트 기반 통신', 3, '2026-04-15 10:32:00', NULL),
(18, '분산 트레이싱 및 로깅', 4, '2026-04-15 10:33:00', NULL),
-- Course 19 (Redis): 5 sections
(19, 'Redis 데이터 구조', 1, '2026-04-20 10:30:00', NULL),
(19, '캐싱 패턴 및 전략', 2, '2026-04-20 10:31:00', NULL),
(19, '클러스터링 및 고가용성', 3, '2026-04-20 10:32:00', NULL),
(19, 'Pub/Sub 메시징', 4, '2026-04-20 10:33:00', NULL),
(19, '성능 튜닝 및 모니터링', 5, '2026-04-20 10:34:00', NULL),
-- Course 20 (GraphQL): 4 sections
(20, 'GraphQL Schema 설계', 1, '2026-04-25 10:30:00', NULL),
(20, 'Query와 Mutation 구현', 2, '2026-04-25 10:31:00', NULL),
(20, 'DataLoader와 배치 처리', 3, '2026-04-25 10:32:00', NULL),
(20, '성능 최적화 및 캐싱', 4, '2026-04-25 10:33:00', NULL);

-- 3. LECTURES 테이블 삽입 (131개 강의)
-- 각 섹션마다 1~2개의 강의 배치

INSERT INTO lectures (section_id, type, title, sequence, content_uri, created_at, updated_at) VALUES
-- Course 1 Sections
(1, 'VIDEO', 'Python 설치 및 개발 환경 구성', 1, 'video/python-001.mp4', '2026-01-15 11:00:00', NULL),
(1, 'PDF', 'Python 기본 문법 정리', 2, 'pdf/python-001.pdf', '2026-01-15 11:01:00', NULL),
(2, 'VIDEO', '리스트, 딕셔너리 활용 실전', 1, 'video/python-002.mp4', '2026-01-15 11:02:00', NULL),
-- Course 2 Sections
(3, 'VIDEO', 'JavaScript 변수와 자료형 이해하기', 1, 'video/js-001.mp4', '2026-01-20 11:00:00', NULL),
(3, 'PDF', '연산자 및 제어문 정리', 2, 'pdf/js-001.pdf', '2026-01-20 11:01:00', NULL),
(4, 'VIDEO', 'DOM 선택 및 조작 기술', 1, 'video/js-002.mp4', '2026-01-20 11:02:00', NULL),
(4, 'PDF', '이벤트 리스너 및 핸들러', 2, 'pdf/js-002.pdf', '2026-01-20 11:03:00', NULL),
(5, 'VIDEO', 'Promise 및 async/await 패턴', 1, 'video/js-003.mp4', '2026-01-20 11:04:00', NULL),
(5, 'PDF', 'fetch API와 AJAX 통신', 2, 'pdf/js-003.pdf', '2026-01-20 11:05:00', NULL),
-- Course 3 Sections
(6, 'VIDEO', 'CREATE TABLE 및 데이터 타입', 1, 'video/sql-001.mp4', '2026-01-25 11:00:00', NULL),
(6, 'VIDEO', 'INSERT, UPDATE, DELETE 기본', 2, 'video/sql-002.mp4', '2026-01-25 11:01:00', NULL),
(7, 'PDF', 'JOIN 종류 및 사용법', 1, 'pdf/sql-001.pdf', '2026-01-25 11:02:00', NULL),
(7, 'VIDEO', '서브쿼리와 쿼리 최적화 기법', 2, 'video/sql-003.mp4', '2026-01-25 11:03:00', NULL),
-- Course 4 Sections
(8, 'VIDEO', 'git init, add, commit 기본', 1, 'video/git-001.mp4', '2026-02-01 11:00:00', NULL),
(8, 'PDF', 'git log 및 diff 활용', 2, 'pdf/git-001.pdf', '2026-02-01 11:01:00', NULL),
(9, 'VIDEO', 'GitHub 저장소 생성 및 push/pull', 1, 'video/git-002.mp4', '2026-02-01 11:02:00', NULL),
(9, 'PDF', 'Pull Request 리뷰 프로세스', 2, 'pdf/git-002.pdf', '2026-02-01 11:03:00', NULL),
(10, 'VIDEO', 'git rebase와 cherry-pick', 1, 'video/git-003.mp4', '2026-02-01 11:04:00', NULL),
(10, 'PDF', '충돌 해결 전략 및 워크플로우', 2, 'pdf/git-003.pdf', '2026-02-01 11:05:00', NULL),
-- Course 5 Sections
(11, 'VIDEO', 'HTML 기본 태그와 시맨틱', 1, 'video/html-001.mp4', '2026-02-05 11:00:00', NULL),
(11, 'PDF', 'Form 및 입력 요소 정리', 2, 'pdf/html-001.pdf', '2026-02-05 11:01:00', NULL),
(12, 'VIDEO', 'CSS Flexbox 레이아웃', 1, 'video/css-001.mp4', '2026-02-05 11:02:00', NULL),
(12, 'PDF', 'CSS Grid 및 반응형 디자인', 2, 'pdf/css-001.pdf', '2026-02-05 11:03:00', NULL),
-- Course 6 Sections
(13, 'VIDEO', 'Linux 디렉토리 구조 및 ls 명령어', 1, 'video/linux-001.mp4', '2026-02-10 11:00:00', NULL),
(13, 'PDF', 'chmod, chown 권한 관리', 2, 'pdf/linux-001.pdf', '2026-02-10 11:01:00', NULL),
(14, 'VIDEO', 'ps, top 프로세스 모니터링', 1, 'video/linux-002.mp4', '2026-02-10 11:02:00', NULL),
(14, 'PDF', 'apt, yum 패키지 관리 도구', 2, 'pdf/linux-002.pdf', '2026-02-10 11:03:00', NULL),
(15, 'VIDEO', 'ifconfig, ss 네트워크 설정', 1, 'video/linux-003.mp4', '2026-02-10 11:04:00', NULL),
(15, 'PDF', 'firewall, ssh 보안 설정', 2, 'pdf/linux-003.pdf', '2026-02-10 11:05:00', NULL),
-- Course 7 Sections
(16, 'VIDEO', 'HTTP 메서드와 상태 코드 이해', 1, 'video/restapi-001.mp4', '2026-02-15 11:00:00', NULL),
(16, 'PDF', 'Request/Response 헤더 분석', 2, 'pdf/restapi-001.pdf', '2026-02-15 11:01:00', NULL),
(17, 'VIDEO', 'REST 아키텍처 설계 원칙', 1, 'video/restapi-002.mp4', '2026-02-15 11:02:00', NULL),
(17, 'PDF', '버전 관리 및 에러 핸들링', 2, 'pdf/restapi-002.pdf', '2026-02-15 11:03:00', NULL),
-- Course 8 Sections
(18, 'VIDEO', '개념 모델링과 ER 다이어그램', 1, 'video/db-001.mp4', '2026-02-20 11:00:00', NULL),
(18, 'PDF', '정규화 및 역정규화 전략', 2, 'pdf/db-001.pdf', '2026-02-20 11:01:00', NULL),
(19, 'VIDEO', 'ACID 속성 및 트랜잭션 동작', 1, 'video/db-002.mp4', '2026-02-20 11:02:00', NULL),
(19, 'PDF', '격리 수준 및 잠금 메커니즘', 2, 'pdf/db-002.pdf', '2026-02-20 11:03:00', NULL),
(20, 'VIDEO', 'B-Tree 인덱스 구조 이해', 1, 'video/db-003.mp4', '2026-02-20 11:04:00', NULL),
(20, 'PDF', 'EXPLAIN PLAN과 쿼리 최적화', 2, 'pdf/db-003.pdf', '2026-02-20 11:05:00', NULL),
-- Course 9 Sections
(21, 'VIDEO', 'useState와 useEffect 심화', 1, 'video/react-001.mp4', '2026-03-01 11:00:00', NULL),
(21, 'PDF', 'useReducer와 커스텀 Hook', 2, 'pdf/react-001.pdf', '2026-03-01 11:01:00', NULL),
(22, 'VIDEO', 'Context API 설정 및 활용', 1, 'video/react-002.mp4', '2026-03-01 11:02:00', NULL),
(22, 'PDF', 'Redux와 Redux Thunk 패턴', 2, 'pdf/react-002.pdf', '2026-03-01 11:03:00', NULL),
(23, 'VIDEO', 'Zustand 및 Jotai 라이브러리', 1, 'video/react-003.mp4', '2026-03-01 11:04:00', NULL),
(23, 'PDF', '상태 관리 성능 최적화', 2, 'pdf/react-003.pdf', '2026-03-01 11:05:00', NULL),
-- Course 10 Sections
(24, 'VIDEO', '스프링 빈 라이프사이클', 1, 'video/springboot-001.mp4', '2026-03-05 11:00:00', NULL),
(24, 'PDF', '@Autowired와 생성자 주입', 2, 'pdf/springboot-001.pdf', '2026-03-05 11:01:00', NULL),
(25, 'VIDEO', '@Aspect 및 Pointcut 표현식', 1, 'video/springboot-002.mp4', '2026-03-05 11:02:00', NULL),
(25, 'PDF', '@Transactional 트랜잭션 관리', 2, 'pdf/springboot-002.pdf', '2026-03-05 11:03:00', NULL),
(26, 'VIDEO', 'JPA 엔티티 및 관계 매핑', 1, 'video/springboot-003.mp4', '2026-03-05 11:04:00', NULL),
(26, 'PDF', 'Repository 패턴과 쿼리 메서드', 2, 'pdf/springboot-003.pdf', '2026-03-05 11:05:00', NULL),
(27, 'VIDEO', 'SecurityConfig와 필터 체인', 1, 'video/springboot-004.mp4', '2026-03-05 11:06:00', NULL),
(27, 'PDF', 'JWT 토큰 기반 인증', 2, 'pdf/springboot-004.pdf', '2026-03-05 11:07:00', NULL),
-- Course 11 Sections
(28, 'VIDEO', 'Express 기본 응답 및 라우팅', 1, 'video/nodejs-001.mp4', '2026-03-10 11:00:00', NULL),
(28, 'PDF', '라우터 그룹 및 URL 파라미터', 2, 'pdf/nodejs-001.pdf', '2026-03-10 11:01:00', NULL),
(29, 'VIDEO', '커스텀 미들웨어 작성', 1, 'video/nodejs-002.mp4', '2026-03-10 11:02:00', NULL),
(29, 'PDF', '에러 핸들링 미들웨어', 2, 'pdf/nodejs-002.pdf', '2026-03-10 11:03:00', NULL),
(30, 'VIDEO', '요청/응답 최적화 기법', 1, 'video/nodejs-003.mp4', '2026-03-10 11:04:00', NULL),
(30, 'PDF', '성능 모니터링 도구', 2, 'pdf/nodejs-003.pdf', '2026-03-10 11:05:00', NULL),
-- Course 12 Sections
(31, 'VIDEO', '벡터 문법 및 템플릿 재사용', 1, 'video/vue-001.mp4', '2026-03-15 11:00:00', NULL),
(31, 'PDF', 'v-if, v-for 조건부 렌더링', 2, 'pdf/vue-001.pdf', '2026-03-15 11:01:00', NULL),
(32, 'VIDEO', 'Composition API 기초', 1, 'video/vue-002.mp4', '2026-03-15 11:02:00', NULL),
(32, 'PDF', 'ref(), reactive() 상태 관리', 2, 'pdf/vue-002.pdf', '2026-03-15 11:03:00', NULL),
(33, 'VIDEO', 'Pinia 상태 관리 라이브러리', 1, 'video/vue-003.mp4', '2026-03-15 11:04:00', NULL),
(33, 'PDF', '게터, 액션 활용법', 2, 'pdf/vue-003.pdf', '2026-03-15 11:05:00', NULL),
(34, 'VIDEO', '플러그인 개발 및 설치', 1, 'video/vue-004.mp4', '2026-03-15 11:06:00', NULL),
(34, 'PDF', '재사용 가능한 모듈 패턴', 2, 'pdf/vue-004.pdf', '2026-03-15 11:07:00', NULL),
-- Course 13 Sections
(35, 'VIDEO', 'JSON 데이터 쿼리 및 저장', 1, 'video/postgresql-001.mp4', '2026-03-20 11:00:00', NULL),
(35, 'PDF', 'JSON 연산자 및 함수', 2, 'pdf/postgresql-001.pdf', '2026-03-20 11:01:00', NULL),
(36, 'VIDEO', 'ROW_NUMBER와 RANK 함수', 1, 'video/postgresql-002.mp4', '2026-03-20 11:02:00', NULL),
(36, 'PDF', 'OVER 절 및 윈도우 프레임', 2, 'pdf/postgresql-002.pdf', '2026-03-20 11:03:00', NULL),
(37, 'VIDEO', 'EXPLAIN ANALYZE 성능 분석', 1, 'video/postgresql-003.mp4', '2026-03-20 11:04:00', NULL),
(37, 'PDF', '인덱스 전략 및 쿼리 튜닝', 2, 'pdf/postgresql-003.pdf', '2026-03-20 11:05:00', NULL),
-- Course 14 Sections
(38, 'VIDEO', 'Document 모델 설계 전략', 1, 'video/mongodb-001.mp4', '2026-03-25 11:00:00', NULL),
(38, 'PDF', '임베드 vs 레퍼런스 선택', 2, 'pdf/mongodb-001.pdf', '2026-03-25 11:01:00', NULL),
(39, 'VIDEO', '싱글 필드 및 컴파운드 인덱싱', 1, 'video/mongodb-002.mp4', '2026-03-25 11:02:00', NULL),
(39, 'PDF', '$group과 집계 파이프라인', 2, 'pdf/mongodb-002.pdf', '2026-03-25 11:03:00', NULL),
(40, 'VIDEO', '멀티 도큐먼트 트랜잭션', 1, 'video/mongodb-003.mp4', '2026-03-25 11:04:00', NULL),
(40, 'PDF', '레플리카 세트 설정', 2, 'pdf/mongodb-003.pdf', '2026-03-25 11:05:00', NULL),
(41, 'VIDEO', 'Mongoose 스키마 및 모델', 1, 'video/mongodb-004.mp4', '2026-03-25 11:06:00', NULL),
(41, 'PDF', 'Mongoose 미들웨어 활용', 2, 'pdf/mongodb-004.pdf', '2026-03-25 11:07:00', NULL),
-- Course 15 Sections
(42, 'VIDEO', 'Dockerfile 작성 및 빌드', 1, 'video/docker-001.mp4', '2026-03-30 11:00:00', NULL),
(42, 'PDF', '이미지 레이어 및 캐싱', 2, 'pdf/docker-001.pdf', '2026-03-30 11:01:00', NULL),
(43, 'VIDEO', '멀티 스테이지 빌드 최적화', 1, 'video/docker-002.mp4', '2026-03-30 11:02:00', NULL),
(43, 'PDF', '이미지 사이즈 감소 기법', 2, 'pdf/docker-002.pdf', '2026-03-30 11:03:00', NULL),
(44, 'VIDEO', 'docker-compose 오케스트레이션', 1, 'video/docker-003.mp4', '2026-03-30 11:04:00', NULL),
(44, 'PDF', '환경 변수 및 볼륨 관리', 2, 'pdf/docker-003.pdf', '2026-03-30 11:05:00', NULL),
-- Course 16 Sections
(45, 'VIDEO', 'Pod와 Service 생성', 1, 'video/kubernetes-001.mp4', '2026-04-05 11:00:00', NULL),
(45, 'PDF', 'Deployment 버전 관리', 2, 'pdf/kubernetes-001.pdf', '2026-04-05 11:01:00', NULL),
(46, 'VIDEO', 'ConfigMap과 Secret 활용', 1, 'video/kubernetes-002.mp4', '2026-04-05 11:02:00', NULL),
(46, 'PDF', '영구 볼륨(PV, PVC) 설정', 2, 'pdf/kubernetes-002.pdf', '2026-04-05 11:03:00', NULL),
(47, 'VIDEO', 'StatefulSet과 DaemonSet', 1, 'video/kubernetes-003.mp4', '2026-04-05 11:04:00', NULL),
(47, 'PDF', '네트워크 정책 및 Ingress', 2, 'pdf/kubernetes-003.pdf', '2026-04-05 11:05:00', NULL),
(48, 'VIDEO', 'livenessProbe와 readinessProbe', 1, 'video/kubernetes-004.mp4', '2026-04-05 11:06:00', NULL),
(48, 'PDF', '자동 스케일링 설정', 2, 'pdf/kubernetes-004.pdf', '2026-04-05 11:07:00', NULL),
-- Course 17 Sections
(49, 'VIDEO', 'EC2 인스턴스 생성 및 구성', 1, 'video/aws-001.mp4', '2026-04-10 11:00:00', NULL),
(49, 'PDF', 'VPC와 서브넷 네트워킹', 2, 'pdf/aws-001.pdf', '2026-04-10 11:01:00', NULL),
(50, 'VIDEO', 'RDS 데이터베이스 생성', 1, 'video/aws-002.mp4', '2026-04-10 11:02:00', NULL),
(50, 'PDF', '자동 백업 및 복구 전략', 2, 'pdf/aws-002.pdf', '2026-04-10 11:03:00', NULL),
(51, 'VIDEO', 'S3 버킷 생성 및 권한', 1, 'video/aws-003.mp4', '2026-04-10 11:04:00', NULL),
(51, 'PDF', '버전 관리 및 수명 주기', 2, 'pdf/aws-003.pdf', '2026-04-10 11:05:00', NULL),
(52, 'VIDEO', 'CloudFront 배포 설정', 1, 'video/aws-004.mp4', '2026-04-10 11:06:00', NULL),
(52, 'PDF', '캐싱 정책 및 원본 설정', 2, 'pdf/aws-004.pdf', '2026-04-10 11:07:00', NULL),
(53, 'VIDEO', 'IAM 사용자 및 정책 관리', 1, 'video/aws-005.mp4', '2026-04-10 11:08:00', NULL),
(53, 'PDF', '역할(Role) 기반 접근 제어', 2, 'pdf/aws-005.pdf', '2026-04-10 11:09:00', NULL),
-- Course 18 Sections
(54, 'VIDEO', '도메인 주도 설계(DDD)', 1, 'video/microservices-001.mp4', '2026-04-15 11:00:00', NULL),
(54, 'PDF', '바운디드 컨텍스트 분리', 2, 'pdf/microservices-001.pdf', '2026-04-15 11:01:00', NULL),
(55, 'VIDEO', 'API Gateway 라우팅', 1, 'video/microservices-002.mp4', '2026-04-15 11:02:00', NULL),
(55, 'PDF', '인증, 속도 제한, 로드 밸런싱', 2, 'pdf/microservices-002.pdf', '2026-04-15 11:03:00', NULL),
(56, 'VIDEO', 'Event Sourcing 패턴', 1, 'video/microservices-003.mp4', '2026-04-15 11:04:00', NULL),
(56, 'PDF', '메시지 브로커(RabbitMQ, Kafka)', 2, 'pdf/microservices-003.pdf', '2026-04-15 11:05:00', NULL),
(57, 'VIDEO', 'Jaeger 분산 추적', 1, 'video/microservices-004.mp4', '2026-04-15 11:06:00', NULL),
(57, 'PDF', '중앙 집중식 로깅(ELK Stack)', 2, 'pdf/microservices-004.pdf', '2026-04-15 11:07:00', NULL),
-- Course 19 Sections
(58, 'VIDEO', 'String, List, Hash 데이터 구조', 1, 'video/redis-001.mp4', '2026-04-20 11:00:00', NULL),
(58, 'PDF', 'Set과 Sorted Set 활용', 2, 'pdf/redis-001.pdf', '2026-04-20 11:01:00', NULL),
(59, 'VIDEO', 'Cache-Aside와 Write-Through', 1, 'video/redis-002.mp4', '2026-04-20 11:02:00', NULL),
(59, 'PDF', '캐시 무효화 전략', 2, 'pdf/redis-002.pdf', '2026-04-20 11:03:00', NULL),
(60, 'VIDEO', '클러스터 노드 설정', 1, 'video/redis-003.mp4', '2026-04-20 11:04:00', NULL),
(60, 'PDF', '장애 조치 및 고가용성', 2, 'pdf/redis-003.pdf', '2026-04-20 11:05:00', NULL),
(61, 'VIDEO', 'SUBSCRIBE와 PSUBSCRIBE', 1, 'video/redis-004.mp4', '2026-04-20 11:06:00', NULL),
(61, 'PDF', '메시지 패턴 및 큐', 2, 'pdf/redis-004.pdf', '2026-04-20 11:07:00', NULL),
(62, 'VIDEO', 'INFO와 MONITOR 명령어', 1, 'video/redis-005.mp4', '2026-04-20 11:08:00', NULL),
(62, 'PDF', '메모리 최적화 및 보안', 2, 'pdf/redis-005.pdf', '2026-04-20 11:09:00', NULL),
-- Course 20 Sections
(63, 'VIDEO', 'Type 정의 및 필드 규칙', 1, 'video/graphql-001.mp4', '2026-04-25 11:00:00', NULL),
(63, 'PDF', 'Scalar 타입과 커스텀 타입', 2, 'pdf/graphql-001.pdf', '2026-04-25 11:01:00', NULL),
(64, 'VIDEO', 'Query와 Mutation 작성', 1, 'video/graphql-002.mp4', '2026-04-25 11:02:00', NULL),
(64, 'PDF', '인자 및 입력 타입', 2, 'pdf/graphql-002.pdf', '2026-04-25 11:03:00', NULL),
(65, 'VIDEO', 'DataLoader 배치 처리', 1, 'video/graphql-003.mp4', '2026-04-25 11:04:00', NULL),
(65, 'PDF', 'N+1 문제 해결', 2, 'pdf/graphql-003.pdf', '2026-04-25 11:05:00', NULL),
(66, 'VIDEO', 'Query 복잡도 분석', 1, 'video/graphql-004.mp4', '2026-04-25 11:06:00', NULL),
(66, 'PDF', '레이트 리미팅 및 캐싱 전략', 2, 'pdf/graphql-004.pdf', '2026-04-25 11:07:00', NULL);

-- 4. MISSIONS 테이블 삽입 (20개 미션)
-- 각 강좌의 2번째 섹션에만 1개 미션씩 배치

INSERT INTO missions (section_id, title, contents, sequence, created_at, updated_at) VALUES
(2, 'Python 데이터 처리 프로젝트', '주어진 CSV 파일을 읽고 판다스로 통계 분석 및 시각화를 수행합니다.', 1, '2026-01-15 12:00:00', NULL),
(4, 'DOM 토이 프로젝트', '바닐라 JavaScript로 TODO 앱을 구현하고 로컬 스토리지에 저장합니다.', 1, '2026-01-20 12:00:00', NULL),
(7, 'SQL 데이터 조회 미션', '주어진 데이터베이스 스키마에서 복잡한 JOIN 쿼리를 작성합니다.', 1, '2026-01-25 12:00:00', NULL),
(9, 'Git 협업 실습', '팀원과 함께 저장소에서 브랜치를 나누고 Pull Request를 생성/리뷰합니다.', 1, '2026-02-01 12:00:00', NULL),
(12, '반응형 웹 페이지 구축', 'HTML/CSS로 모바일, 태블릿, 데스크톱에 대응하는 페이지를 만듭니다.', 1, '2026-02-05 12:00:00', NULL),
(14, 'Linux 시스템 관리 실습', 'Linux 서버를 설정하고 사용자 권한, 프로세스, 네트워크를 관리합니다.', 1, '2026-02-10 12:00:00', NULL),
(17, 'REST API 설계 미션', '주어진 요구사항에 맞춰 RESTful API 스펙을 정의하고 문서화합니다.', 1, '2026-02-15 12:00:00', NULL),
(19, '데이터베이스 스키마 설계', '상품, 주문 도메인의 정규화된 스키마를 ER 다이어그램으로 작성합니다.', 1, '2026-02-20 12:00:00', NULL),
(22, 'React 상태 관리 앱', 'Redux를 활용한 쇼핑 카트 앱을 구현합니다.', 1, '2026-03-01 12:00:00', NULL),
(25, 'Spring Boot API 서버 구축', 'JPA와 Spring Security를 적용한 사용자 관리 API를 개발합니다.', 1, '2026-03-05 12:00:00', NULL),
(28, 'Node.js 익스프레스 서버', '미들웨어를 활용한 에러 핸들링과 로깅 기능이 있는 서버를 구현합니다.', 1, '2026-03-10 12:00:00', NULL),
(31, 'Vue 3 포트폴리오 사이트', 'Composition API로 동적 포트폴리오 페이지를 개발합니다.', 1, '2026-03-15 12:00:00', NULL),
(35, 'PostgreSQL 복잡 쿼리', '윈도우 함수와 JSON을 활용한 고급 쿼리를 최적화합니다.', 1, '2026-03-20 12:00:00', NULL),
(38, 'MongoDB 컬렉션 설계', '전자상거래 도메인의 Document 모델을 설계하고 임베드 최적화를 수행합니다.', 1, '2026-03-25 12:00:00', NULL),
(42, 'Docker 이미지 최적화', '멀티 스테이지 빌드로 Node.js 앱 이미지 사이즈를 50% 이상 감소시킵니다.', 1, '2026-03-30 12:00:00', NULL),
(45, 'Kubernetes 클러스터 구축', '로컬 쿠버네티스에 마이크로서비스 앱을 배포하고 자동 스케일링을 구성합니다.', 1, '2026-04-05 12:00:00', NULL),
(49, 'AWS 아키텍처 설계', 'AWS 기능을 활용한 고가용성 웹 애플리케이션 아키텍처를 설계합니다.', 1, '2026-04-10 12:00:00', NULL),
(54, '마이크로서비스 프로토타입', 'API Gateway와 이벤트 기반 통신으로 간단한 마이크로서비스를 구현합니다.', 1, '2026-04-15 12:00:00', NULL),
(58, 'Redis 캐싱 시스템', '캐시 전략을 적용한 API 응답 속도 개선 프로젝트를 완료합니다.', 1, '2026-04-20 12:00:00', NULL),
(63, 'GraphQL 서버 개발', 'DataLoader를 활용한 고성능 GraphQL API 서버를 개발합니다.', 1, '2026-04-25 12:00:00', NULL);

-- 5. TAGS 테이블 삽입 (기술 기반 태그)

INSERT INTO tags (tag_name) VALUES
('Python'),
('JavaScript'),
('SQL'),
('Git'),
('HTML/CSS'),
('Linux'),
('REST API'),
('Database'),
('React'),
('Spring Boot'),
('Node.js'),
('Vue'),
('PostgreSQL'),
('MongoDB'),
('Docker'),
('Kubernetes'),
('AWS'),
('Microservices'),
('Redis'),
('GraphQL'),
('Java'),
('Go'),
('DevOps'),
('Caching'),
('Architecture'),
('Infra'),
('Backend'),
('Frontend');

-- 6. COURSE_TAG_REFS 테이블 삽입 (과정과 태그 매핑)
-- 강좌별 2~3개의 기술 태그 할당

INSERT INTO course_tag_refs (course_id, tag_id) VALUES
-- Course 1 (Python) -> Python, Backend
(1, 1), (1, 27),
-- Course 2 (JavaScript) -> JavaScript, Frontend
(2, 2), (2, 28),
-- Course 3 (SQL) -> SQL, Database
(3, 3), (3, 8),
-- Course 4 (Git) -> Git, DevOps
(4, 4), (4, 23),
-- Course 5 (HTML/CSS) -> HTML/CSS, Frontend
(5, 5), (5, 28),
-- Course 6 (Linux) -> Linux, DevOps
(6, 6), (6, 23),
-- Course 7 (REST API) -> REST API, Backend
(7, 7), (7, 27),
-- Course 8 (Database) -> Database, SQL
(8, 8), (8, 3),
-- Course 9 (React) -> React, Frontend, JavaScript
(9, 9), (9, 28), (9, 2),
-- Course 10 (Spring Boot) -> Spring Boot, Backend, Java
(10, 10), (10, 27), (10, 21),
-- Course 11 (Node.js) -> Node.js, Backend, JavaScript
(11, 11), (11, 27), (11, 2),
-- Course 12 (Vue) -> Vue, Frontend, JavaScript
(12, 12), (12, 28), (12, 2),
-- Course 13 (PostgreSQL) -> PostgreSQL, Database
(13, 13), (13, 8),
-- Course 14 (MongoDB) -> MongoDB, Database
(14, 14), (14, 8),
-- Course 15 (Docker) -> Docker, DevOps
(15, 15), (15, 23),
-- Course 16 (Kubernetes) -> Kubernetes, DevOps, Infra
(16, 16), (16, 23), (16, 26),
-- Course 17 (AWS) -> AWS, Infra, Architecture
(17, 17), (17, 26), (17, 25),
-- Course 18 (Microservices) -> Microservices, Architecture, Backend
(18, 18), (18, 25), (18, 27),
-- Course 19 (Redis) -> Redis, Caching, Backend
(19, 19), (19, 24), (19, 27),
-- Course 20 (GraphQL) -> GraphQL, Backend, API
(20, 20), (20, 27), (20, 7);

-- ============================================
-- 데이터 삽입 완료
-- 강좌: 20개 (모두 PUBLISHED, member_id=9999)
-- 섹션: 66개 (강좌별 2~5개 가변)
-- 강의: 131개 (섹션당 1~2개)
-- 미션: 20개 (각 강좌 2번째 섹션에만 1개)
-- 태그: 28개 (기술 기반)
-- 과정_태그 매핑: 49개 (강좌별 2~3개 태그)
-- ============================================
