import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestProject
 */
@WebServlet("/TestProject")
public class TestProject extends HttpServlet {
 private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestProject() {
        super();
        // TODO Auto-generated constructor stub
    }

 /**
  * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  // TODO Auto-generated method stub
  response.setCharacterEncoding("UTF-8");
  request.setCharacterEncoding("UTF-8");
  response.setContentType("text/html");
  if(request.getParameter("keyword")== null) {
   String requestUri = request.getRequestURI();
   request.setAttribute("requestUri", requestUri);
   request.getRequestDispatcher("Search.jsp").forward(request, response);
   return;
  }
  GoogleQuery google = new GoogleQuery(request.getParameter("keyword"));
  google.SetScore();
  ArrayList<WebPage> web=google.getWebPage();
  google.sort(); 
  String[][] s = new String[web.size()][2];
  request.setAttribute("query", s);
  int num = 0;
  for(int i=web.size()-1;i>=0;i--) {
      String key = web.get(i).name;
      String value = web.get(i).url;
      s[num][0] = key;
      s[num][1] = value;
      num++;
  }
  request.getRequestDispatcher("googleitem.jsp")
   .forward(request, response); 
  
 }

 /**
  * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  // TODO Auto-generated method stub
  doGet(request, response);
 }

}