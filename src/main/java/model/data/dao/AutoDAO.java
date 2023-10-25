package model.data.dao;

import model.Auto;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.VARCHAR;

public class AutoDAO {
    
    public static void agregarAuto(DSLContext query, Auto auto){
        Table tablaAuto= table(name("Auto"));
        Field[] columnas = tablaAuto.fields("marca","modelo","color","nombre_automotora");
        query.insertInto(tablaAuto, columnas[0], columnas[1],columnas[2],columnas[3])
                .values(auto.getMarca(), auto.getModelo(), auto.getColor(), auto.getAutomotora().getNombre())
                .execute();

    }
    public void modificarAuto(DSLContext query, String modelo, String columnaTabla, Object dato){
      query.update(DSL.table("Auto")).set(DSL.field(columnaTabla),dato).
              where(DSL.field("modelo").eq(modelo)).execute();
    }
    public List obtenerAuto(DSLContext query, String columnaTabla, Object dato){
        Result resultados = query.select().from(DSL.table("Auto")).where(DSL.field(columnaTabla).eq(dato)).fetch();
        return obtenerListaAutos(resultados);
    }
    public void eliminarAuto(DSLContext query, String modelo){
        Table tablaEstudiante= table(name("Auto"));
        query.delete(DSL.table("Estudiante")).where(DSL.field("modelo").eq(modelo)).execute();
    }
    private List obtenerListaAutos(Result resultados){
        List<Auto> autos = new ArrayList<>();
        for(int fila=0; fila<resultados.size();fila++){
            String marca = (String) resultados.getValue(fila,"marca");
            String modelo = (String) resultados.getValue(fila,"modelo");
            String color = (String) resultados.getValue(fila,"color");
            autos.add(new Auto(marca,modelo,color,null));
        }
        return autos;
    }
    public static boolean validarExistenciaAuto(DSLContext query,String columnaTabla, Object dato){
        Result resultados = query.select().from(DSL.table("Auto")).where(DSL.field(columnaTabla).eq(dato)).fetch();
        if(resultados.size()>=1){
            return true;
        }
        else{
            return false;
        }
    }
}
