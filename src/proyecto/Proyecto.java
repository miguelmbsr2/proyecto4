/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import modelo.conexion;

/**
 *
 * @author diaz
 */
public class Proyecto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        conexion c = new conexion();
        Connection cn = c.conectar();
        try {

            Statement st = cn.createStatement();
            String sql = "select * from habitaciones";
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            JOptionPane.showConfirmDialog(null, rs.getString("caracteristicas"));
        
        } catch (Exception e) {
        }

    }

}
