package model;

public class Automotora {
    private String nombre;

    private String direccion;

    private String redes;

    public Automotora(String nombre, String direccion, String redes) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.redes = redes;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getRedes() {
        return redes;
    }
}
