package model.data;

import org.jooq.DSLContext;
import org.jooq.DataType;
import org.jooq.impl.DSL;

import java.sql.Connection;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.*;

public class DBGenerator {

    public static void iniciarBD(String nombreBD) throws ClassNotFoundException {
        Connection connection = DBConnector.connection("root","");
        DSLContext create = DSL.using(connection);
        crearBaseDato(create,nombreBD);
        create = actualizarConexion(connection,nombreBD);
        crearTablaAutomotora(create);
        crearTablaAuto(create);
        relacionarTabla(create,"Auto","nombre_automotora","Automotora");
        DBConnector.closeConnection();

    }
    public static DSLContext conectarBD(String nombre) throws ClassNotFoundException {
        Connection connection = DBConnector.connection(nombre,"root","");
        DSLContext create = DSL.using(connection);
        return create;
    }
    private static void crearBaseDato(DSLContext create, String nombreBD){
        create.createDatabaseIfNotExists(nombreBD).execute();
    }

    private static DSLContext actualizarConexion(Connection connection,String nombreBD){
        DBConnector.closeConnection();
        connection= DBConnector.connection(nombreBD,"root","");
        DSLContext create=DSL.using(connection);
        return create;
    }

    private static void crearTablaAutomotora(DSLContext create){
        create.createTableIfNotExists("Automotora")
                .column("nombre_automotora",VARCHAR(100))
                .column("direccion",VARCHAR(50))
                .column("redes",VARCHAR(50))
                .constraint(primaryKey("nombre_automotora")).execute();
    }

    private static void crearTablaAuto(DSLContext create){
        create.createTableIfNotExists("Auto").column("marca",VARCHAR(50))
                .column("modelo",VARCHAR(100))
                .column("color",VARCHAR(50))
                .column("nombre_automotora",VARCHAR(50))
                .constraint(primaryKey("modelo")).execute();

    }
    private static void relacionarTabla(DSLContext create, String nombreTabla, String claveForanea, String nombreTablaRelacion){
      //  create.alterTableIfExists(nombreTabla).add(foreignKey(claveForanea).references(nombreTablaRelacion)).execute();
        create.alterTableIfExists(nombreTabla).alterConstraint(foreignKey(claveForanea).references(nombreTablaRelacion)).enforced();
    }
    private static void agregarColumnaTabla(DSLContext create, String nombreTabla, String columna, DataType tipoColumna){
        create.alterTableIfExists(nombreTabla).addColumn(columna,tipoColumna);
    }

}
