package be.vdab.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import be.vdab.entities.Docent;
import be.vdab.enums.Geslacht;
import be.vdab.services.DocentService;

/**
 * Servlet implementation class DocentToevoegenServlet
 */
@WebServlet("/docenten/toevoegen.htm")
public class DocentToevoegenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String VIEW = "/WEB-INF/JSP/docenten/toevoegen.jsp";
	private static final String REDIRECT_URL = "%s/docenten/toegevoegd.htm?docentNr=%d";
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
		request.setCharacterEncoding("UTF-8");
		List<String> fouten = new ArrayList<>();
		String voornaam = request.getParameter("voornaam");
		if (voornaam == null || voornaam.isEmpty()) {
			fouten.add("Voornaam verplicht");
		}
		String familienaam = request.getParameter("familienaam");
		if (familienaam == null || familienaam.isEmpty()) {
			fouten.add("Familienaam verplicht");
		}
		BigDecimal wedde = null;
		try {
			wedde = new BigDecimal(request.getParameter("wedde"));
			if (wedde.compareTo(BigDecimal.ZERO) < 0) {
				fouten.add("Wedde moet een positief getal zijn");
			}
		} catch (NumberFormatException ex) {
			fouten.add("Wedde moet een getal zijn");
		}
		String geslacht = request.getParameter("geslacht");
		if (geslacht == null) {
			fouten.add("Geslacht verplicht");
		}
		if (fouten.isEmpty()) {
			Docent docent = new Docent(voornaam, familienaam, wedde, Geslacht.valueOf(geslacht));
			docentService.create(docent);
			response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL, 
					request.getContextPath(), docent.getDocentNr())));
		} else {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}

}