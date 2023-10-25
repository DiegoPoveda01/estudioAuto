package model;


public class Auto {

    private String marca;

    private String modelo;

    private String color;
    private Automotora automotora;

    public Auto(String marca, String modelo, String color, Automotora automotora) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.automotora = automotora;
    }

    public Automotora getAutomotora() {
        return automotora;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString(){
        return this.marca+","+this.modelo+","+this.color;
    }
}
