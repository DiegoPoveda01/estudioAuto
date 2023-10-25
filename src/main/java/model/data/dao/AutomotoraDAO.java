package model.data.dao;

import model.Automotora;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Result;
import org.jooq.Table;
import org.jooq.impl.DSL;

import java.util.List;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.table;

public class AutomotoraDAO {
    public static void agregarAutomotora(DSLContext query, Automotora automotora){
        Table tablaAutomotora= table(name("Automotora"));
        Field[] columnas = tablaAutomotora.fields("nombre","direccion","redes");
        query.insertInto(tablaAutomotora, columnas[0], columnas[1],columnas[2])
                .values(automotora.getNombre(), automotora.getDireccion(), automotora.getRedes())
                .execute();
    }
    public static boolean validarExistenciaAutomotora(DSLContext query,String columnaTabla, Object dato){
        Result resultados = query.select().from(DSL.table("Automotora")).where(DSL.field(columnaTabla).eq(dato)).fetch();
        if(resultados.size()>=1){
            return true;
        }
        else{
            return false;
        }
    }
    public static Automotora buscarAutomotora(DSLContext query, Object dato){
        Result resultados= (Result) buscarAutomotora(query,"nombre_automotora",dato);
        String nombreAutomotora = (String) resultados.getValue(0,"nombre_automotora");
        String direccionAutomotora = (String) resultados.getValue(0,"direccion");
        String redesAutomotora = (String) resultados.getValue(0,"redes");
        return new Automotora(nombreAutomotora, direccionAutomotora, redesAutomotora);
    }

    public static List buscarAutomotora(DSLContext query, String columnaTabla, Object dato){
        Result resultados = query.select().from(DSL.table("Automotora")).where(DSL.field(columnaTabla).eq(dato)).fetch();
        return resultados;
    }
    public static Object[] getNombreAutomotoras(DSLContext query){
        Table automotora= DSL.table("Automotora");
        Result resultados = query.select(automotora.field("nombre_automotora")).from(automotora).fetch();
        if(resultados.size()==0){
            return new String[]{"Error no existen automotoras"};
        }
        else {
            return resultados.getValues("nombre_automotora").toArray();
        }
    }
}
