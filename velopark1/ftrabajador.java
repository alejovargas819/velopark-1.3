/* Esta clase almacena los metodos CRUD para la tabla trabajador donde 
   hereda datos de la tabla persona para completar los datos del trabajador
   tambien almacena el metodo login el cual administra el acceso al sistema*/
package Logica;



import Datos.vtrabajador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class ftrabajador {

    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    private String sSQL2 = "";
    public Integer totalregistros;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "Nombre", "Doc", "Número Documento", "Dirección", "Teléfono", "Email","Acceso","Login","Clave","Estado"};

        String[] registro = new String[11];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select p.idpersona,p.nombres,p.tipo_documento,p.num_documento,"
                + "p.direccion,p.telefono,p.email,t.acceso,t.login,t.password,t.estado from persona p inner join Trabajador t "
                + "on p.idpersona=t.idpersona where num_documento like '%"
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
               
                registro[7] = rs.getString("acceso");
                registro[8] = rs.getString("login");
                registro[9] = rs.getString("password");
                registro[10] = rs.getString("estado");
                
                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    public boolean insertar(vtrabajador dts) {
        sSQL = "insert into persona (nombres,tipo_documento,num_documento,direccion,telefono,email)"
                + "values (?,?,?,?,?,?)";
        sSQL2 = "insert into trabajador (idpersona,acceso,login,password,estado)"
                + "values ((select idpersona from persona order by idpersona desc limit 1),?,?,?,?)";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            pst.setString(1, dts.getNombres());
          
            pst.setString(2, dts.getTipo_documento());
            pst.setString(3, dts.getNum_documento());
            pst.setString(4, dts.getDireccion());
            pst.setString(5, dts.getTelefono());
            pst.setString(6, dts.getEmail());

           
            pst2.setString(1, dts.getAcceso());
            pst2.setString(2, dts.getLogin());
            pst2.setString(3, dts.getPassword());
            pst2.setString(4, dts.getEstado());
            
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

    public boolean editar(vtrabajador dts) {
        sSQL = "update persona set nombres=?,tipo_documento=?,num_documento=?,"
                + " direccion=?,telefono=?,email=? where idpersona=?";
        
        sSQL2 = "update trabajador set acceso=?,login=?,password=?,estado=?"
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

           
            pst2.setString(1, dts.getAcceso());
            pst2.setString(2, dts.getLogin());
            pst2.setString(3, dts.getPassword());
            pst2.setString(4, dts.getEstado());
            pst2.setInt(5, dts.getIdpersona());

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

    public boolean eliminar(vtrabajador dts) {
        sSQL = "delete from trabajador where idpersona=?";
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
    
    /* Creacion de un defaulttablemodel para almacenar los datos de registro, pero este sera invisible 
       en el formulario solo servira para alimentar el metodo */
    
    public DefaultTableModel login(String login,String password) {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "Nombres","Acceso","Login","Clave","Estado"};

        String[] registro = new String[8];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select p.idpersona,p.nombres,t.acceso,t.login,t.password,t.estado from persona p inner join Trabajador t "
                + "on p.idpersona=t.idpersona where t.login='"
                + login + "'and t.password='" + password + "' and t.estado='Habilitado'";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("idpersona");
                registro[1] = rs.getString("nombres");
                
                registro[2] = rs.getString("acceso");
                registro[3] = rs.getString("login");
                registro[4] = rs.getString("password");
                registro[5] = rs.getString("estado");
                
                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }
    
    
    
    
    
    
    
}
