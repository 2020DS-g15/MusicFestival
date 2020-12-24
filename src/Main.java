import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Main() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 設定網站編碼
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		// 進入搜尋頁面
		if (request.getParameter("keyword") == null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("Search.jsp").forward(request, response);
			return;
		}

		// 搜尋
		GoogleQuery google = new GoogleQuery(request.getParameter("keyword"), 10);
		HashMap<String, String> query = google.query();

		// 結果轉成矩陣
		String[][] s = new String[query.size()][2];
		request.setAttribute("query", s);
		int num = 0;
		for (Entry<String, String> entry : query.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			s[num][0] = key;
			s[num][1] = value;
			num++;
		}
		request.getRequestDispatcher("googleitem.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
