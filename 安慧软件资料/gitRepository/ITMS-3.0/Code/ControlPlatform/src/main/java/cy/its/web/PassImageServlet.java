package cy.its.web;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cy.its.service.imageQuery.PassImage;

@WebServlet("/PassImage")
public class PassImageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String imgUrl = req.getParameter("imageUrl");
		resp.setContentType("image/jpeg;charset=utf-8");
		resp.setDateHeader("Expires", System.currentTimeMillis() + 60 * 60 * 1000);
		OutputStream outputStream = null;
		try {
			outputStream = resp.getOutputStream();
			byte[] imgData = PassImage.query(imgUrl);
			outputStream.write(imgData);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			outputStream.close();
		}
	}

}
