
package Logica;

/* En esta clase se creara una tabla para mostrar los registros guardados en un formularios Jtable
   de la tabla mensualidad, asi como lnsertar, acualizar y eliminar (CRUD) */

import Datos.mensualidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class fmensualidad {

    private conexion mysql = new conexion();
    //Creacion de una variable de tipo connection
    private Connection cn = mysql.conectar();
    //variables donde se van a guardar las cadenas de conexion
    private String sSQL = "";
    private String sSQL2 = "";
    //variable publica de tipo entera que contara los registros que tiene la tabla
    public Integer totalregistros;
    
    // Creacion de una funcion defaulttablemodel para mostar los registros de la tabla mensualidad
    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"id", "Nombre", "Doc", "Número Documento", "Dirección", "Teléfono", "Email","Mensualidad","Placa","id_trabajador","nom_trabajador","id_tarifa","tipo_tarifa","valor","fecha_ini","fecha_fin","Descripcion","Ubicacion","valor","Descuento","Subtotal","IVA","total"};

        String[] registro = new String[23];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select p.idpersona,p.nombres,p.tipo_documento,p.num_documento,"
                + "p.direccion,p.telefono,p.email,m.idmensualidad,m.Placa,m.id_trabajador,(select nombres from persona where idpersona=m.id_trabajador)as trabajadorn,"+
                "m.id_tarifa,(select tipo_tarifa from tarifa  where idtarifa=m.id_tarifa)as tipo_Tarifan,"+
                "(select precio_tarifa from tarifa where idtarifa=m.id_tarifa)as Plenan,"+
                "m.Fecha_ini,m.Feha_fin,m.Descripcion,m.Ubicacion,m.Valor,m.Descuento,m.subtotal,m.IVA,m.Total from persona p inner join mensualidad m "
                + "on p.idpersona=m.idpersona where Placa like '%"
                + buscar + "%' order by idpersona desc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("idpersona");
                registro[1] = rs.getString("nombres");
                registro[2] = rs.getString("tipo_documento");
                registro[3] = rs.getString("num_documento");
                registro[4] = rs.getString("direccion");
                registro[5] = rs.getString("telefono");
                registro[6] = rs.getString("email");
                registro[7] = rs.getString("idmensualidad");
                registro[8] = rs.getString("Placa");
                registro[9] = rs.getString("id_trabajador");
                registro[10] = rs.getString("trabajadorn");
                registro[11] = rs.getString("id_tarifa");
                registro[12] = rs.getString("tipo_tarifan");
                registro[13] = rs.getString("Plenan");
                registro[14] = rs.getString("Fecha_ini");
                registro[15] = rs.getString("Feha_fin");
                registro[16] = rs.getString("Descripcion");
                registro[17] = rs.getString("Ubicacion");
                registro[18] = rs.getString("Valor");
                registro[19] = rs.getString("Descuento");
                registro[20] = rs.getString("subtotal");
                registro[21] = rs.getString("IVA");
                registro[22] = rs.getString("Total");
                
                
                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    public boolean insertar(mensualidad dts) {
        sSQL = "insert into persona (nombres,tipo_documento,num_documento,direccion,telefono,email)"
                + "values (?,?,?,?,?,?)";
        sSQL2 = "insert into mensualidad (idpersona,Placa,id_trabajador,id_tarifa,Fecha_ini,Feha_fin,Descripcion,Ubicacion,Valor,Descuento,subtotal,IVA,Total)"
                + "values ((select idpersona from persona order by idpersona desc limit 1),?,?,?,?,?,?,?,?,?,?,?,?)";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            pst.setString(1, dts.getNombres());
            pst.setString(2, dts.getTipo_documento());
            pst.setString(3, dts.getNum_documento());
            pst.setString(4, dts.getDireccion());
            pst.setString(5, dts.getTelefono());
            pst.setString(6, dts.getEmail());

           
            pst2.setString(1, dts.getPlaca());
            pst2.setInt(2, dts.getId_trabajador());
            pst2.setInt(3, dts.getId_tarifa());
            pst2.setString(4, dts.getFecha_ini());
            pst2.setString(5, dts.getFecha_fin());
            pst2.setString(6, dts.getDescripcion());
            pst2.setString(7, dts.getUbicacion());
            pst2.setDouble(8, dts.getValor());
            pst2.setDouble(9, dts.getDescuento());
            pst2.setDouble(10, dts.getSubtotal());
            pst2.setDouble(11, dts.getIVA());
            pst2.setDouble(12, dts.getTotal());
            
            
            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;

                } else {
                    return false;
                }

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

     public boolean editar(mensualidad dts) {
        sSQL = "update persona set nombres=?,tipo_documento=?,num_documento=?,"
                + " direccion=?,telefono=?,email=? where idpersona=?";
        
        sSQL2 = "update mensualidad set Placa=?,id_trabajador=?,id_tarifa=?,Fecha_ini=?,Feha_fin=?,Descripcion=?,Ubicacion=?,Valor=?,Descuento=?,subtotal=?,IVA=?,Total=?"
                + " where idpersona=?";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            pst.setString(1, dts.getNombres());
            pst.setString(2, dts.getTipo_documento());
            pst.setString(3, dts.getNum_documento());
            pst.setString(4, dts.getDireccion());
            pst.setString(5, dts.getTelefono());
            pst.setString(6, dts.getEmail());
            pst.setInt(7, dts.getIdpersona());

            
            pst2.setString(1, dts.getPlaca());
            pst2.setInt(2, dts.getId_trabajador());
            pst2.setInt(3, dts.getId_tarifa());
            pst2.setString(4, dts.getFecha_ini());
            pst2.setString(5, dts.getFecha_fin());
            pst2.setString(6, dts.getDescripcion());
            pst2.setString(7, dts.getUbicacion());
            pst2.setDouble(8, dts.getValor());
            pst2.setDouble(9, dts.getDescuento());
            pst2.setDouble(10, dts.getSubtotal());
            pst2.setDouble(11, dts.getIVA());
            pst2.setDouble(12, dts.getTotal());
            pst2.setInt(13, dts.getIdpersona());

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;

                } else {
                    return false;
                }

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean eliminar(mensualidad dts) {
        sSQL = "delete from mensualidad where idpersona=?";
        sSQL2 = "delete from persona where idpersona=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            
            pst.setInt(1, dts.getIdpersona());

            
            pst2.setInt(1, dts.getIdpersona());

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;

                } else {
                    return false;
                }

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
    
   

    }
    
    
    
    
    
    
    

