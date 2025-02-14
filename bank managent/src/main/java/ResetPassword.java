

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/ResetPassword")
public class ResetPassword extends HttpServlet {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bankmanagement";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "BankingSystem@12";


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String oldPassword = request.getParameter("oldpassword");
		String newPassword = request.getParameter("password1");
		String confirmPassword = request.getParameter("password2");
		PrintWriter out = response.getWriter();
		
		if (!newPassword.equals(confirmPassword)) {
			out.println("<html><head>");
            out.println("<script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@11\"></script>");
            out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css\">");
            out.println("<title>Error</title></head><body>");
            out.println("<script>");
            out.println("Swal.fire({");
            out.println("  title: 'Error',");
            out.println("  text: 'Error: Password Doesn't Match !',");
            out.println("  icon: 'error'");
            out.println("}).then(() => { history.back(); });"); // Using history.back() to go back to the previous page
            out.println("</script>");
            out.println("</body></html>");
            out.close();
		}
		
		else {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
		        PreparedStatement query = con.prepareStatement("SELECT * FROM customer WHERE  username = ? and password = ?;");
		        query.setString(1, username);
		        query.setString(2, oldPassword);
		        ResultSet rs = query.executeQuery();
		        
		        if (rs.next()) {
		        	
		        	
		        	
			        PreparedStatement query1 = con.prepareStatement("update customer set password = ? where username = ? ;");
			        query1.setString(1, confirmPassword);
			        query1.setString(2,username);
		        	int row = query1.executeUpdate();
		        	if (row>0) {
		        		
		  
		        		
		        		out.println("<html><head>");
		                out.println("<script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@11\"></script>");
		                out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css\">");
		                out.println("<title>Success</title></head><body>");
		                out.println("<script>");
		                out.println("Swal.fire({");
		                out.println("  title: 'Success',");
		                out.println("  text: 'Money has been successfully deposited!',");
		                out.println("  icon: 'success'");
		                out.println("}).then(() => { window.location.href = 'login.jsp'; });"); // Redirect to a success page or any other action
		                out.println("</script>");
		                out.println("</body></html>");
		                out.close();
		        		
		        		
		        	}
		        	else {
		        		out.println("<html><head>");
			            out.println("<script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@11\"></script>");
			            out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css\">");
			            out.println("<title>Error</title></head><body>");
			            out.println("<script>");
			            out.println("Swal.fire({");
			            out.println("  title: 'Error',");
			            out.println("  text: 'Error: Reset Password failed !',");
			            out.println("  icon: 'error'");
			            out.println("}).then(() => { history.back(); });"); // Using history.back() to go back to the previous page
			            out.println("</script>");
			            out.println("</body></html>");
			            out.close();
		        		
		        	}
		        	
		        	
		        	
		        }
		        else {
		        	
		        	out.println("<html><head>");
		            out.println("<script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@11\"></script>");
		            out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css\">");
		            out.println("<title>Error</title></head><body>");
		            out.println("<script>");
		            out.println("Swal.fire({");
		            out.println("  title: 'Error',");
		            out.println("  text: 'Error: Username or Old password incorrect !',");
		            out.println("  icon: 'error'");
		            out.println("}).then(() => { history.back(); });"); // Using history.back() to go back to the previous page
		            out.println("</script>");
		            out.println("</body></html>");
		            out.close();
		        	
		        }
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        

		
		

	
	}

}
