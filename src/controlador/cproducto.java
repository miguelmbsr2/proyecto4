/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.conexion;
import modelo.producto;

/**
 *
 * @author diaz
 */
public class cproducto {
    conexion con=new conexion();
    Connection c=con.conectar();
    public DefaultTableModel mostrar(String buscar){
        DefaultTableModel modelo=null;
        String [] titulos={"idproducto","nombre","descripcion","unidad de medida","precio de venta"};
        modelo= new DefaultTableModel(null,titulos);
        String sql="select * from productos where nombre like '%"+buscar+"%' order by idproducto";
        try {
        Statement st=c.createStatement();
        ResultSet rs=st.executeQuery(sql);
        while(rs.next()){
            String [] registro = new String[5];
            registro[0]=rs.getString("idproducto");
            registro[1]=rs.getString("nombre");
            registro[2]=rs.getString("descripcion");
            registro[3]=rs.getString("unidadmedida");
            registro[4]=Double.toString(rs.getDouble("precioventa"));
            modelo.addRow(registro);
        }
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
        
        
        return modelo;
    }
    public boolean insertar(producto pr){
        String sql="insert into productos (nombre, descripcion,unidadmedida,precioventa)"+
                   "values(?,?,?,?)";
        try {
             PreparedStatement pt=c.prepareStatement(sql);
             pt.setString(1, pr.getNombre());
             pt.setString(2, pr.getDescripcion());
             pt.setString(3, pr.getUnidadmedida());
             pt.setDouble(4,pr.getPrecioventa() );
             pt.executeUpdate();
             return true;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
       
    }
    public boolean actualizar(producto pr){
        String sql="update productos set nombre=?, descripcion=?,unidadmedida=?,precioventa=?"+
                   "where idproducto=?";
        try {
             PreparedStatement pt=c.prepareStatement(sql);
             pt.setString(1, pr.getNombre());
             pt.setString(2, pr.getDescripcion());
             pt.setString(3, pr.getUnidadmedida());
             pt.setDouble(4,pr.getPrecioventa() );
             pt.setString(5,pr.getIdproducto() );
             pt.executeUpdate();
             return true;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
       
    }
    public boolean eliminar(producto pr){
        String sql="delete from productos where idproducto=?";
                   
        try {
             PreparedStatement pt=c.prepareStatement(sql);
             pt.setString(1, pr.getIdproducto());
             pt.executeUpdate();
             return true;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
       
    }
    
    
    
}
