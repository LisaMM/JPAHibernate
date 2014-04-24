package be.vdab.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import be.vdab.services.DocentService;

/**
 * Servlet implementation class DocentVanTotWeddeServlet
 */
@WebServlet("/docenten/vantotwedde.htm")
public class DocentVanTotWeddeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/docenten/vantotwedde.jsp";
	private final DocentService docentService = new DocentService();
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//voorlopige dummy waarden meegeven
		request.setAttribute("docenten", docentService.findByWedde(null, null));
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
