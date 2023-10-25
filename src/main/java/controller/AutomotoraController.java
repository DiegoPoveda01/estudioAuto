package controller;

import model.Automotora;
import model.data.dao.AutomotoraDAO;
import model.data.DBConnector;
import model.data.DBGenerator;
import org.jooq.DSLContext;

public class AutomotoraController {

    public static boolean añadirAutomotora(String nombreAutomotora, String direccionAutomotora, String redesAutomotora) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD("Automotriz");
        if(!AutomotoraDAO.validarExistenciaAutomotora(query,"nombre_automotora",nombreAutomotora)){
            Automotora automotora = new Automotora(nombreAutomotora,direccionAutomotora,redesAutomotora);
            AutomotoraDAO.agregarAutomotora(query, automotora);
            DBConnector.closeConnection();
            return true;
        }
        else{
            return false;
        }
    }
    public static Object[] getNombreAutomotoras() throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD("Automotriz");
        Object[] carreras = AutomotoraDAO.getNombreAutomotoras(query);
        DBConnector.closeConnection();
            return carreras;
    }

}
