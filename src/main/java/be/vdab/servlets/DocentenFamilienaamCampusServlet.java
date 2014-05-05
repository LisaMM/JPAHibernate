package be.vdab.servlets;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import be.vdab.services.*;

/**
 * Servlet implementation class DocentenFamilienaamCampusServlet
 */
@WebServlet("/docenten/familienaamcampus.htm")
public class DocentenFamilienaamCampusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/docenten/familienaamcampus.jsp";
	private final DocentService docentService = new DocentService();
	private final CampusService campusService = new CampusService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("campussen", campusService.findAll());
		if (!request.getParameterMap().isEmpty()) {
			if (request.getParameter("campussen") == null) {
				request.setAttribute("fouten", Arrays.asList("Kies een campus"));
			} else {
				request.setAttribute("docenten", docentService
						.findByFamilienaamEnCampus(request
								.getParameter("familienaam"), Long
								.parseLong(request.getParameter("campussen"))));
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
