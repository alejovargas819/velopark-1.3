
package Logica;

/* En esta clase se creara una tabla para mostrar los registros guardados en un formularios Jtable
   de la tabla tarifas por fraccion, asi como lnsertar, acualizar y eliminar (CRUD) */

import Datos.vtarifas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class ftarifas {
    
    private conexion mysql = new conexion();
    //Creacion de una variable de tipo connection
    private Connection cn = mysql.conectar();
    //variable donde se va a guardar la cadena de conexion
    private String sSQL="";
    //variable publica de tipo entera que contara los registros que tiene la tabla
    public Integer totalregistros;
    
    
    // Creacion de una funcion defaulttablemodel para mostar los registros de la tabla tarifa  
    public DefaultTableModel mostrar(String buscar){
        DefaultTableModel modelo; 
        
        String [] titulos ={"id","Tipo Tarifa","Precio Tarifa","Tarifa Plena"};
        
        String [] registro = new String [4];
        
        totalregistros=0;
        
        //para que guarde los titulos 
        modelo = new DefaultTableModel(null,titulos);
        
        //Instruccion sql la que va a obtener todos los registros de la tabla tarifas por fraccion
        sSQL="select * from tarifa where precio_plena_min is not null AND tipo_tarifa like '%"+ buscar +"%' order by tipo_tarifa";
        
        try {
            //Sentencia statement
            Statement st=cn.createStatement();
            //Resultset para ejecutar la consulta
            ResultSet rs = st.executeQuery(sSQL);
            
            
            while(rs.next()){
                registro [0] = rs.getString("idtarifa");
                registro [1] = rs.getString("tipo_tarifa");
                registro [2] = rs.getString("precio_tarifa");
                registro [3] = rs.getString("precio_plena_min");
                
               
                totalregistros = totalregistros+1;
                //envio a manera de fila del vector registro
                modelo.addRow(registro);
                
            }
            return modelo;
            
        } catch (Exception e) {
            
            //En caso de excepcion que muestre un mensaje expecifico
            JOptionPane.showConfirmDialog(null,e);
            return null;
        }
        
    
    
    }
    
   public DefaultTableModel mostrarvista(String buscar){
        DefaultTableModel modelo; 
        
        String [] titulos ={"id","Tipo Tarifa","Precio Tarifa","Tarifa Plena"};
        
        String [] registro = new String [4];
        
        totalregistros=0;
        //para que guarde los titulos 
        modelo = new DefaultTableModel(null,titulos);
        
        //Instruccion sql la que va a obtener todos los registros de la tabal tarifa por fraccion
        
        sSQL="select * from tarifa where precio_plena_min is not null AND tipo_tarifa like '%"+ buscar +"%' order by tipo_tarifa";
        
        try {
            //creamos la sentencia statement
            Statement st=cn.createStatement();
            //creamos un resultset para ejecutar la consulta
            ResultSet rs = st.executeQuery(sSQL);
            
            
            while(rs.next()){
                registro [0] = rs.getString("idtarifa");
                registro [1] = rs.getString("tipo_tarifa");
                registro [2] = rs.getString("precio_tarifa");
                registro [3] = rs.getString("precio_plena_min");
                
               
                totalregistros = totalregistros+1;
                //envio a manera de fila del vector registro
                modelo.addRow(registro);
                
            }
            return modelo;
            
        } catch (Exception e) {
            
        //En caso de excepcion que muestre un mensaje expecifico
            JOptionPane.showConfirmDialog(null,e);
            return null;
        }
        
    
    
    }
    
   
   
    //insertar de tipo boolean 
    public boolean insertar(vtarifas dts){
        //la instruccion query para insertar datos en la bd, sin la fila idtarifa ya que es de tipo autoincrement
        sSQL="insert into tarifa (tipo_tarifa,precio_tarifa,precio_plena_min)" + 
                "values (?,?,?)";
        try {
            //Preparar la consulta
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getTipo_tarifa());
            pst.setDouble(2, dts.getPrecio_tarifa());
            pst.setDouble(3, dts.getPrecio_plena_min());
           
           
            
            //Esta varible va a almacenar el valor del statement
            int n=pst.executeUpdate();
            //Una condicion por si no se pueden insertar datos
            if (n!=0){
            return true;
            }
            else{
            return false;
            }
            
            
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null,e);
            //si no se pueden insertar registros
            return false;
        }
    }
    
    
    public boolean editar(vtarifas dts){
        //Creacion de la consulta
        sSQL="update tarifa set tipo_tarifa=?,precio_tarifa=?,precio_plena_min=?"+
                    "where idtarifa=?";
        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getTipo_tarifa());
            pst.setDouble(2, dts.getPrecio_tarifa());
            pst.setDouble(3, dts.getPrecio_plena_min());
           
            pst.setInt(4, dts.getIdtarifa());
            
            //Esta varible va a almacenar el valor del statement
            
            int n=pst.executeUpdate();
            
            if (n!=0){
            return true;
            }
            else{
            return false;
            }
            
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null,e);
            //Si no se pueden editar registros
            return false;
        }
    }
    
    public boolean eliminar(vtarifas dts){
        
        sSQL="delete from tarifa where idtarifa=?";
        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdtarifa());
            
            //Esta varible va a almacenar el valor del statement
            
            int n=pst.executeUpdate();
            
            if (n!=0){
            return true;
            }
            else{
            return false;
            }
            
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null,e);
            return false;
        }
       
    }
    
}
