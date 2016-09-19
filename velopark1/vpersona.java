
package Datos;

/* Esta clase es la que inicializa y declara las varibles previamente creadas en la BD MySQL,
   de la tabla persona se declaran los metodos GET y SETTER para luego ser llamados en 
   las demas clases  */

public class vpersona {
    private int idpersona;
    private String nombres;
    private String tipo_documento;
    private String num_documento;
    private String direccion;
    private String telefono;
    private String email;

    public vpersona() {
    }

    public vpersona(int idpersona, String nombres, String tipo_documento, String num_documento, String direccion, String telefono, String email) {
        this.idpersona = idpersona;
        this.nombres = nombres;
        this.tipo_documento = tipo_documento;
        this.num_documento = num_documento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    public int getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getNum_documento() {
        return num_documento;
    }

    public void setNum_documento(String num_documento) {
        this.num_documento = num_documento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   
    
}
