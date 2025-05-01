# 📌 Demo Project (Spring Boot BBS)

Spring Boot 기반의 게시판(BBS) 시스템입니다. 사용자 인증, 게시글 작성, 댓글, 파일 업로드 등의 기능을 제공하며, MVC 아키텍처와 MyBatis를 활용하여 설계되었습니다.

---

## 📁 프로젝트 구조

### 📦 src/main/java/com.bbs.demo

- `DemoApplication.java` - Spring Boot 메인 클래스

#### 🔒 config (보안 설정 및 핸들러)
- `CustomAccessDeniedHandler.java`
- `CustomUserDetailsService.java`
- `LoginSuccessHandler.java`
- `SecurityConfig.java`

#### 🌐 controller (웹 요청 처리)
- `AdminController.java`
- `BoardController.java`
- `CommentController.java`
- `FileController.java`
- `IndexController.java`
- `LoginController.java`
- `NoteController.java`
- `PageController.java`
- `RegisterController.java`
- `WellKnownController.java`

#### 🧱 domain (엔티티 클래스 - DB 매핑)
- `Admin.java`, `Boards.java`, `Comments.java`, `Files.java`
- `Notes.java`, `Page.java`, `Token.java`
- `UserGrade.java`, `Users.java`, `ViewCount.java`

#### 🧩 mapper (MyBatis 매퍼 인터페이스)
- `AdminMapper.java`, `BoardMapper.java`, `CommentMapper.java`
- `FileMapper.java`, `IndexMapper.java`, `LoginMemberMapper.java`
- `NoteMapper.java`, `PageMapper.java`

#### ⚙️ service (비즈니스 로직)
- `AdminService.java`, `CommentService.java`, `FileService.java`, `NoteService.java`
- `FileServiceImpl.java`, `NoteServiceImpl.java`, `TempService.java`, `Tokenizer.java`

---

### 📂 src/main/resources

#### 🧾 mapper (MyBatis XML 매퍼)
- `AdminMapper.xml`, `BoardMapper.xml`, `CommentMapper.xml`
- `FileMapper.xml`, `IndexMapper.xml`, `LoginMapper.xml`
- `NoteMapper.xml`, `PageMapper.xml`

#### 🖼 templates (Thymeleaf HTML 뷰)
- `admin_tables.html`, `admin.html`, `board_list.html`
- `index.html`, `login.html`, `note_modify.html`
- `note_read.html`, `note_register.html`, `register.html`


---

## 🔐 보안 구성

- `SecurityConfig`: Spring Security 설정
- `CustomAccessDeniedHandler`, `LoginSuccessHandler`: 접근 제어 및 로그인 성공 처리
- `CustomUserDetailsService`: 사용자 정보 로드 및 인증 처리
- `Tokenizer`: 토큰 생성 및 파싱 로직 포함

---

## 🧾 주요 기능 설명

| 기능 | 설명 | 관련 클래스 |
|------|------|--------------|
| 사용자 인증 | 로그인, 회원가입, 인증 토큰 처리 | `LoginController`, `RegisterController`, `Tokenizer` |
| 게시글 기능 | 게시글 작성, 수정, 삭제, 조회 | `NoteController`, `NoteService`, `NoteMapper` |
| 댓글 기능 | 댓글 추가 및 관리 | `CommentController`, `CommentService` |
| 파일 업로드 | 게시글 파일 첨부 기능 | `FileController`, `FileService` |
| 관리자 기능 | 사용자 및 권한 관리 | `AdminController`, `AdminService` |
| 페이지 이동 | 페이지네이션 및 뷰 처리 | `PageController`, `pageMapper.xml` |

---

## 🗃️ 데이터베이스 매핑 구조

- 도메인 객체와 Mapper 인터페이스, Mapper XML이 1:1 매핑됨.
- 예시:
  - `Notes.java` ↔ `NoteMapper.java` ↔ `NoteMapper.xml`

---

## 🖼️ 사용자 화면 (Thymeleaf View)

| 파일명 | 설명 |
|--------|------|
| `login.html` | 로그인 화면 |
| `register.html` | 회원가입 화면 |
| `board_list.html` | 게시글 목록 |
| `note_register.html` | 게시글 작성 |
| `note_modify.html` | 게시글 수정 |
| `note_read.html` | 게시글 상세보기 |
| `admin.html` | 관리자 테이블 관리 화면 |

---
