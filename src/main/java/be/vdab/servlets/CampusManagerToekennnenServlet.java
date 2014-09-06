package be.vdab.servlets;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import be.vdab.exceptions.*;
import be.vdab.services.*;

/**
 * Servlet implementation class CampusManagerToekennnenServlet
 */
@WebServlet("/campussen/managertoekennen.htm")
public class CampusManagerToekennnenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/campussen/managertoekennen.jsp";
	private static final String REDIRECT_URL = "%s/campussen/managertoekennen.htm";
	private final CampusService campusService = new CampusService();
	private final ManagerService managerService = new ManagerService();

	private void getCampussenEnManagers(HttpServletRequest request) {
		request.setAttribute("campussen", campusService.findAll());
		request.setAttribute("managers", managerService.findAll());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		getCampussenEnManagers(request);
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<String> fouten = new ArrayList<>();
		String campusNrString = request.getParameter("campusNr");
		if (campusNrString == null) {
			fouten.add("Kies een campus.");
		}
		String managerNrString = request.getParameter("managerNr");
		if (managerNrString == null) {
			fouten.add("Kies een manager.");
		}
		if (fouten.isEmpty()) {
			try {
				long campusNr = Long.parseLong(campusNrString);
				long managerNr = Long.parseLong(managerNrString);
				campusService.kenManagerToe(campusNr, managerNr);
				response.sendRedirect(response.encodeRedirectURL(String.format(
						REDIRECT_URL, request.getContextPath())));
			} catch (CampusNietGevondenException ex) {
				fouten.add("Campus niet gevonden");
			} catch (ManagerNietGevondenException ex) {
				fouten.add("Manager niet gevonden");
			}
		}
		if (!fouten.isEmpty()) {
			request.setAttribute("fouten", fouten);
			getCampussenEnManagers(request);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}

}
