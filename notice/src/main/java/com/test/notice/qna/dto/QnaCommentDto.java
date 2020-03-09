package com.test.notice.qna.dto;

public class QnaCommentDto {
	private int num;
	private String writer;
	private String content; //댓글 내용
	private String target_id; //댓글 대상자의 아이디
	private String regdate;
	
	public QnaCommentDto() {}

	public QnaCommentDto(int num, String writer, String content, String target_id, String regdate) {
		super();
		this.num = num;
		this.writer = writer;
		this.content = content;
		this.target_id = target_id;
		this.regdate = regdate;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTarget_id() {
		return target_id;
	}

	public void setTarget_id(String target_id) {
		this.target_id = target_id;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	
}
