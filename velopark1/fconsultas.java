

package Logica;

/* Esta clase es la encargada de conectar al formulario Historico con la BD y para ello se 
   realizan algunas consultas de las tablas registro y mensualidad..*/

import Datos.vregistro;
import Presentacion.frmregistro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class fconsultas {
   
    /* Instancia a la clase conexion para realizar Jtable de las tablas registro y mensualidad a la BD  */
    
   private conexion mysql=new conexion();
   //Creacion de una variable de tipo connection
   private Connection cn=mysql.conectar();
   //variable donde se va a guardar la cadena de conexion
   private String sSQL="";
   //variable publica de tipo entera que contara los registros que tiene la tabla
   public Integer totalregistros;
   
   
   // Creacion de una funcion defaulttablemodel para mostar los registros de la tabla registro
    public DefaultTableModel mostrar2(String buscar){
       DefaultTableModel modelo1;
       
       String [] titulos = {"ID","Placa","Nombre","Telefono","idtarifa","Tipo Tarifa","Fraccion","Plena","trabajador_In","Trabajador Entrada","Hora Ingreso","Ubicacion","Hora Salida","Trabajador_Our","Trabajador Salida","Tiempo","Valor","Descuento","IVA","Total"};
       
       String [] registro =new String [20];
       
       totalregistros=0;
       modelo1 = new DefaultTableModel(null,titulos);
       
      sSQL="select r.idregistro,r.placa,r.Nombre,r.telefono,r.idtarifa,"+
               "(select tipo_tarifa from tarifa  where idtarifa=r.idtarifa)as tipo_Tarifan,"+
               "(select precio_tarifa from tarifa where idtarifa=r.idtarifa)as Fraccionn,"+
               "(select precio_plena_min from tarifa where idtarifa=r.idtarifa)as Plenan,"+
               "r.trabajador_in,(select nombres from persona where idpersona=r.trabajador_in)as trabajadorn,"+
               "r.hora_ingreso,r.ubicacion,r.hora_salida,"+
               "r.trabajador_out,(select nombres from persona where idpersona=r.trabajador_out)as trabajadorp,"+
               "r.tiempo,r.valor,r.descuento,r.IVA,r.total from registro r inner join tarifa t on r.idtarifa=t.idtarifa "
               + "where r.hora_salida is NOT NULL AND hora_salida like '%"+ buscar + "%'"
               + " order by idregistro desc";
       
       try {
           Statement st= cn.createStatement();
           ResultSet rs=st.executeQuery(sSQL);
           
           while(rs.next()){
               registro [0]=rs.getString("idregistro");
               registro [1]=rs.getString("placa");
               registro [2]=rs.getString("Nombre"); 
               registro [3]=rs.getString("telefono");            
               registro [4]=rs.getString("idtarifa");
               registro [5]=rs.getString("tipo_Tarifan");
               registro [6]=rs.getString("Fraccionn");
               registro [7]=rs.getString("Plenan");
               registro [8]=rs.getString("trabajador_in");
               registro [9]=rs.getString("trabajadorn");     
               registro [10]=rs.getString("hora_ingreso");
               registro [11]=rs.getString("ubicacion");
               registro [12]=rs.getString("hora_salida"); 
               registro [13]=rs.getString("trabajador_out");
               registro [14]=rs.getString("trabajadorp");
               registro [15]=rs.getString("tiempo");
               registro [16]=rs.getString("valor"); 
               registro [17]=rs.getString("descuento");
               registro [18]=rs.getString("IVA");
               registro [19]=rs.getString("total");
               
               
               totalregistros=totalregistros+1;
               modelo1.addRow(registro);
               
           }
           return modelo1;
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return null;
       }    
   } 
    
    public DefaultTableModel mostrar(String buscar){
       DefaultTableModel modelo;
       
       String [] titulos = {"Id","Usuario","Total"};
       
       String [] registro =new String [3];
       
       totalregistros=0;
       modelo = new DefaultTableModel(null,titulos);
       
      sSQL="SELECT trabajador_out,(select nombres from persona where idpersona=trabajador_out)as trabajadorp,"
              + " SUM(total) as total FROM registro where hora_salida like '%"+ buscar + "%' GROUP BY trabajador_out"; 
       try {
           Statement st= cn.createStatement();
           ResultSet rs=st.executeQuery(sSQL);
           
           while(rs.next()){
               registro [0]=rs.getString("trabajador_out");
               registro [1]=rs.getString("trabajadorp");
               registro [2]=rs.getString("total");           
               
               totalregistros=totalregistros+1;
               modelo.addRow(registro);
               
           }
           return modelo;
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return null;
       }      
   } 
   
    // Creacion de una funcion defaulttablemodel para mostar los registros de la tabla mensualidad
     public DefaultTableModel mostrar_mensualidad1(String buscar) {
        DefaultTableModel modelo_men;

        String[] titulos = {"id", "Nombre", "Doc", "Documento", "Dirección", "Teléfono", "Email","Mensualidad","Placa","id_trabajador","Trabajador","id_tarifa","tipo_tarifa","valor","Inicio","Vence","Descripcion","Ubicacion","valor","IVA","Descuento","Total"};

        String[] registro = new String[22];

        totalregistros = 0;
        modelo_men = new DefaultTableModel(null, titulos);

        sSQL = "select p.idpersona,p.nombres,p.tipo_documento,p.num_documento,"
                + "p.direccion,p.telefono,p.email,m.idmensualidad,m.Placa,m.id_trabajador,(select nombres from persona where idpersona=m.id_trabajador)as trabajadorn,"+
                "m.id_tarifa,(select tipo_tarifa from tarifa  where idtarifa=m.id_tarifa)as tipo_Tarifan,"+
                "(select precio_tarifa from tarifa where idtarifa=m.id_tarifa)as Plenan,"+
                "m.Fecha_ini,m.Feha_fin,m.Descripcion,m.Ubicacion,m.Valor,m.IVA,m.Descuento,m.Total from persona p inner join mensualidad m "
                + "on p.idpersona=m.idpersona where Fecha_ini like '%"
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
                registro[19] = rs.getString("IVA");
                registro[20] = rs.getString("Descuento");
                registro[21] = rs.getString("Total");
                
                
                totalregistros = totalregistros + 1;
                modelo_men.addRow(registro);

            }
            return modelo_men;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }
     
      public DefaultTableModel mostrar_mensualidad2(String buscar){
       DefaultTableModel modelo_men1;
       
       String [] titulos = {"Id","Usuario","Total"};
       
       String [] registro =new String [3];
       
       totalregistros=0;
       modelo_men1 = new DefaultTableModel(null,titulos);
       
      sSQL="SELECT id_trabajador,(select nombres from persona where idpersona=id_trabajador)as trabajadorp,"
              + " SUM(total) as total FROM mensualidad where Fecha_ini like '%"+ buscar + "%' GROUP BY id_trabajador"; 
       try {
           Statement st= cn.createStatement();
           ResultSet rs=st.executeQuery(sSQL);
           
           while(rs.next()){
               registro [0]=rs.getString("id_trabajador");
               registro [1]=rs.getString("trabajadorp");
               registro [2]=rs.getString("total"); 
               
               
               totalregistros=totalregistros+1;
               modelo_men1.addRow(registro);
               
           }
           return modelo_men1;
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return null;
       }
       
       
       
   } 
   
   
  
}
