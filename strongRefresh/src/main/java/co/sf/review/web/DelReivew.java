package co.sf.review.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.sf.common.Control;
import co.sf.review.service.ReviewService;
import co.sf.review.service.ReviewServiceImpl;

public class DelReivew implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/json;charset=utf-8");
		
		String no = req.getParameter("no");
		
		ReviewService svc = new ReviewServiceImpl();
		
		if(svc.removeReview(no)) {
			System.out.println("삭제 성공");
			resp.sendRedirect("review.do");
		}else {
			System.out.println("삭제 실패");
			resp.sendRedirect("review.do");
		}
	}
	
}