package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MuseumDAO;

@WebServlet("/")
public class MuseumController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MuseumController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
         doPro(request,response);
      }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
         request.setCharacterEncoding("UTF-8");
         doPro(request, response);
      }
    
    protected void doPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        
        String context = request.getContextPath(); //톰캣의 contextpath를 가져온다.
        
        String command = request.getServletPath(); //경로 맨 끝 파일명을 가져온다.
        String site = null;
        
        System.out.println(context + ", " + command);
        
        MuseumDAO museum = new MuseumDAO();
        
        switch(command) {
        case "/list":
        	site = museum.selectAll(request, response);
        	break;
        case "/add":
        	site = "add.jsp";
        	break;
        case "/insert":
      	  int result = museum.insert(request, response);
      	  response.setContentType("text/html; charset=UTF-8");
      	  PrintWriter out = response.getWriter();
      	  String r_confirm = request.getParameter("r_confirm");
  			if (result == 1) {
  				if (r_confirm.equals("Y")) {
  					out.println("<script>");
  	  				out.println(" alert('Your reservation is complete Please pay on site'); location.href='" + context + "';  ");
  	  				out.println("</script>");
  	  				out.flush();
  				} else {
  				out.println("<script>");
  				out.println(" alert('xxxxxxxxxxxxx'); location.href='" + context + "';  ");
  				out.println("</script>");
  				out.flush();
  				}
  			} else {
  				out.println("<script>");
  				out.println("alert('정보를 지우고 처음부터 다시 입력합니다!'); location.reload();='" + context + "'; ");
  				out.println("</script>");
  				out.flush();
  			}
          break;
        case "/customerlist":
        	site = museum.selectCustomer(request, response);
        	break;
        case "/modify":
      	   site = museum.modify(request, response);
      	   break;
        case "/update":
			int result1 = museum.update(request, response);
			response.setContentType("text/html; charset=UTF-8");
				out = response.getWriter();
			if (result1 == 1) {
				out.println("<script>");
				out.println(" alert('회원수정이 완료 되었습니다!'); location.href='" + context + "';  ");
				out.println("</script>");
				out.flush();
			} else {
				out.println("<script>");
				out.println("alert('수정실패!'); location.href='" + context + "'; ");
				out.println("</script>");
				out.flush();
			}
			break;
        case "/delete":
			int result2 = museum.delete(request, response);
			response.setContentType("text/html; charset=UTF-8");
			out = response.getWriter();
			if (result2 == 1) {
				out.println("<script>");
				out.println(" alert('회원삭제가 완료 되었습니다!'); location.href='" + context + "';  ");
				out.println("</script>");
				out.flush();
			} else {
				out.println("<script>");
				out.println("alert('삭제실패!'); location.href='" + context + "'; ");
				out.println("</script>");
				out.flush();
			}
			break;
        case "/home":
        	site = "index.jsp";
        	break;
       }
         
         
         getServletContext().getRequestDispatcher("/" + site).forward(request, response);
     }
 }
