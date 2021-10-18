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
import modelo.habitacion;

/**
 *
 * @author diaz
 */
public class chabitacion {

     
    private conexion con =new conexion();
    private Connection cn=con.conectar();

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo = null;
       

        String[] titulos = {"idhabitacion", "numero", "piso", "decripcion", "caracteristicas", "precio", "tipohabitacion", "estado"};
        modelo = new DefaultTableModel(null, titulos);
        try {
            String sql = "select * from habitaciones where piso like '%" + buscar + "%'";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String[] registro = new String[8];
            while (rs.next()) {
                registro[0] = Integer.toString(rs.getInt("idhabitacion"));
                registro[1] = Integer.toString(rs.getInt("numero"));
                registro[2] = Integer.toString(rs.getInt("piso"));
                registro[3] = rs.getString("descripcion");
                registro[4] = rs.getString("caracteristicas");
                registro[5] = rs.getString("precio");
                registro[6] = rs.getString("tipohabitacion");
                registro[7] = rs.getString("estado");

                modelo.addRow(registro);

            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }

        return modelo;
    }

    public boolean insertar(habitacion h) {
        String sql = "insert into habitaciones (numero,piso,descripcion,caracteristicas,precio,tipohabitacion,estado)"
                + "values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pt = cn.prepareStatement(sql);
            pt.setInt(1, h.getNumero());
            pt.setInt(2, h.getPiso());
            pt.setString(3, h.getDescripcion());
            pt.setString(4, h.getCaracteristicas());
            pt.setString(5, h.getPrecio());
            pt.setString(6, h.getTipo());
            pt.setString(7, h.getEstado());
            pt.executeUpdate();
            return true;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }

    }

    public boolean actualizar(habitacion h) {
        String sql = "update habitaciones set numero=?,piso=?,descripcion=?,caracteristicas=?,precio=?,tipohabitacion=?,estado=?"+
                "where idhabitacion=?";

        try {
            
            PreparedStatement pt = cn.prepareStatement(sql);
            pt.setInt(1, h.getNumero());
            pt.setInt(2, h.getPiso());
            pt.setString(3, h.getDescripcion());
            pt.setString(4, h.getCaracteristicas());
            pt.setString(5, h.getPrecio());
            pt.setString(6, h.getTipo());
            pt.setString(7, h.getEstado());
            pt.setInt(8, h.getIdhabitacion());
            pt.executeUpdate();
            return true;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }

    }

    public boolean eliminar(habitacion h) {
            
        try {
            String sql = "delete from habitaciones where idhabitacion=?";
            
            PreparedStatement pt = cn.prepareStatement(sql);
            pt.setInt(1, h.getIdhabitacion());
            pt.executeUpdate();
            return true;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }

    }

}
