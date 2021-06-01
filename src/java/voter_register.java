/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
/**
 *
 * @author shiva
 */
@WebServlet(urlPatterns = {"/voter_register"})
public class voter_register extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    {
        try{
            Class.forName("org.gjt.mm.mysql.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/Onlinevoting","root","");
            PreparedStatement stmt;
            ResultSet rs;
             stmt=con.prepareStatement("select count(*)+1 from voter");
             rs=stmt.executeQuery();
             
             int voteid=0;
             if(rs.next())
             {
                 voteid=rs.getInt(1);
             }
            if(request.getParameter("b1")!=null)
            {
                String s1=request.getParameter("name");
                String s2=request.getParameter("address");
                String s3=request.getParameter("city");
                String s4=request.getParameter("id");
                String s5=request.getParameter("p1");
                stmt=con.prepareStatement("insert into voter values(?,?,?,?,?,?,?)");
                stmt.setInt(1,voteid);
                stmt.setString(2, s1);
                stmt.setString(3, s2);
                stmt.setString(4,s3);
                stmt.setString(5, s4);
                stmt.setString(6, s5);
                stmt.setString(7, "N");
                stmt.executeUpdate();
            }
            response.setContentType("text/html");
            PrintWriter out=response.getWriter();
            out.write("<html><body>");
            out.write("<form  method='post'>");
            out.write("<table align='center' width=100%><caption align='center'>Registration</caption>");
            out.write("<tr><td>Name:&nbsp</td><td><input type='text' name='name'></td></tr>");
            out.write("<tr><td>Address:&nbsp</td><td><input type='text'></td></tr>");
            out.write("<tr><td>city:&nbsp</td><td><select name='city'><option>Meerut</option><option>Bulandshar</option><option>saharanpur</option><option>Barelly</option><option>Ghaziabad</option></td></tr>");
            out.write("<tr><td>LOGIN_ID</td><td><input type='text' name='id'></td></tr>");
            out.write("<tr><td>Password</td><td><input type='password' name='p1'></td></tr>");
            out.write("<tr><td></td><td><input type='submit' name='b1' size='40' bgcolor='green' ></td></tr>");
            out.write("</table></form>");
            out.write("</body></html>");
            con.close();
            
        }catch(Exception ee){System.out.println("error"+ee);}
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
