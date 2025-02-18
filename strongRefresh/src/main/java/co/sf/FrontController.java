package co.sf;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.sf.cart.web.AddCart;
import co.sf.cart.web.CartCntIcon;
import co.sf.cart.web.CartForm;
import co.sf.cart.web.ChangeCnt;
import co.sf.cart.web.RemoveCart;
import co.sf.common.Control;
import co.sf.heart.web.HeartForm;
import co.sf.heart.web.RemoveHeart;
import co.sf.order.web.AddAddress;
import co.sf.order.web.CreateOrder;
import co.sf.order.web.OrderDetailForm;
import co.sf.order.web.OrderForm;
import co.sf.order.web.OrderHistory;
import co.sf.order.web.RemoveOrderDetail;
import co.sf.order.web.RemoveSelectedCartItems;
import co.sf.order.web.Thankyou;
import co.sf.order.web.UpdateOrderStatus;
import co.sf.product.web.CountCategory;
import co.sf.product.web.ProductDetailAjax;
import co.sf.product.web.ProductDetailForm;
import co.sf.product.web.ProductForm;
import co.sf.product.web.ProductList;
import co.sf.product.web.ProductTotal;
import co.sf.product.web.cartProductAjax;
import co.sf.product.web.heartProductAjax;
import co.sf.product.web.prdQnAAjax;
import co.sf.product.web.prdReviewAjax;
import co.sf.product.web.srcPrd;
import co.sf.qna.web.AddQna;
import co.sf.qna.web.DelQna;
import co.sf.qna.web.ModifyQna;
import co.sf.qna.web.ModifyQnaForm;
import co.sf.qna.web.ModifyQnaList;
import co.sf.qna.web.QnaDetailForm;
import co.sf.qna.web.QnaForm;
import co.sf.qna.web.QnaList;
import co.sf.review.web.AddReview;
import co.sf.review.web.DelReivew;
import co.sf.review.web.ModifyReview;
import co.sf.review.web.ModifyReviewForm;
import co.sf.review.web.ModifyReviewList;
import co.sf.review.web.ReviewForm;
import co.sf.review.web.ReviewList;
import co.sf.review.web.reviewDetailForm;
import co.sf.user.web.Album1;
import co.sf.user.web.Album10;
import co.sf.user.web.Album12;
import co.sf.user.web.Album15;
import co.sf.user.web.Album2;
import co.sf.user.web.Album5;
import co.sf.user.web.AlbumForm;
import co.sf.user.web.CheckIdAjax;
import co.sf.user.web.FindIdControl;
import co.sf.user.web.FindIdForm;
import co.sf.user.web.FindPwControl;
import co.sf.user.web.FindPwForm;
import co.sf.user.web.JoinControl;
import co.sf.user.web.JoinForm;
import co.sf.user.web.LoginControl;
import co.sf.user.web.LoginForm;
import co.sf.user.web.LogoutControl;
import co.sf.user.web.ModifyControl;
import co.sf.user.web.ModifyMember;
import co.sf.user.web.MyPageForm;
import co.sf.user.web.TemporaryPwForm;
import co.sf.web.MainControl;

//front -> 요청 url(*.do) - 실행컨트롤 매칭.
//main.do -> FrontController -> /web-inf/public/main.jsp
//객체생성 - init -> service - destroy
public class FrontController extends HttpServlet {
	private Map<String, Control> map; // key: url, value: control

	public FrontController() {
		map = new HashMap<>();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// 메인 홈페이지
		map.put("/main.do", new MainControl());		
		
		
		// 찜 화면
		map.put("/heart.do", new HeartForm());
		// 찜 삭제
		map.put("/removeHeart.do", new RemoveHeart());		
		// 찜 화면에서 카드 담기 클릭시 카트 목록에 담기
		map.put("/addCart.do", new AddCart());
		
		// 카트 화면
		map.put("/cart.do", new CartForm());	
		// 카트 삭제
		map.put("/removeCart.do", new RemoveCart());
		// 카트 수량 변경
		map.put("/changeCnt.do", new ChangeCnt());
		// 카트 수량 아이콘에 출력
		map.put("/cartCntIcon.do", new CartCntIcon());
		// 주문생성
		map.put("/createOrder.do", new CreateOrder());
		
		
		
		
		// 주문 페이지
		map.put("/order.do", new OrderForm());
		// 주문 리스트
		map.put("/orderdetail.do", new OrderDetailForm());
		// 주문하기 = > 
		// 새로운 배송지
		map.put("/addAddress.do", new AddAddress());
		// 주문성공 페이지
		map.put("/thankyou.do", new Thankyou());
		// 주문조회 페이지
		map.put("/orderHistory.do", new OrderHistory());
		// 주문 내역 페이지 삭제
		map.put("/removeOrderDetail.do", new RemoveOrderDetail());
		// 주문 완료시 선택 카트 아이템 삭제
		map.put("/removeSelectedCartItems.do", new RemoveSelectedCartItems());
		// 주문 완료 시 주문 상태 변환
		map.put("/updateOrderStatus.do", new UpdateOrderStatus());
		
		
		// 제품 페이지
		map.put("/productForm.do", new ProductForm());
		// 제품 페이지에 제품 출력
		map.put("/productListJson.do", new ProductList());
		map.put("/productTotalCnt.do", new ProductTotal());
		
		// 제품 상세페이지
		map.put("/productDetail.do", new ProductDetailForm());
		map.put("/productDetailAjax.do", new ProductDetailAjax());
		// 제품 찜하기
		map.put("/heartProductAjax.do", new heartProductAjax());
		// 제품 카트에 담기
		map.put("/cartProductAjax.do", new cartProductAjax());
		// 제품 리뷰
		map.put("/prdReview.do", new prdReviewAjax());
		// 제품 QNA
		map.put("/prdQnA.do", new prdQnAAjax());
		// 제품 검색
		map.put("/srcPrd.do", new srcPrd());
		// 카테고리 갯수
		map.put("/countCategory.do", new CountCategory());
		

		// 로그인
		map.put("/loginForm.do", new LoginForm());
		map.put("/login.do", new LoginControl());
		//로그아웃
		map.put("/logout.do", new LogoutControl());

		// 회원가입
		map.put("/joinForm.do", new JoinForm());
		map.put("/join.do", new JoinControl());

		// 아이디 중복체크
		map.put("/checkIdAjax.do", new CheckIdAjax());
		// 아이디찾기
		map.put("/findId.do", new FindIdForm());
		map.put("/findIdResult.do", new FindIdControl());
		// 비밀번호찾기
		map.put("/findPw.do", new FindPwForm());
		map.put("/temporaryPw.do", new TemporaryPwForm());
		map.put("/findPwResult.do", new FindPwControl());
		
		//마이페이지
		map.put("/mypage.do", new MyPageForm());
		//회원정보수정
		map.put("/modifyMember.do", new ModifyMember());
		map.put("/modify.do", new ModifyControl());
		//앨범
		map.put("/albumForm.do", new AlbumForm());
			//앨범- 썸네일10
			map.put("/album10.do", new Album10());
			//앨범- 썸네일1
			map.put("/album1.do", new Album1());
			//앨범- 썸네일 5
			map.put("/album5.do", new Album5());
			//앨범- 썸네일 15
			map.put("/album15.do", new Album15());
			//앨범- 썸네일 12
			map.put("/album12.do", new Album12());
			//앨범- 썸네일 2
			map.put("/album2.do", new Album2());
			
			//앨범- 썸네일 3
			//앨범- 썸네일 8
			//앨범- 썸네일 13
			
			//앨범- 썸네일 14
			//앨범- 썸네일 9
			//앨범- 썸네일 7
			

		// QNA
		map.put("/qna.do", new QnaForm());
		// QNA 목록 요청
		map.put("/qnaList.do", new QnaList());
		// QNA 추가페이지
		map.put("/qnaDetail.do", new QnaDetailForm());
		// QNA 추가
		map.put("/addQna.do", new AddQna());
		// QNA 수정페이지
		map.put("/modifyQnaForm.do", new ModifyQnaForm());
		// QNA 수정데이터
		map.put("/modifyQnaList.do", new ModifyQnaList());
		// QNA 수정
		map.put("/modifyQna.do", new ModifyQna());
		// QNA 삭제
		map.put("/delQna.do", new DelQna());

		
		// 리뷰
		map.put("/review.do", new ReviewForm());
		// 리뷰 목록 요청
		map.put("/reviewList.do", new ReviewList());
		// 리뷰 추가페이지
		map.put("/reviewDetail.do", new reviewDetailForm());
		// 리뷰 추가 -- 여기서부터 시작
		map.put("/addReview.do", new AddReview());
		// 리뷰 수정페이지
		map.put("/modifyReviewForm.do", new ModifyReviewForm());
		// 리뷰 수정데이터
		map.put("/modifyReviewList.do", new ModifyReviewList());
		// 리뷰 수정
		map.put("/modifyReview.do", new ModifyReview());
		// 리뷰 삭제
		map.put("/delReview.do", new DelReivew());
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/json;charset=utf-8");
		req.setCharacterEncoding("UTF-8");
		String uri = req.getRequestURI(); // http://localhost/BoardWeb/main.do
//		System.out.println("URI : " + uri); //BoardWeb/main.do
		String context = req.getContextPath(); // /BoardWeb -> project name;
//		System.out.println("context : " + context);
		String page = uri.substring(context.length()); // main.do
//		System.out.println("page : " + page);

		Control result = map.get(page);
		result.exec(req, resp);
	}
}
