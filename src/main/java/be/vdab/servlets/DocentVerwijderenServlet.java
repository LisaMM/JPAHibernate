package be.vdab.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import be.vdab.services.DocentService;

/**
 * Servlet implementation class DocentVerwijderenServlet
 */
@WebServlet("/docenten/verwijderen.htm")
public class DocentVerwijderenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DocentService docentService = new DocentService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		docentService.delete(Long.parseLong(request.getParameter("docentNr")));
		response.sendRedirect(response.encodeRedirectURL(request.getContextPath()));
	}

}
