package com.kh.bvengers.board.controller;

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
import com.kh.bvengers.board.model.vo.Board;
import com.kh.bvengers.user.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class InsertNoticeServlet
 */
@WebServlet("/insertNotice")
public class InsertNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertNoticeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		if(ServletFileUpload.isMultipartContent(request)) {

			int maxSize = 1024*1024*10;

			String root = request.getSession().getServletContext().getRealPath("/");

			String saveSrc = root + "thumbnail_uploadFiles/";

			MultipartRequest multiRequest = new MultipartRequest(request,saveSrc,maxSize,"UTF-8",new com.kh.bvengers.common.MyFileRenamePolicy());
			//저장파일 array
			ArrayList<String> saveFiles = new ArrayList<String>();
			//원본파일 array
			ArrayList<String> originFiles = new ArrayList<String>();

			Enumeration<String> files = multiRequest.getFileNames();

			while(files.hasMoreElements()) {


				String name = files.nextElement();
				if(!name.equals("files")&&name!=null) {
				saveFiles.add(multiRequest.getFilesystemName(name));
				originFiles.add(multiRequest.getOriginalFileName(name));

				}else {

				}
				}

			String multiTitle = multiRequest.getParameter("title");
			String multiContent = multiRequest.getParameter("content");
			String uno = ((Member)(request.getSession().getAttribute("loginUser"))).getMemberNo();
			String postCode = multiRequest.getParameter("hiddenCode");

			Board b = new Board();
			b.setPostsTitle(multiTitle);
			b.setContents(multiContent);
			b.setMemberNo(Integer.parseInt(uno));
			b.setPostsCode(postCode);

			ArrayList<Attachment>fileList = new ArrayList<Attachment>();

			for(int i = originFiles.size() -1; i>=0; i--) {
				Attachment at = new Attachment();
				at.setFileSrc(saveSrc);
				at.setOrginFileName(originFiles.get(i));
				at.setNewFileName(saveFiles.get(i));

				fileList.add(at);

			}
			int result = new com.kh.bvengers.board.model.service.BoardService().insertNotice(b,fileList);

			if(result > 0) {
				response.sendRedirect(request.getContextPath()+"/smnl.mm");
			}else {
				//실패시 저장된 사진 삭제
				for(int i = 0; i < saveFiles.size(); i++) {
					File failedFile = new File(saveSrc + saveFiles.get(i));
					failedFile.delete();
				}

				request.setAttribute("msg", "사진 게시판 등록 실패!");
				request.getRequestDispatcher("views/common/errorpage.jsp").forward(request, response);
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






















