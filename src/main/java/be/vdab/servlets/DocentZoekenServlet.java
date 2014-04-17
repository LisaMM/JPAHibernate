package be.vdab.servlets;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import be.vdab.dao.DocentDAO;
import be.vdab.entities.Docent;

/**
 * Servlet implementation class DocentZoekenServlet
 */
@WebServlet("/docenten/zoeken.htm")
public class DocentZoekenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/docenten/zoeken.jsp";
	private final DocentDAO docentDAO = new DocentDAO();
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (! request.getParameterMap().isEmpty()) {
			List<String> fouten = new ArrayList<>();
			try {
				long docentNr = Long.parseLong(request.getParameter("docentNr"));
				Docent docent = docentDAO.read(docentNr);
				if (docent == null) {
					fouten.add("Docent niet gevonden");
				} else {
					request.setAttribute("docent", docent);
				}
			} catch (NumberFormatException ex) {
				fouten.add("Tik een getal");
			}
			if (! fouten.isEmpty()) {
				request.setAttribute("fouten", fouten);
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
