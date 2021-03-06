
package pe.edu.cibertec.dominio;

import javax.persistence.Basic;
import javax.persistence.Entity;

import pe.edu.cibertec.dominio.base.EntidadBase;

@Entity
public class Categoria extends EntidadBase{
    
    @Basic
    private String nombre;
    
    @Basic
    private String imagen;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    
}
