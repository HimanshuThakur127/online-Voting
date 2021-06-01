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
@WebServlet(urlPatterns = {"/cand_register"})
public class cand_register extends HttpServlet {

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
            Class.forName("org.mm.gjt.mysql.Driver");
            Connection con=DriverManager.getConnection("jdbc://127.0.0.1/Onlinevoting","root","");
            PreparedStatement stmt;
            ResultSet rs;
            if(request.getParameter("b1")!=null)
            {
                String s1=request.getParameter("name");
                String s2=request.getParameter("address");
                int a1=Integer.parseInt(request.getParameter("voterid"));
                String s3=request.getParameter("city");
                String s4=request.getParameter("party");
                String s5=request.getParameter("partyid");
                stmt=con.prepareStatement("insert into candidate values(?,?,?,?,?,?)");
                stmt.setInt(1,a1);
                stmt.setString(2,s1);
                stmt.setString(3,s2);
                stmt.setString(4,s3);
                stmt.setString(5,s4);
                stmt.setInt(6,Integer.parseInt(s5));
                stmt.executeUpdate();
               response.sendRedirect("home");
            }    
            response.setContentType("text/html");
            PrintWriter out=response.getWriter();
            out.write("<html></body>");
            out.write("<form method='post'>");
            out.write("<table align='center'><caption align='center'><font color='red'>Registration for Election</caption>");
            out.write("<tr><td>Name:&nbsp</td><td><input type='text' name='name'></td></tr>");
            out.write("<tr><td>Address:&nbsp</td><td><input type='text' name='address'></td></tr>");
            out.write("<tr><td>Voter-Id:&nbsp</td><td><input type='text' name='voterid'></td></tr>");
            out.write("<tr><td>City:&nbsp&nbsp</td><td><select name='city'><option>Meerut</option><option>Bulandshar</option><option>saharanpur</option><option>Barelly</option><option>Ghaziabad</option></select></td></tr>");
            out.write("<tr><td>Party Name:&nbsp</td><td><select name='party'><option>BJP</option><option>CONGRESS</option><option>BSP</option><option>SP</option><option>AAP</option></td></tr>");
            out.write("<tr><td>Party-id:&nbsp</td><td><input type='text' name='partyid'></td></tr>");
            out.write("<tr><td></td></td><input type='submit' name='b1' value='Done' size='40'></td></tr>");
            out.write("</table></form>");
            out.write("</body></html>");
            con.close();
            
        }catch(Exception ee){  }
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
