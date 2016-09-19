
package Datos;

/* Esta clase es la que inicializa y declara las varibles previamente creadas en la BD MySQL,
   de la tabla tarifas, en si es solo una tabla pero utilizo dos clases para los dos formularios
   tarifas por fraccion y tarifas por mensualidad; se declaran los metodos GET y SETTER para 
   luego ser llamados en las demas clases  */

public class vtarifas_mes {
    int idtarifa;
    String tipo_tarifa;
     double precio_tarifa;
     

    public vtarifas_mes() {
    }

    public vtarifas_mes(int idtarifa, String tipo_tarifa, double precio_tarifa) {
        this.idtarifa = idtarifa;
        this.tipo_tarifa = tipo_tarifa;
        this.precio_tarifa = precio_tarifa;
        
    }

    public int getIdtarifa() {
        return idtarifa;
    }

    public void setIdtarifa(int idtarifa) {
        this.idtarifa = idtarifa;
    }

    public String getTipo_tarifa() {
        return tipo_tarifa;
    }

    public void setTipo_tarifa(String tipo_tarifa) {
        this.tipo_tarifa = tipo_tarifa;
    }

    public double getPrecio_tarifa() {
        return precio_tarifa;
    }

    public void setPrecio_tarifa(double precio_tarifa) {
        this.precio_tarifa = precio_tarifa;
    }

    
   
}
