CREATE TABLE board_qna(
	num Number PRIMARY key,
	Writer VARCHAR2(100) not null, -- 글 작성자의 id
	title VARCHAR2(100) not null,
	content CLOB,
	viewCount NUMBER, -- 조회수
	regdate DATE
);

Create Sequence board_qna_seq;
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