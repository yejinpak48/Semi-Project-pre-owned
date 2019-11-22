package com.kh.bvengers.product.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.bvengers.board.model.vo.Attachment;
import com.kh.bvengers.board.model.vo.Posts;
import com.kh.bvengers.board.model.vo.PostsContents;
import com.kh.bvengers.common.MyFileRenamePolicy;
import com.kh.bvengers.product.model.service.ProductService;
import com.kh.bvengers.product.model.vo.Product;
import com.kh.bvengers.user.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class InsertProductPostServlet
 */
@WebServlet("/insert.po")
public class InsertProductPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertProductPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 1024 * 1024 * 20;

			String root = request.getSession().getServletContext().getRealPath("/");

			String savePath = root + "thumbnail_uploadFiles/";


			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());

			ArrayList<String> saveFiles = new ArrayList<String>();
			ArrayList<String> originFiles = new ArrayList<String>();

			Enumeration<String> files = multiRequest.getFileNames();

			while(files.hasMoreElements()) {
				String name = files.nextElement();
				if(multiRequest.getFilesystemName(name) != null) {
					saveFiles.add(multiRequest.getFilesystemName(name));
					originFiles.add(multiRequest.getOriginalFileName(name));
				}
			}

			int memberNo =  Integer.parseInt(((Member) (request.getSession().getAttribute("loginUser"))).getMemberNo());
			//로그인 기능 추가 시 위 주석 해제(현재 로그인한 유저 번호)
			String postsTitle = multiRequest.getParameter("postsTitle");				//posts 제목
			String productName = multiRequest.getParameter("productName");				//product 상품명
			int productMoney = Integer.parseInt(multiRequest.getParameter("productMoney"));//product 가격
			String contents = multiRequest.getParameter("contents");					//posts_contents 내용
			String keepDate = multiRequest.getParameter("keepDate");					//product 보관일자

			String accountHolder = multiRequest.getParameter("accountHolder");			//member 예금주
			String bankCode = multiRequest.getParameter("bankCode");					//member 은행명
			String accountNo = multiRequest.getParameter("accountNo");					//member 계좌번호

			String mainCate = multiRequest.getParameter("mainCate");					//product 상위 카테고리
			String subCate1 = multiRequest.getParameter("subCate1");					//product 카테고리1
			String subCate2 = multiRequest.getParameter("subCate2");					//product 카테고리2
			String subCate3 = multiRequest.getParameter("subCate3");					//product 카테고리3

			String productCode = "";												//product 상품코드
			String productCate = "";												//product 카테고리 코드

			if(mainCate.equals("pc")) {
				if(subCate1.equals("desktop")) {
					productCode = "11";
					productCate = "DESKTOP";
				}else if(subCate1.equals("pcEtc")) {
					productCode = "12";
					productCate = "PC_ETC";
				}
			}else if(mainCate.equals("laptop")) {
				if(subCate2.equals("case")) {
					productCode = "21";
					productCate = "CASE";
				}else if(subCate2.equals("notebook")) {
					productCode = "22";
					productCate = "NOTEBOOK";
				}else if(subCate2.equals("notebookEtc")) {
					productCode = "23";
					productCate = "NOTE_ETC";
				}

			}else {
				if(subCate3.equals("ha")) {
					productCode = "31";
					productCate = "HA";
				}else if(subCate3.equals("ka")) {
					productCode = "32";
					productCate = "KA";
				}else{
					productCode = "33";
					productCate = "CAMERA";
				}
			}

			int ran = 0;
			for(;;) {
				ran = (int) (Math.random() * 1000000);
				if(ran > 100000) {
					break;
				}
			}

			productCode = productCode + ran;

			//member테이블 객체 생성
			Member member = new Member();
			member.setMemberId(memberNo + "");
			member.setAccountHolder(accountHolder);
			member.setBankCode(bankCode);
			member.setAccountNo(accountNo);

			//posts테이블 객체 생성
			Posts posts = new Posts();
			posts.setPostsTitle(postsTitle);
			posts.setMemberNo(memberNo);

			//postsContents 객체 생성
			PostsContents postsContents = new PostsContents();
			postsContents.setContents(contents);

			//product 객체 생성
			Product product = new Product();
			product.setProductCode(productCode);
			product.setProductName(productName);
			product.setProductMoney(productMoney);
			product.setProductCate(productCate);
			product.setMemberNo(memberNo+"");
			product.setKeepDate(keepDate);

			//attachment 객체는 ArrayList 형태로 생성
			ArrayList<Attachment> fileList = new ArrayList<Attachment>();
			for(int i = originFiles.size()-1; i >= 0; i--) {
				Attachment at = new Attachment();
				at.setFileSrc(savePath);
				at.setOrginFileName(originFiles.get(i));
				String newName = saveFiles.get(i).substring(saveFiles.get(i).length() - 24, saveFiles.get(i).length());


				at.setNewFileName(newName);
				at.setPostsId(posts.getPostsId()+"");
				at.setProductCode(productCode);
				fileList.add(at);
			}


			int result = new ProductService().insertProductPost(member, posts, postsContents, product, fileList);

			if(result >0) {
				response.sendRedirect("index.jsp");
			}else {
				//실패 시 저장된 사진 삭제
				for(int i = 0; i < saveFiles.size(); i++) {
					File failedFile = new File(savePath + saveFiles.get(i));
					failedFile.delete();
				}

				request.setAttribute("msg", "상품 등록 실패!");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}





























