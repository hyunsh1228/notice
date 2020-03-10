CREATE TABLE board_notice(
	num Number PRIMARY key,
	Writer VARCHAR2(100) not null, -- 글 작성자의 id
	title VARCHAR2(100) not null,
	content CLOB,
	viewCount NUMBER, -- 조회수
	regdate DATE
);

CREATE SEQUENCE board_notice_seq;

-- 특정 페이지 가져오는 방법  --
SELECT *								--3. page select한다.
FROM
	(SELECT result1.*, Rownum As RNUM   --2. 번호부여하고
	FROM
		(SELECT num,writer,title 		--1.줄세우고
		FROM board_qna
		WHERE writer LIKE '%gura%' 		--키워드 검색 (동적 쿼리로 처리.) // 생길 수도 있고 아닐 수도 있다.
		ORDER by num DESC) result1)
WHERE RNUM BETWEEN ? and ? 				--몇번 페이지인지

CREATE TABLE board_user
(ID VARCHAR2(50) PRIMARY KEY,
PWD VARCHAR2(100) NOT NULL,
EMAIL VARCHAR2(50),
REGDATE DATE
);

CREATE TABLE board_qna(
	num NUMBER PRIMARY KEY,
	writer VARCHAR2(100) NOT NULL, -- 글 작성자의 id 
	title VARCHAR2(100) NOT NULL,
	content CLOB,
	viewCount NUMBER, -- 조회수
	regdate DATE
);

CREATE SEQUENCE board_qna_seq;

CREATE TABLE board_qna_comment(
	num NUMBER PRIMARY KEY, -- 댓글의 글번호
	writer VARCHAR2(100), -- 댓글 작성자
	content VARCHAR2(500), -- 댓글 내용
	target_id VARCHAR2(100), -- 댓글의대상이되는아이디(글작성자)
	ref_group NUMBER, -- 댓글 그룹번호
	comment_group NUMBER, -- 원글에 달린 댓글 내에서의 그룹번호
	deleted CHAR(3) DEFAULT 'no', -- 댓글이 삭제 되었는지 여부 
	regdate DATE -- 댓글 등록일 
);

CREATE SEQUENCE board_qna_comment_seq;