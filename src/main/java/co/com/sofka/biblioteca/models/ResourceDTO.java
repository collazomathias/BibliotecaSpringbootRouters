package co.com.sofka.biblioteca.models;

import javax.validation.constraints.NotBlank;

public class ResourceDTO {

    private String id;

    @NotBlank
    private String fechaPrestamo;
    @NotBlank
    private Boolean disponible;
    @NotBlank
    private String nombre;
    @NotBlank
    private String tipo;
    @NotBlank
    private String tema;

    public ResourceDTO(){}

    public ResourceDTO(String id, String fechaPrestamo, Boolean disponible, String nombre, String tipo, String tema) {
        this.id = id;
        this.fechaPrestamo = fechaPrestamo;
        this.disponible = disponible;
        this.nombre = nombre;
        this.tipo = tipo;
        this.tema = tema;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((disponible == null) ? 0 : disponible.hashCode());
        result = prime * result + ((fechaPrestamo == null) ? 0 : fechaPrestamo.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((tema == null) ? 0 : tema.hashCode());
        result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ResourceDTO other = (ResourceDTO) obj;
        if (disponible == null) {
            if (other.disponible != null)
                return false;
        } else if (!disponible.equals(other.disponible))
            return false;
        if (fechaPrestamo == null) {
            if (other.fechaPrestamo != null)
                return false;
        } else if (!fechaPrestamo.equals(other.fechaPrestamo))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (tema == null) {
            if (other.tema != null)
                return false;
        } else if (!tema.equals(other.tema))
            return false;
        if (tipo == null) {
            if (other.tipo != null)
                return false;
        } else if (!tipo.equals(other.tipo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ResourceDTO [disponible=" + disponible + ", fechaPrestamo=" + fechaPrestamo + ", id=" + id + ", nombre="
                + nombre + ", tema=" + tema + ", tipo=" + tipo + "]";
    }

    

}
