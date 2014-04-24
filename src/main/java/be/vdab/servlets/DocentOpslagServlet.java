package be.vdab.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import be.vdab.exceptions.DocentNietGevondenException;
import be.vdab.services.DocentService;

/**
 * Servlet implementation class DocentOpslagServlet
 */
@WebServlet("/docent/opslag.htm")
public class DocentOpslagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String VIEW = "/WEB-INF/JSP/docenten/opslag.jsp";
	private final DocentService docentService = new DocentService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BigDecimal percentage = null;
		List<String> fouten = new ArrayList<>();
		try {
			percentage = new BigDecimal(request.getParameter("percentage"));
			if (percentage.compareTo(BigDecimal.ZERO) <= 0) {
				fouten.add("Percentage moet een positief getal zijn");
			}
		} catch (NumberFormatException ex) {
			fouten.add("Percentage moet een getal zijn");
		}
		long docentNr = Long.parseLong(request.getParameter("docentNr"));
		if (fouten.isEmpty()) {
			try {
				docentService.opslag(docentNr, percentage);
			} catch (DocentNietGevondenException ex) {
				fouten.add("Docent niet gevonden");
			}
		}
		if (fouten.isEmpty()) {
			response.sendRedirect(response.encodeRedirectURL(request
					.getContextPath()));
		} else {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}
}
