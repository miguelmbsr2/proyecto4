/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author diaz
 */
public class conexion {
    private String bd="sistemahotel";
    private String url="jdbc:mysql://"+"127.0.0.1/"+bd;
    private String usuario="root";
    private String contra="";
    
    public Connection conectar(){
        Connection cn = null;
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            cn=DriverManager.getConnection(this.url, this.usuario, this.contra);
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
               
        return cn;
    }
    
}
