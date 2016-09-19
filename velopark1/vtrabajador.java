

package Datos;

/* Esta clase es la que inicializa y declara las varibles previamente creadas en la BD MySQL,
   de la tabla persona se declaran los metodos GET y SETTER para luego ser llamados en 
   las demas clases**/

public class vtrabajador extends vpersona{
     
     String acceso;
     String login;
     String password;
     String estado;

    public vtrabajador() {
    }

    public vtrabajador(String acceso, String login, String password, String estado) {
       
        this.acceso = acceso;
        this.login = login;
        this.password = password;
        this.estado = estado;
    }


    public String getAcceso() {
        return acceso;
    }

    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
     
     
     
}
