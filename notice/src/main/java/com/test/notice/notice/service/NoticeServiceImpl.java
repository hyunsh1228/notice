package com.test.notice.notice.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.notice.exception.CanNotDeleteException;
import com.test.notice.notice.dao.NoticeDao;
import com.test.notice.notice.dto.NoticeDto;


@Service
public class NoticeServiceImpl implements NoticeService{
	@Autowired
	private NoticeDao noticeDao;
	
	//한 페이지에 나타낼 row 의 갯수 
	static final int PAGE_ROW_COUNT=5;
	//하단 디스플레이 페이지 갯수 
	static final int PAGE_DISPLAY_COUNT=5; // 페이지 하단에 1 2 3 나옴

	@Override
	public void getList(HttpServletRequest request) {
		
		String keyword=request.getParameter("keyword");
		String condition=request.getParameter("condition");
		
		//검색 키워드가 존재한다면 키워드를 담을 CafeDto 객체 생성 
		NoticeDto dto=new NoticeDto();
		if(keyword != null) {//검색 키워드가 전달된 경우
			if(condition.equals("titlecontent")) {//제목+내용 검색
				dto.setTitle(keyword);
				dto.setContent(keyword);
			}else if(condition.equals("title")) {//제목 검색
				dto.setTitle(keyword);
			}else if(condition.equals("writer")) {//작성자 검색
				dto.setWriter(keyword);
			}
			
			String encodedKeyword=null;
			try {
				encodedKeyword=URLEncoder.encode(keyword, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			//키워드와 검색조건을 request 에 담는다. 
			request.setAttribute("keyword", keyword);
			request.setAttribute("encodedKeyword", encodedKeyword);
			request.setAttribute("condition", condition);
		}			
		
		//보여줄 페이지의 번호
		int pageNum=1; // pageNum=현재 페이지 번호
		//보여줄 페이지의 번호가 파라미터로 전달되는지 읽어와 본다.	
		String strPageNum=request.getParameter("pageNum");
		if(strPageNum != null){//페이지 번호가 파라미터로 넘어온다면
			//페이지 번호를 설정한다.
			pageNum=Integer.parseInt(strPageNum);
		}
		//보여줄 페이지 데이터의 시작 ResultSet row 번호
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT; // PAGE_ROW_COUNT가 공차
		//보여줄 페이지 데이터의 끝 ResultSet row 번호
		int endRowNum=pageNum*PAGE_ROW_COUNT;
		
		//전체 row 의 갯수를 읽어온다.
		int totalRow=noticeDao.getCount(dto);
		//전체 페이지의 갯수 구하기
		int totalPageCount=
				(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		//시작 페이지 번호
		int startPageNum=
			1+((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//끝 페이지 번호
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		//끝 페이지 번호가 잘못된 값이라면 
		if(totalPageCount < endPageNum){
			endPageNum=totalPageCount; //보정해준다. 
		}		
		// CafeDto 객체에 위에서 계산된 startRowNum 과 endRowNum 을 담는다.
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);
		
		//1. DB 에서 글 목록을 얻어온다. (글목록을 얻어오는 DAO를 만든다.)
		List<NoticeDto> list=noticeDao.getList(dto);
		//2. view page 에 필요한 값을 request 에 담아둔다.(forward 이동)
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPageNum", startPageNum);
		request.setAttribute("endPageNum", endPageNum);
		request.setAttribute("totalPageCount", totalPageCount);		
		request.setAttribute("list", list);
		//전체 글의 갯수도 담기
		request.setAttribute("totalRow", totalRow);
		//Client IP주소 얻어오기
		String ipAddress=request.getRemoteAddr();
		System.out.println("아이디 :"+(String)request.getSession().getAttribute("id")+", 클라이언트 IP 주소: "+ipAddress);
		request.setAttribute("ipAddress", ipAddress);
	}

	

	@Override
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		//1. 삭제할 글 번호를 읽어온다.
		int num=Integer.parseInt(request.getParameter("num"));
		//세션의 아이디와 글 작성자를 비교해서 같을 때만 삭제한다.
		String id=(String)request.getSession().getAttribute("id");
		//글작성자 (num을 전달해서 정보 얻어오고 dto로 작성자를 얻어온다.)
		String writer=noticeDao.getData(num).getWriter();
		//아이디와 글 작성자가 같은지 여부
		boolean isEqual=id.equals(writer);
		if(!isEqual){
			throw new CanNotDeleteException();
		}
		//2. DB 에서 삭제한다.
		int isSuccess=noticeDao.delete(num);
		if(isSuccess!=0) {
			request.setAttribute("isSuccess", true);
		}else {
			request.setAttribute("isSuccess", false);
		}
	}

	@Override
	public void updateform(HttpServletRequest request, HttpServletResponse response) {
		//파라미터로 전달되는 수정할 글번호를 읽어온다.
		int num=Integer.parseInt(request.getParameter("num"));
		request.getSession().setAttribute("num",num);
		
		String pageNum=request.getParameter("pageNum");
		request.getSession().setAttribute("pageNum", pageNum);
		
		String title=noticeDao.getData(num).getTitle();
		request.getSession().setAttribute("title", title);
		//글작성자 (num을 전달해서 정보 얻어오고 dto로 작성자를 얻어온다.)
		String writer=noticeDao.getData(num).getWriter();
		request.getSession().setAttribute("writer", writer);
		
		String content=noticeDao.getData(num).getContent();
		request.getSession().setAttribute("content", content);
		
		//세션의 아이디와 글 작성자를 비교해서 같을 때만 삭제한다.
		String id=(String)request.getSession().getAttribute("id");

		//아이디와 글 작성자가 같은지 여부
		boolean isEqual=id.equals(writer);
		if(!isEqual){
			//에러를 응답하고 (SC_FORBIDDEN => 금지된 요청{403(상수값으로 얻어오는 것)}으로 응답하는 것)
			try {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "남의 글 삭제 하기 없기!");
			} catch (IOException e) {
				e.printStackTrace();
			}
			//메소드를 종료한다.
			return;
		}
		//DB 에서 수정할 글 정보를 얻어온다.
		NoticeDto dto=noticeDao.getData(num);
		request.getSession().setAttribute("dto",dto);
	}

	@Override
	public void update(HttpServletRequest request) {
		//1. 폼전송되는 수정 반영할 글 정보를 파라미터에서 얻어온다.
		int num=Integer.parseInt(request.getParameter("num"));
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String pageNum=request.getParameter("pageNum");
		request.getSession().setAttribute("pageNum", pageNum);
		//2. DB 에 수정반영하고 //수정반영 하는 Dao 만들어야 함
		NoticeDto dto = new NoticeDto();
		dto.setNum(num);
		dto.setTitle(title);
		dto.setContent(content);
		int isSuccess=noticeDao.update(dto);
		if(isSuccess!=0) {
			request.setAttribute("isSuccess", true);
		}else {
			request.setAttribute("isSuccess", false);
		}
	}

	@Override
	public void addViewCount(HttpServletRequest request, int num) {
		noticeDao.addViewCount(num);		
	}
	
	@Override
	public void getDetail(HttpServletRequest request) {
		//파라미터로 전달되는 글번호
		int num=Integer.parseInt(request.getParameter("num"));

		//검색과 관련된 파라미터를 읽어와 본다.
		String keyword=request.getParameter("keyword");
		String condition=request.getParameter("condition");

		//CafeDto 객체 생성 (select 할때 필요한 정보를 담기 위해)
		NoticeDto dto=new NoticeDto();

		if(keyword != null) {//검색 키워드가 전달된 경우
			if(condition.equals("titlecontent")) {//제목+내용 검색
				dto.setTitle(keyword);
				dto.setContent(keyword);
			}else if(condition.equals("title")) {//제목 검색
				dto.setTitle(keyword);
			}else if(condition.equals("writer")) {//작성자 검색
				dto.setWriter(keyword);
			}
			//request 에 검색 조건과 키워드 담기
			request.setAttribute("condition", condition);
			
			String encodedKeyword=null;
			try {
				encodedKeyword=URLEncoder.encode(keyword, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			//인코딩된 키워드와 인코딩 안된 키워드를 모두 담는다.
			request.setAttribute("encodedKeyword", encodedKeyword);
			request.setAttribute("keyword", keyword);
		}		
		//CafeDto 에 글번호도 담기
		dto.setNum(num);
		//조회수 1 증가 시키기
		noticeDao.addViewCount(num);
		//글정보를 얻어와서
		NoticeDto dto2=noticeDao.getData(dto);
		//request 에 글정보를 담고 
		request.setAttribute("dto", dto2);
	}



	@Override
	public void saveContent(NoticeDto dto) {
		noticeDao.insert(dto);
	}

}
