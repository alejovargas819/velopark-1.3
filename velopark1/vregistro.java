
package Datos;

/* Esta clase es la que inicializa y declara las varibles previamente creadas en la BD MySQL,
   de la tabla registro se declaran los metodos GET y SETTER para luego ser llamados en 
   las demas clases  */

public class vregistro{
    private int idregistro;
    private String placa;
    private String Nombre;
    private String telefono;
    private int idtarifa;
    private int trabajador_in;
    private String hora_ingreso;
    private String ubicacion;
    private String hora_salida;
    private int trabajador_out;
    private String tiempo;
    private Double valor;
    private Double IVA;
    private Double descuento;
    private Double subtotal;
    private Double total;

    public vregistro() {
    }

    public vregistro(int idregistro, String placa, String Nombre, String telefono, int idtarifa, int trabajador_in, String hora_ingreso, String ubicacion, String hora_salida, int trabajador_out, String tiempo, Double valor, Double IVA, Double descuento, Double subtotal, Double total) {
        this.idregistro = idregistro;
        this.placa = placa;
        this.Nombre = Nombre;
        this.telefono = telefono;
        this.idtarifa = idtarifa;
        this.trabajador_in = trabajador_in;
        this.hora_ingreso = hora_ingreso;
        this.ubicacion = ubicacion;
        this.hora_salida = hora_salida;
        this.trabajador_out = trabajador_out;
        this.tiempo = tiempo;
        this.valor = valor;
        this.IVA = IVA;
        this.descuento = descuento;
        this.subtotal = subtotal;
        this.total = total;
    }

    public int getIdregistro() {
        return idregistro;
    }

    public void setIdregistro(int idregistro) {
        this.idregistro = idregistro;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getIdtarifa() {
        return idtarifa;
    }

    public void setIdtarifa(int idtarifa) {
        this.idtarifa = idtarifa;
    }

    public int getTrabajador_in() {
        return trabajador_in;
    }

    public void setTrabajador_in(int trabajador_in) {
        this.trabajador_in = trabajador_in;
    }

    public String getHora_ingreso() {
        return hora_ingreso;
    }

    public void setHora_ingreso(String hora_ingreso) {
        this.hora_ingreso = hora_ingreso;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }

    public int getTrabajador_out() {
        return trabajador_out;
    }

    public void setTrabajador_out(int trabajador_out) {
        this.trabajador_out = trabajador_out;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getIVA() {
        return IVA;
    }

    public void setIVA(Double IVA) {
        this.IVA = IVA;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
    

}