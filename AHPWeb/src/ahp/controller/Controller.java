package ahp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ahp.utilities.Matrix;
import ahp.utilities.Utilities;

/**
 * Servlet implementation class HomePage
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		int numOfCriterias = 0;
		int numOfChoices = 0;
		String[] criteriaList;
		String[] choiceList;
		Matrix[] choiceMatrixList;
		HttpSession session = request.getSession();
		String act = (String) request.getParameter("act");
		if (act == null) {
			act = "";
		}
		switch (act) {
		case "getNumOfCriterias":
			numOfCriterias = Integer.parseInt(request.getParameter("numOfCriterias"));
			numOfChoices = Integer.parseInt(request.getParameter("numOfChoices"));
			session.setAttribute("numOfCriterias", numOfCriterias);
			session.setAttribute("numOfChoices", numOfChoices);
			request.getRequestDispatcher("/WEB-INF/CriteriaName.jsp").include(request, response);
			break;
		case "enterCriteriaName":
			numOfCriterias = (int) session.getAttribute("numOfCriterias");
			criteriaList = new String[numOfCriterias];
			for (int i = 0; i < numOfCriterias; i++) {
				criteriaList[i] = request.getParameter("criteria" + i);
			}
			session.setAttribute("criteriaList", criteriaList);
			request.getRequestDispatcher("/WEB-INF/CriteriaMatrix.jsp").include(request, response);
			break;
		case "enterChoiceName":
			numOfChoices = (int) session.getAttribute("numOfChoices");
			choiceList = new String[numOfChoices];
			for (int i = 0; i < numOfChoices; i++) {
				choiceList[i] = request.getParameter("choice" + i);
			}
			session.setAttribute("choiceList", choiceList);
			request.getRequestDispatcher("/WEB-INF/ChoiceMatrix.jsp").include(request, response);
			break;
		case "enterCriteriaMatrix":
			int col = (int) session.getAttribute("numOfCriterias");
			float data[][] = new float[col][col];
			for (int i = 0; i < col; i++) {
				for (int j = 0; j < col; j++) {
					data[i][j] = Float.parseFloat(request.getParameter("" + i + j));
				}
			}
			Matrix criteriaEigenMatrix = (new Matrix(data, col, col)).eigenMatrix();
			session.setAttribute("criteriaEigenMatrix", criteriaEigenMatrix);
			System.out.println("criteriaEigenMatrix\n" + criteriaEigenMatrix.toString());
			request.getRequestDispatcher("/WEB-INF/ChoiceName.jsp").include(request, response);
			break;
		case "enterChoiceMatrix":
			numOfChoices = (int) session.getAttribute("numOfChoices");
			numOfCriterias = (int) session.getAttribute("numOfCriterias");
			criteriaList = (String[]) session.getAttribute("criteriaList");
			choiceMatrixList = new Matrix[numOfCriterias];
			for (int k = 0; k < numOfCriterias; k++) {
				float data1[][] = new float[numOfChoices][numOfChoices];
				for (int i = 0; i < numOfChoices; i++) {
					for (int j = 0; j < numOfChoices; j++) {
						data1[i][j] = Float.parseFloat(request.getParameter(criteriaList[k] + i + j));
						System.out.println(data1[i][j]);
					}
				}
				choiceMatrixList[k] = new Matrix(data1, numOfChoices, numOfChoices);
			}
			for (int i = 0; i < choiceMatrixList.length; i++) {
				System.out.println("Matrix choices:\n" + choiceMatrixList[i].toString());
			}
			Matrix choiceMatrixAfter = Utilities.createMatrix(choiceMatrixList);
			System.out.println("Choice matrix after:\n" + choiceMatrixAfter.toString());
			session.setAttribute("choiceMatrixAfter", choiceMatrixAfter);
			request.getRequestDispatcher("/WEB-INF/Result.jsp").include(request, response);
			break;
		default:
			request.getRequestDispatcher("/WEB-INF/Error.jsp").include(request, response);
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
