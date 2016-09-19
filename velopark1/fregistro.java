
package Logica;

/* En esta clase se creara una tabla para mostrar los registros guardados en un formularios Jtable
   de la tabla registro, asi como lnsertar, acualizar y eliminar (CRUD) */

import Datos.vregistro;
import Presentacion.frmregistro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class fregistro {
    
   private conexion mysql=new conexion();
   //Creacion de una variable de tipo connection
   private Connection cn=mysql.conectar();
   //variable donde se va a guardar la cadena de conexion
   private String sSQL="";
   //variable publica de tipo entera que contara los registros que tiene la tabla
   public Integer totalregistros;
   
   // Creacion de una funcion defaulttablemodel para mostar los registros de la tabla tarifa
   public DefaultTableModel mostrar(String buscar){
       DefaultTableModel modelo;
       
       String [] titulos = {"ID","Placa","Nombre","Telefono","idtarifa","Tipo Tarifa","Fraccion","Plena","trabajador_In","Trabajador","Hora Ingreso","Ubicacion","Hora Salida","Trabajador_Our","Trabajador","Tiempo","Valor","Descuento","IVA","subtotal","Total"};
       
       String [] registro =new String [21];
       
       totalregistros=0;
       modelo = new DefaultTableModel(null,titulos);
       
       sSQL="select r.idregistro,r.placa,r.Nombre,r.telefono,r.idtarifa,"+
               "(select tipo_tarifa from tarifa  where idtarifa=r.idtarifa)as tipo_Tarifan,"+
               "(select precio_tarifa from tarifa where idtarifa=r.idtarifa)as Fraccionn,"+
               "(select precio_plena_min from tarifa where idtarifa=r.idtarifa)as Plenan,"+
               "r.trabajador_in,(select nombres from persona where idpersona=r.trabajador_in)as trabajadorn,"+
               "r.hora_ingreso,r.ubicacion,r.hora_salida,"+
               "r.trabajador_out,(select nombres from persona where idpersona=r.trabajador_out)as trabajadorp,"+
               "r.tiempo,r.valor,r.descuento,r.IVA,r.subtotal,r.total from registro r inner join tarifa t on r.idtarifa=t.idtarifa where r.hora_salida is NULL AND placa like '%"+ buscar + "%' order by idregistro desc";
       
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
               registro [19]=rs.getString("subtotal");
               registro [20]=rs.getString("total");
               
               
               totalregistros=totalregistros+1;
               modelo.addRow(registro);
               
           }
           return modelo;
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return null;
       }
       
       
       
   } 
   
   
   
   public boolean insertar (vregistro dts){
       
       String placa = frmregistro.txtplaca.getText();
      // String hora = frmregistro.txthora.getText();
       sSQL="INSERT INTO registro (placa,Nombre,telefono,idtarifa,trabajador_in,hora_ingreso,ubicacion) SELECT * FROM (SELECT ? as placa,? as Nombre,? as telefono,? as idtarifa,? as trabajador_in,? as hora_ingreso,? as ubicacion) AS tmp WHERE NOT EXISTS ( SELECT placa FROM registro WHERE placa = '%"+ placa + "%') LIMIT 1";
               
            
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           pst.setString(1, dts.getPlaca());
           pst.setString(2, dts.getNombre());
           pst.setString(3, dts.getTelefono()); 
           pst.setInt(4, dts.getIdtarifa());
           pst.setInt(5, dts.getTrabajador_in());
           pst.setString(6, dts.getHora_ingreso());
           pst.setString(7, dts.getUbicacion());
        
           
           
           
           int n=pst.executeUpdate();
           
           if (n!=0){
               return true;
           }
           else {
               return false;
           }
           
           
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return false;
       }
   }
   /* La tabla registro se maneja en dos tiempos, uno donde se guarda el ingreso
      y otro donde se guarda la salida, pero en si no se hacen dos insert, si no que para dar 
      salida al registro osea completar la mitad de la tabla lo que se hace es una 
      actualizacion */
   public boolean insertar_salida (vregistro dts){
       sSQL="update registro set placa=?,Nombre=?,telefono=?,idtarifa=?,trabajador_in=?,hora_ingreso=?,ubicacion=?,hora_salida=?,"
                + " trabajador_out=?,tiempo=?,valor=?,descuento=?,IVA=?,subtotal=?,total=? where idregistro=?";
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           pst.setString(1, dts.getPlaca());
           pst.setString(2, dts.getNombre());
           pst.setString(3, dts.getTelefono());
           pst.setInt(4, dts.getIdtarifa());
           pst.setInt(5, dts.getTrabajador_in());
           pst.setString(6, dts.getHora_ingreso());
           pst.setString(7, dts.getUbicacion());
           pst.setString(8, dts.getHora_salida());
           pst.setInt(9, dts.getTrabajador_out());
           pst.setString(10, dts.getTiempo());
           pst.setDouble(11, dts.getValor());
           pst.setDouble(12, dts.getDescuento());
           pst.setDouble(13, dts.getIVA());
           pst.setDouble(14, dts.getSubtotal());
           pst.setDouble(15, dts.getTotal());
           pst.setInt(16, dts.getIdregistro());
           
           
           
           
           int n=pst.executeUpdate();
           
           if (n!=0){
               return true;
           }
           else {
               return false;
           }
           
           
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return false;
       }
   }
   
   
    
}
