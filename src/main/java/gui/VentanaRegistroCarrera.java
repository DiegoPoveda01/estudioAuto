package gui;

import controller.AutomotoraController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class VentanaRegistroCarrera extends Ventana {
    private JLabel textoEncabezado, textoDireccion, textoNombre, textoRedes;
    private JTextField campoDireccion, campoNombre, campoRedes;
    private JButton botonRegistrar, botonCancelar;


    public VentanaRegistroCarrera(){
        super("Registro de Automotora", 500, 520);
        generarElementosVentana();
    }
    private void generarElementosVentana() {
        generarEncabezado();
        generarBotonCancelar();
        generarBotonRegistrar();
        generarCampoNombre();
        generarDireccionAutomotora();
        generarRedesAutomotora();
    }
    private void generarEncabezado() {
        String textoCabecera = "Registro de Automotora";
        super.generarJLabelEncabezado(this.textoEncabezado, textoCabecera, 100, 10, 290, 50);

    }
    private void generarBotonRegistrar() {
        String textoBoton= "Registrar Automotora";
        this.botonRegistrar = super.generarBoton(textoBoton, 75, 400, 150, 20);
        this.add(this.botonRegistrar);
        this.botonRegistrar.addActionListener(this);
    }
    private void generarBotonCancelar() {
        String textoBotonCancelar = "Cancelar";
        this.botonCancelar = super.generarBoton(textoBotonCancelar, 275, 400, 150, 20);
        this.add(this.botonCancelar);
        this.botonCancelar.addActionListener(this);
    }
    private void generarCampoNombre(){
        String textoNombre= "Nombre automotora:";
        super.generarJLabel(this.textoNombre,textoNombre,20,50,150,20);
        this.campoNombre= super.generarJTextField(200,50,250,20);
        this.add(this.campoNombre);
    }
    private void generarDireccionAutomotora(){
        String textoDireccion= "Dirección automotora:";
        super.generarJLabel(this.textoDireccion,textoDireccion,20,100,150,20);
        this.campoDireccion= super.generarJTextField(200,100,250,20);
        this.add(this.campoDireccion);
    }
    private void generarRedesAutomotora(){
        String textoRedes= "Redes automotora:";
        super.generarJLabel(this.textoRedes,textoRedes,20,150,150,20);
        this.campoRedes = super.generarJFormattedTextField(super.generarFormato(0),200,150,250,20);
        this.add(this.campoRedes);
    }

    private boolean registrarCarrera() throws ClassNotFoundException {
        if(this.campoNombre.getText().length()==0 || this.campoDireccion.getText().length()==0 || this.campoRedes.getText().length()==0){
            return false;
        }
        else{
            return AutomotoraController.añadirAutomotora(this.campoNombre.getText(),this.campoDireccion.getText(), String.valueOf(Integer.parseInt(this.campoRedes.getText())));

        }
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.botonRegistrar) {
            try {
                if(registrarCarrera()) {
                    JOptionPane.showMessageDialog(this,"Automotora registrada correctamente");
                    VentanaBienvenida ventanaBienvenida = new VentanaBienvenida();
                    this.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(this,"Automotora ya ingresada o datos incorrectos");
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
