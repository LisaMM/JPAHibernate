package be.vdab.servlets;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import be.vdab.entities.Docent;
import be.vdab.exceptions.DocentNietGevondenException;
import be.vdab.services.DocentService;

/**
 * Servlet implementation class DocentZoekenServlet
 */
@WebServlet("/docenten/zoeken.htm")
public class DocentZoekenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/docenten/zoeken.jsp";
	private final DocentService docentService = new DocentService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (!request.getParameterMap().isEmpty()) {
			List<String> fouten = new ArrayList<>();
			try {
				long docentNr = Long
						.parseLong(request.getParameter("docentNr"));
				Docent docent = docentService.read(docentNr);
				if (docent == null) {
					fouten.add("Docent niet gevonden");
				} else {
					request.setAttribute("docent", docent);
				}
			} catch (NumberFormatException ex) {
				fouten.add("Tik een getal");
			}
			if (!fouten.isEmpty()) {
				request.setAttribute("fouten", fouten);
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	public static final String REDIRECT_URL = "%s/docenten/zoeken.htm?docentNr=%d";

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("verwijderen") == null) {
			bijnamenToevoegen(request, response);
		} else {
			bijnamenVerwijderen(request, response);
		}
	}

	private void bijnamenVerwijderen(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		List<String> fouten = new ArrayList<>();
		long docentNr = Long.parseLong(request.getParameter("docentNr"));
		String[] bijnamen = request.getParameterValues("bijnaam");
		if (bijnamen != null) {
			try {
				docentService.bijnamenVerwijderen(docentNr, bijnamen);
			} catch (DocentNietGevondenException ex) {
				fouten.add("Docent niet gevonden");
			}
		}
		if (fouten.isEmpty()) {
			response.sendRedirect(response.encodeRedirectURL(String.format(
					REDIRECT_URL, request.getContextPath(), docentNr)));
		} else {
			request.setAttribute("docent", docentService.read(docentNr));
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}

	private void bijnamenToevoegen(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		List<String> fouten = new ArrayList<>();
		long docentNr = Long.parseLong(request.getParameter("docentNr"));
		String bijnaam = request.getParameter("bijnaam");
		if (bijnaam == null || bijnaam.isEmpty()) {
			fouten.add("Bijnaam verplicht");
		} else {
			try {
				docentService.bijnaamToevoegen(docentNr, bijnaam);
			} catch (DocentNietGevondenException ex) {
				fouten.add("Docent niet gevonden");
			}
		}
		if (fouten.isEmpty()) {
			response.sendRedirect(response.encodeRedirectURL(String.format(
					REDIRECT_URL, request.getContextPath(), docentNr)));
		} else {
			request.setAttribute("docent", docentService.read(docentNr));
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}
}
