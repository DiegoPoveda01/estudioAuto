package gui;

import controller.AutomotoraController;
import controller.AutoController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class VentanaRegistroEstudiante extends Ventana {
    private JLabel textoEncabezado, textoMarca, textoModelo, textoColor, textoAutomotora;
    private JTextField campoMarca, campoModelo, campoColor;
    private JComboBox campoAutomotora;
    private JButton botonRegistrar, botonCancelar;


    public VentanaRegistroEstudiante() throws ClassNotFoundException {
        super("Registro de Auto", 500, 520);
        generarElementosVentana();
    }
    private void generarElementosVentana() throws ClassNotFoundException {
        generarEncabezado();
        generarBotonCancelar();
        generarBotonRegistrar();
        generarCampoMatricula();
        generarCampoNombre();
        generarCampoRut();
        generarListaCarrera();
    }
    private void generarEncabezado() {
        String textoCabecera = "Registro de Auto";
        super.generarJLabelEncabezado(this.textoEncabezado, textoCabecera, 190, 10, 200, 50);
    }
    private void generarBotonRegistrar() {
        String textoBoton= "Registrar Auto";
        this.botonRegistrar = super.generarBoton(textoBoton, 55, 400, 170, 20);
        this.add(this.botonRegistrar);
        this.botonRegistrar.addActionListener(this);
    }
    private void generarBotonCancelar() {
        String textoBotonCancelar = "Cancelar";
        this.botonCancelar = super.generarBoton(textoBotonCancelar, 275, 400, 170, 20);
        this.add(this.botonCancelar);
        this.botonCancelar.addActionListener(this);
    }
    private void generarCampoNombre(){
        String textoMarca= "Marca:";
        super.generarJLabel(this.textoMarca,textoMarca,20,50,150,20);
        this.campoMarca= super.generarJTextField(200,50,250,20);
        this.add(this.campoMarca);
    }
    private void generarCampoRut(){
        String textoModelo= "Modelo:";
        super.generarJLabel(this.textoModelo,textoModelo,20,100,150,20);
        this.campoModelo= super.generarJTextField(200,100,250,20);
        this.add(this.campoModelo);
    }
    private void generarCampoMatricula(){
        String textoColor= "Color:";
        super.generarJLabel(this.textoColor,textoColor,20,150,150,20);
        this.campoColor= super.generarJTextField(200,150,250,20);
        this.add(this.campoColor);
    }
    private void generarListaCarrera() throws ClassNotFoundException {
        super.generarJLabel(this.textoAutomotora,"Automotora:",20,200,100,20);
        this.campoAutomotora=super.generarListaDesplegable(AutomotoraController.getNombreAutomotoras(),200,200, 250, 20);
        this.add(this.campoAutomotora);
    }
    private boolean registrarEstudiante() throws ClassNotFoundException {
        if(this.campoMarca.getText().length()==0 || this.campoModelo.getText().length()==0 || this.campoColor.getText().length()==0 || this.campoAutomotora.getSelectedItem().equals("Error no existen carreras")){
            return false;
        }
        else{
            return AutoController.registrarAuto(this.campoMarca.getText(),this.campoModelo.getText(), this.campoColor.getText(),this.campoAutomotora.getSelectedItem().toString());
        }
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.botonRegistrar) {
            try {
                if(registrarEstudiante()) {
                    JOptionPane.showMessageDialog(this,"Auto registrado");
                    VentanaBienvenida ventanaBienvenida = new VentanaBienvenida();
                    this.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(this,"Ingrese datos validos");
                }
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }

        }
        if (e.getSource() == this.botonCancelar){
            VentanaBienvenida ventanaBienvenida = new VentanaBienvenida();
            this.dispose();
        }

    }

}
