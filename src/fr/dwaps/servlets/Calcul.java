package fr.dwaps.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Calcul
 */
@WebServlet("/Calcul")
public class Calcul extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String URL_PAGE = "/WEB-INF/calcul.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Calcul() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		forwardPage(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nb1Str = request.getParameter("nb1");
		String nb2Str = request.getParameter("nb2");
		String typeOperation = request.getParameter("typeOperation");
		String error = "";
		
		int nb1 = 0, nb2 = 0, resultat = 0; // Initialisation avec 0 pour valeur par défaut
		
		// On tente la conversion des chaîne en entier
		// pour pouvoir effectuer l'opération
		try {
			if (null != nb1Str && !nb1Str.isEmpty()) {
				nb1 = Integer.parseInt(nb1Str);	
			}
			if (null != nb2Str && !nb2Str.isEmpty()) {
				nb2 = Integer.parseInt(nb2Str);	
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			error = "Oups, il y a eu un soucis...";
		}
		
		// On vérifie le type d'opération demandé
		// et on l'effectue
		switch (typeOperation) {
			case "additionner":
				typeOperation = "+";
				resultat = nb1 + nb2;
				break;
			case "soustraire":
				typeOperation = "-";
				resultat = nb1 - nb2;
				break;
			case "diviser":
				typeOperation = "/";
				try {
					resultat = nb1 / nb2;
				} catch (Exception e) {
					e.printStackTrace();
					error = "Tsssss, la division par zéro : PAS BIEN !!!";
				}
				break;
			case "multiplier":
				typeOperation = "x";
				resultat = nb1 * nb2;
		}
		
		request.setAttribute("nb1", nb1);
		request.setAttribute("nb2", nb2);
		request.setAttribute("typeOperation", typeOperation);
		request.setAttribute("resultat", resultat);
		request.setAttribute("error", error);
		
		forwardPage(request, response);
	}
	
	private void forwardPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(URL_PAGE).forward(request, response);
	}

}
