

package Datos;

/* Esta clase es la que inicializa y declara las varibles previamente creadas en la BD MySQL,
   de la tabla mensualidad se declaran los metodos GET y SETTER para luego ser llamados en 
   las demas clases  */

public class mensualidad extends vpersona{
    
     
    
     int idmensualidad;
     String Placa;
     int id_trabajador;
     int id_tarifa;
     String Fecha_ini;
     String Fecha_fin;
     String Descripcion;
     String Ubicacion;
     double Valor;
     double Descuento;
     double subtotal;
     double IVA;
     double total;

    public mensualidad() {
    }

    public mensualidad(int idmensualidad, String Placa, int id_trabajador, int id_tarifa, String Fecha_ini, String Fecha_fin, String Descripcion, String Ubicacion, double Valor, double Descuento, double subtotal, double IVA, double total) {
        this.idmensualidad = idmensualidad;
        this.Placa = Placa;
        this.id_trabajador = id_trabajador;
        this.id_tarifa = id_tarifa;
        this.Fecha_ini = Fecha_ini;
        this.Fecha_fin = Fecha_fin;
        this.Descripcion = Descripcion;
        this.Ubicacion = Ubicacion;
        this.Valor = Valor;
        this.Descuento = Descuento;
        this.subtotal = subtotal;
        this.IVA = IVA;
        this.total = total;
    }

    public int getIdmensualidad() {
        return idmensualidad;
    }

    public void setIdmensualidad(int idmensualidad) {
        this.idmensualidad = idmensualidad;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String Placa) {
        this.Placa = Placa;
    }

    public int getId_trabajador() {
        return id_trabajador;
    }

    public void setId_trabajador(int id_trabajador) {
        this.id_trabajador = id_trabajador;
    }

    public int getId_tarifa() {
        return id_tarifa;
    }

    public void setId_tarifa(int id_tarifa) {
        this.id_tarifa = id_tarifa;
    }

    public String getFecha_ini() {
        return Fecha_ini;
    }

    public void setFecha_ini(String Fecha_ini) {
        this.Fecha_ini = Fecha_ini;
    }

    public String getFecha_fin() {
        return Fecha_fin;
    }

    public void setFecha_fin(String Fecha_fin) {
        this.Fecha_fin = Fecha_fin;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String Ubicacion) {
        this.Ubicacion = Ubicacion;
    }

    public double getValor() {
        return Valor;
    }

    public void setValor(double Valor) {
        this.Valor = Valor;
    }

    public double getDescuento() {
        return Descuento;
    }

    public void setDescuento(double Descuento) {
        this.Descuento = Descuento;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIVA() {
        return IVA;
    }

    public void setIVA(double IVA) {
        this.IVA = IVA;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
     
     

}