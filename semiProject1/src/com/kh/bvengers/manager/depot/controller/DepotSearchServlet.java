package com.kh.bvengers.manager.depot.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.bvengers.manager.depot.model.servies.DepotService;
import com.kh.bvengers.manager.depot.model.vo.Depot;
import com.kh.bvengers.manager.depot.model.vo.DepotPageInfo;


@WebServlet("/search.dp")
public class DepotSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DepotSearchServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String root = request.getParameter("root");
		String session= request.getParameter("session");
		String floor = request.getParameter("floor");
		String room = request.getParameter("room");
		String insertDate = request.getParameter("insertDate");
		String releaseDate = request.getParameter("releaseDate");
		String productCode = request.getParameter("productCode");
		String currentPage1 = request.getParameter("currentPage");
		
		String location = "";
		
		if(root!=null&&session.equals("")&&floor.equals("")&&room.equals("")) {
			location = root;
		}else if(root.equals("")&&session!=null&&floor.equals("")&&room.equals("")) {
			location = session;
		}else if(root.equals("")&&session.equals("")&&floor!=null&& room.equals("")) {
			location = floor;
		}else if(root.equals("")&&session.equals("")&&floor.equals("")&&room!=null){
			location = room;
		}else if(root!=null&&session!=null&&floor.equals("")&&room.equals("")) {
			location = root+"-"+session;
		}else if(root!=null&&session.equals("")&&floor!=null&& room.equals("")) {
			location = root+"-_-"+floor;
		}else if (root!=null&&session.equals("")&&floor.equals("")&&room!=null) {
			location = root +"-_-_-"+room;
		}else if(root.equals("")&&session!=null&&floor!=null&& room.equals("")) {
			location = "__-"+session+"-"+floor+"-_";
		}else if(root.equals("")&&session!=null&&floor.equals("")&&room!=null) {
			location = "__-"+session+"-_-"+room;
		}else if (root.equals("")&&session.equals("")&&floor!=null&&room!=null) {
			location = "__-_"+floor+room;
		}else if (root!=null&&session!=null&&floor!=null&&room.equals("")) {
			location = root+"-"+session+"-"+floor+"-_";
		}else if(root!=null&&session!=null&&floor.equals("")&&room!=null){
			location = root+"-"+session+"-_-"+room;
		}else if(root!=null&&session.equals("")&&floor!=null&&room!=null) {
			location = root+"-_-"+floor+"-"+room;
		}else if(root.equals("")&&session!=null&&floor!=null&&room!=null) {
			location = "__-"+session+"-"+floor+"-"+room;
		}else if(!root.equals("")&&!session.equals("")&&!floor.equals("")&&!room.equals("")) {
			location =root+"-"+session+"-"+floor+"-"+room;
		}
		
		int currentPage;		
		int limit;				
		int maxPage;			
		int startPage;			
		int endPage;			
	
		if(currentPage1==null) {		
			currentPage=1;						
		}else {
			currentPage = Integer.parseInt(currentPage1);
		}
		
		limit = 10;
		
		int listCount = 0;	
		HashMap<String, Object> hmap = null;
		ArrayList <Depot> list = null;
		
		DepotPageInfo pi =null;
		
		
		if(location!=null&&insertDate.equals("")&&releaseDate.equals("")&&productCode.equals("")) {
			listCount = new DepotService().searchL(location);
			
			startPage = (((int)((double) currentPage / limit + 0.9)) - 1) * 10 + 1;
			
			endPage = startPage + 10 - 1;
			maxPage = (int)((double)listCount/limit+0.9);			
			
			if(maxPage < endPage) {
				endPage = maxPage;
			}
			
			hmap =new HashMap<String,Object>();
			pi = new DepotPageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
			list = new DepotService().searchListL(location,currentPage,limit);
			hmap.put("pi", pi);
			hmap.put("list", list);
			
		}else if(location.equals("")&&insertDate!=null&&releaseDate.equals("")&&productCode.equals("")) {
			listCount = new DepotService().searchId(insertDate);
			
			maxPage = (int)((double)listCount/limit+0.9);
			
			startPage = (((int)((double) currentPage / limit + 0.9)) - 1) * 10 + 1;
			
			endPage = startPage + 10 - 1;
			
			if(maxPage < endPage) {
				endPage = maxPage;
			}
			hmap =new HashMap<String,Object>();
			pi = new DepotPageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
			list = new DepotService().searchListId(insertDate,currentPage,limit);
			hmap.put("pi", pi);
			hmap.put("list", list);
		}else if(location.equals("")&&insertDate.equals("")&&releaseDate!=null&&productCode.equals("")) {
			listCount = new DepotService().searchRd(releaseDate);
			
			maxPage = (int)((double)listCount/limit+0.9);
			
			startPage = (((int)((double) currentPage / limit + 0.9)) - 1) * 10 + 1;
			
			endPage = startPage + 10 - 1;
			
			if(maxPage < endPage) {
				endPage = maxPage;
			}
			hmap =new HashMap<String,Object>();
			pi = new DepotPageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
			list = new DepotService().searchListRd(releaseDate,currentPage,limit);
			hmap.put("pi", pi);
			hmap.put("list", list);
		}else if(location.equals("")&&insertDate.equals("")&&releaseDate.equals("")&&productCode!=null) {
			listCount = new DepotService().searchP(productCode);
			
			maxPage = (int)((double)listCount/limit+0.9);
			
			startPage = (((int)((double) currentPage / limit + 0.9)) - 1) * 10 + 1;
			
			endPage = startPage + 10 - 1;
			
			if(maxPage < endPage) {
				endPage = maxPage;
			}
			hmap =new HashMap<String,Object>();
			pi = new DepotPageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
			list = new DepotService().searchListP(productCode,currentPage,limit);
			hmap.put("pi", pi);
			hmap.put("list", list);
		}else if(location!=null&&insertDate!=null&&releaseDate.equals("")&&productCode.equals("")) {
			listCount = new DepotService().searchLId(location,insertDate);
			
			maxPage = (int)((double)listCount/limit+0.9);
			
			startPage = (((int)((double) currentPage / limit + 0.9)) - 1) * 10 + 1;
			
			endPage = startPage + 10 - 1;
			
			if(maxPage < endPage) {
				endPage = maxPage;
			}
			hmap =new HashMap<String,Object>();
			pi = new DepotPageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
			list = new DepotService().searchListLId(location,insertDate,currentPage,limit);
			hmap.put("pi", pi);
			hmap.put("list", list);
		}else if(location!=null&&insertDate.equals("")&&releaseDate!=null&&productCode.equals("")) {
			listCount = new DepotService().searchLRd(location,releaseDate);
			
			maxPage = (int)((double)listCount/limit+0.9);
			
			startPage = (((int)((double) currentPage / limit + 0.9)) - 1) * 10 + 1;
			
			endPage = startPage + 10 - 1;
			
			if(maxPage < endPage) {
				endPage = maxPage;
			}
			hmap =new HashMap<String,Object>();
			pi = new DepotPageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
			list = new DepotService().searchListLRd(location,releaseDate,currentPage,limit);
			hmap.put("pi", pi);
			hmap.put("list", list);
		}else if(location!=null&&insertDate.equals("")&&releaseDate.equals("")&&productCode!=null) {
			listCount = new DepotService().searchLP(location,productCode);
			
			maxPage = (int)((double)listCount/limit+0.9);
			
			startPage = (((int)((double) currentPage / limit + 0.9)) - 1) * 10 + 1;
			
			endPage = startPage + 10 - 1;
			
			if(maxPage < endPage) {
				endPage = maxPage;
			}
			hmap =new HashMap<String,Object>();
			pi = new DepotPageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
			list = new DepotService().searchListLP(location,productCode,currentPage,limit);
			hmap.put("pi", pi);
			hmap.put("list", list);
		}else if(location.equals("")&&insertDate!=null&&releaseDate!=null&&productCode.equals("")) {
			listCount = new DepotService().searchIdRd(insertDate,releaseDate);
			
			maxPage = (int)((double)listCount/limit+0.9);
			
			startPage = (((int)((double) currentPage / limit + 0.9)) - 1) * 10 + 1;
			
			endPage = startPage + 10 - 1;
			
			if(maxPage < endPage) {
				endPage = maxPage;
			}
			hmap =new HashMap<String,Object>();
			pi = new DepotPageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
			list = new DepotService().searchListIdRd(insertDate,releaseDate,currentPage,limit);
			hmap.put("pi", pi);
			hmap.put("list", list);
		}else if(location.equals("")&&insertDate!=null&&releaseDate.equals("")&&productCode!=null) {
			listCount = new DepotService().searchIdp(insertDate,productCode);
			
			maxPage = (int)((double)listCount/limit+0.9);
			
			startPage = (((int)((double) currentPage / limit + 0.9)) - 1) * 10 + 1;
			
			endPage = startPage + 10 - 1;
			
			if(maxPage < endPage) {
				endPage = maxPage;
			}
			hmap =new HashMap<String,Object>();
			pi = new DepotPageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
			list = new DepotService().searchListIdp(insertDate,productCode,currentPage,limit);
			hmap.put("pi", pi);
			hmap.put("list", list);
		}else if(location.equals("")&&insertDate.equals("")&&releaseDate!=null&&productCode!=null) {
			listCount = new DepotService().searchRdP(releaseDate,productCode);
			
			maxPage = (int)((double)listCount/limit+0.9);
			
			startPage = (((int)((double) currentPage / limit + 0.9)) - 1) * 10 + 1;
			
			endPage = startPage + 10 - 1;
			
			if(maxPage < endPage) {
				endPage = maxPage;
			}
			hmap =new HashMap<String,Object>();
			pi = new DepotPageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
			list = new DepotService().searchListRdP(releaseDate,productCode,currentPage,limit);
			hmap.put("pi", pi);
			hmap.put("list", list);
		}else if(location!=null&&insertDate!=null&&releaseDate!=null&&productCode.equals("")) {
			listCount = new DepotService().searchLIdRd(location,insertDate,releaseDate);
			
			maxPage = (int)((double)listCount/limit+0.9);
			
			startPage = (((int)((double) currentPage / limit + 0.9)) - 1) * 10 + 1;
			
			endPage = startPage + 10 - 1;
			
			if(maxPage < endPage) {
				endPage = maxPage;
			}
			hmap =new HashMap<String,Object>();
			pi = new DepotPageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
			list = new DepotService().searchListLIdRd(location,insertDate,releaseDate,currentPage,limit);
			hmap.put("pi", pi);
			hmap.put("list", list);
		}else if(location!=null&&insertDate!=null&&releaseDate.equals("")&&productCode!=null) {
			listCount = new DepotService().searchLIdP(location,insertDate,productCode);
			
			maxPage = (int)((double)listCount/limit+0.9);
			
			startPage = (((int)((double) currentPage / limit + 0.9)) - 1) * 10 + 1;
			
			endPage = startPage + 10 - 1;
			
			if(maxPage < endPage) {
				endPage = maxPage;
			}
			hmap =new HashMap<String,Object>();
			pi = new DepotPageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
			list = new DepotService().searchListLIdP(location,insertDate,productCode,currentPage,limit);
			hmap.put("pi", pi);
			hmap.put("list", list);
		}else if(location!=null&&insertDate.equals("")&&releaseDate!=null&&productCode!=null) {
			listCount = new DepotService().searchLRdP(location,releaseDate,productCode);
			
			maxPage = (int)((double)listCount/limit+0.9);
			
			startPage = (((int)((double) currentPage / limit + 0.9)) - 1) * 10 + 1;
			
			endPage = startPage + 10 - 1;
			
			if(maxPage < endPage) {
				endPage = maxPage;
			}
			hmap =new HashMap<String,Object>();
			pi = new DepotPageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
			list = new DepotService().searchListLRdP(location,releaseDate,productCode,currentPage,limit);
			hmap.put("pi", pi);
			hmap.put("list", list);
		}else if(location.equals("")&&insertDate!=null&&releaseDate!=null&&productCode!=null) {
			listCount = new DepotService().searchIdRdP(insertDate,releaseDate,productCode);
			
			maxPage = (int)((double)listCount/limit+0.9);
			
			startPage = (((int)((double) currentPage / limit + 0.9)) - 1) * 10 + 1;
			
			endPage = startPage + 10 - 1;
			
			if(maxPage < endPage) {
				endPage = maxPage;
			}
			hmap =new HashMap<String,Object>();
			pi = new DepotPageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
			list = new DepotService().searchListIdRdP(insertDate,releaseDate,productCode,currentPage,limit);
			hmap.put("pi", pi);
			hmap.put("list", list);
		}else if(location!=null&&insertDate!=null&&releaseDate!=null&&productCode!=null) {
			listCount = new DepotService().searchTotal(location,insertDate,releaseDate,productCode);
			
			maxPage = (int)((double)listCount/limit+0.9);
			
			startPage = (((int)((double) currentPage / limit + 0.9)) - 1) * 10 + 1;
			
			endPage = startPage + 10 - 1;
			
			if(maxPage < endPage) {
				endPage = maxPage;
			}
			hmap =new HashMap<String,Object>();
			pi = new DepotPageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
			list = new DepotService().searchListTotal(location,insertDate,releaseDate,productCode,currentPage,limit);
			hmap.put("pi", pi);
			hmap.put("list", list);
		}else {
			listCount = new DepotService().getCheckListCount();
			
			maxPage = (int)((double)listCount/limit+0.9);
			
			startPage = (((int)((double) currentPage / limit + 0.9)) - 1) * 10 + 1;
			
			endPage = startPage + 10 - 1;
			
			if(maxPage < endPage) {
				endPage = maxPage;
			}
			
			pi = new DepotPageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
			list = new DepotService().selectCheckAll(currentPage,limit);
			hmap.put("pi", pi);
			hmap.put("list", list);
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		new Gson().toJson(hmap,response.getWriter());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
