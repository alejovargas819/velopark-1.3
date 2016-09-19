/* En esta clase se creara una tabla para mostrar los registros guardados en un formularios Jtable
   de la tabla tarifas por mensualidad, asi como lnsertar, acualizar y eliminar (CRUD) */

package Logica;

import Datos.vtarifas_mes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class ftarifas_mes {
    
    private conexion mysql = new conexion();
    
    //Creacion de una variable de tipo connection
    
    private Connection cn = mysql.conectar();
    
    //Variable donde se va a guardar la cadena de conexion
    
    private String sSQL="";
    
    //Variable publica de tipo entera que contara los registros que tiene
    
    public Integer totalregistros;
    
    
    
    
    
    // Despues se crea una funcion defaulttablemodel para mostar los registros de la tabla tarifas por mensualidad
    
    public DefaultTableModel mostrar(String buscar){
        DefaultTableModel modelo; 
        
        String [] titulos ={"id","Tipo Tarifa","Precio Tarifa"};
        
        String [] registro = new String [3];
        
        totalregistros=0;
        //Para que guarde los titulos 
        modelo = new DefaultTableModel(null,titulos);
        
        //Instruccion sql la que va a obtener todos los registros de la tabal habitacion
        
        sSQL="select * from tarifa where precio_plena_min is null AND tipo_tarifa like '%"+ buscar +"%' order by tipo_tarifa";
        
        try {
            //Sentencia statement
            Statement st=cn.createStatement();
            //Resultset para ejecutar la consulta
            ResultSet rs = st.executeQuery(sSQL);
            
            
            while(rs.next()){
                registro [0] = rs.getString("idtarifa");
                registro [1] = rs.getString("tipo_tarifa");
                registro [2] = rs.getString("precio_tarifa");
                
               
                totalregistros = totalregistros+1;
                //Envio a manera de fila mi vector registro
                modelo.addRow(registro);
                
            }
            return modelo;
            
        } catch (Exception e) {
            
        //En caso de excepcion que muestre algo expecificO
            JOptionPane.showConfirmDialog(null,e);
            return null;
        }
        
    
    
    }
    
   public DefaultTableModel mostrarvista(String buscar){
        DefaultTableModel modelo; 
        
        String [] titulos ={"id","Tipo Tarifa","Precio Tarifa"};
        
        String [] registro = new String [3];
        
        totalregistros=0;
        //Para que guarde los titulos 
        modelo = new DefaultTableModel(null,titulos);
        
        //Instruccion sql la que va a obtener todos los registros de la tabal habitacion
        
        sSQL="select * from tarifa where precio_plena_min is null AND tipo_tarifa like '%"+ buscar +"%' order by tipo_tarifa";
        
        try {
            //Sentencia statement
            Statement st=cn.createStatement();
            //Resultset para ejecutar la consulta
            ResultSet rs = st.executeQuery(sSQL);
            
            
            while(rs.next()){
                registro [0] = rs.getString("idtarifa");
                registro [1] = rs.getString("tipo_tarifa");
                registro [2] = rs.getString("precio_tarifa");
                
                
               
                totalregistros = totalregistros+1;
                //Envio a manera de fila del vector registro
                modelo.addRow(registro);
                
            }
            return modelo;
            
        } catch (Exception e) {
            
        //En caso de excepcion que muestre algo expecifico
            JOptionPane.showConfirmDialog(null,e);
            return null;
        }
        
    
    
    }
    
   
   
    //Insertar de tipo boolean 
    public boolean insertar(vtarifas_mes dts){
        //La instruccion query para insertar datos en la bd, sin la fila id ya q es autoincrement
        sSQL="insert into tarifa (tipo_tarifa,precio_tarifa)" + 
                "values (?,?)";
        try {
            //Preparar la consulta
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getTipo_tarifa());
            pst.setDouble(2, dts.getPrecio_tarifa());
           
           
           
            
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
            //Si no se pueden insertar registros...
            return false;
        }
    }
    
    
    public boolean editar(vtarifas_mes dts){
        //Creamos la consulta
        sSQL="update tarifa set tipo_tarifa=?,precio_tarifa=?"+
                    "where tipo_tarifa=?";
        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, dts.getTipo_tarifa());
            pst.setDouble(2, dts.getPrecio_tarifa());
           
           
            pst.setString(3, dts.getTipo_tarifa());
            
            // esta varible va a almacenar el valor del statement
            
            int n=pst.executeUpdate();
            
            if (n!=0){
            return true;
            }
            else{
            return false;
            }
            
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null,e);
            //si no se pueden editar registros
            return false;
        }
    }
    
    public boolean eliminar(vtarifas_mes dts){
        
        sSQL="delete from tarifa where idtarifa=?";
        
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdtarifa());
            
            // Esta varible va a almacenar el valor del statement
            
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
