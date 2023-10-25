package controller;

import model.Automotora;
import model.Auto;
import model.data.dao.AutomotoraDAO;
import model.data.DBConnector;
import model.data.DBGenerator;
import model.data.dao.AutoDAO;
import org.jooq.DSLContext;

public class AutoController {

    public static boolean registrarAuto(String marca, String modelo, String color, String nombreAutomotora) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD("Automotriz");
        if(!AutoDAO.validarExistenciaAuto(query,"modelo",modelo)){
            Automotora automotora = AutomotoraDAO.buscarAutomotora(query,nombreAutomotora);
            Auto auto = new Auto(marca,modelo,color, automotora);
            AutoDAO.agregarAuto(query, auto);
            DBConnector.closeConnection();
            return true;
        }
        else{
            DBConnector.closeConnection();
            return false;
        }
    }
}
