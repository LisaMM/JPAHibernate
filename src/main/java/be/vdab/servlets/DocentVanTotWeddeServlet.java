package be.vdab.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import be.vdab.entities.Docent;
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
		if (! request.getParameterMap().isEmpty()) {
			List<String> fouten = new ArrayList<>();
			BigDecimal van = null;
			try {
				van = new BigDecimal(request.getParameter("van"));
			} catch (NumberFormatException ex) {
				fouten.add("Van moet een getal zijn");
			}
			BigDecimal tot = null;
			try {
				tot = new BigDecimal(request.getParameter("tot"));
			} catch (NumberFormatException ex) {
				fouten.add("Tot moet een getal zijn");
			}
			if (fouten.isEmpty()) {
				Iterable<Docent> docenten = docentService.findByWedde(van, tot);
				if (! docenten.iterator().hasNext()) {
					fouten.add("Geen docenten gevonden");
				} else {
					request.setAttribute("docenten", docenten);
				}
			}
			if (! fouten.isEmpty()) {
				request.setAttribute("fouten", fouten);
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
