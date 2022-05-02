package mx.edu.ittepic.ladm_u3_practica1_benjaminmaximilianozepedaibarra

import android.content.ContentValues
import android.database.sqlite.SQLiteException
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity

class Area(este: FragmentActivity) {
    private var este = este
    private var err = ""

    fun consultar() : ArrayList<String>{
        var baseDatos = BaseDatos(este, "mapeo_empresas", null, 1)
        val arreglo = ArrayList<String>()
        err = ""
        try {
            var tabla = baseDatos.readableDatabase
            var SQL_SELECT = "SELECT * FROM AREA"
            var cursor = tabla.rawQuery(SQL_SELECT, null)
            if(cursor.moveToFirst()){
                do {
                    val areaCad =   "Id:${cursor.getString(0)}\n" +
                            "Descripcion:${cursor.getString(1)}\n" +
                            "Division:${cursor.getString(2)}\n" +
                            "Empleados:${cursor.getInt(3)}\n"
                    arreglo.add(areaCad)
                } while (cursor.moveToNext())
            }
        } catch (err : SQLiteException) {
            this.err = err.message!!
        } finally {
            baseDatos.close()
        }
        return arreglo
    }

    fun mostrarPDescripcion(descripcionBuscar : String) : ArrayList<String>{
        var baseDatos = BaseDatos(este, "mapeo_empresas", null, 1)
        val arreglo = ArrayList<String>()
        err = ""
        try {
            var tabla = baseDatos.readableDatabase
            var SQL_SELECT = "SELECT * FROM AREA WHERE DESCRIPCION=?"
            var cursor = tabla.rawQuery(SQL_SELECT, arrayOf(descripcionBuscar))
            if(cursor.moveToFirst()){
                do {
                    val areaCad =   "Id:${cursor.getString(0)}\n" +
                                    "Descripcion:${cursor.getString(1)}\n" +
                                    "Division:${cursor.getString(2)}\n" +
                                    "Empleados:${cursor.getInt(3)}\n"
                    arreglo.add(areaCad)
                } while (cursor.moveToNext())
            }
        } catch (err : SQLiteException) {
            this.err = err.message!!
        } finally {
            baseDatos.close()
        }
        return arreglo
    }

    fun mostrarPDivision(divisionBuscar : String) : ArrayList<String>{
        var baseDatos = BaseDatos(este, "mapeo_empresas", null, 1)
        val arreglo = ArrayList<String>()
        err = ""
        try {
            var tabla = baseDatos.readableDatabase
            var SQL_SELECT = "SELECT * FROM AREA WHERE DIVISION=?"
            var cursor = tabla.rawQuery(SQL_SELECT, arrayOf(divisionBuscar))
            if(cursor.moveToFirst()){
                do {
                    val areaCad =   "Id:${cursor.getString(0)}\n" +
                                    "Descripcion:${cursor.getString(1)}\n" +
                                    "Division:${cursor.getString(2)}\n" +
                                    "Empleados:${cursor.getInt(3)}\n"
                    arreglo.add(areaCad)
                } while (cursor.moveToNext())
            }
        } catch (err : SQLiteException) {
            this.err = err.message!!
        } finally {
            baseDatos.close()
        }
        return arreglo
    }

    fun insertar(descripcion : String, division : String, cantidad_Empleados : Int): Boolean{
        var baseDatos = BaseDatos(este, "mapeo_empresas", null, 1)
        err = ""
        try {
            var tabla = baseDatos.writableDatabase
            var datos = ContentValues()
            datos.put("DESCRIPCION", descripcion)
            datos.put("DIVISION", division)
            datos.put("CANTIDAD_EMPLEADOS", cantidad_Empleados)
            var resultado = tabla.insert("AREA", "IDAREA", datos)
            if (resultado == -1L){
                return false
            }
        } catch (err : SQLiteException){
            this.err = err.message!!
            return false
        } finally {
            baseDatos.close()
        }
        return true
    }

    fun eliminar(idAreaEliminar: String) : Boolean{
        var baseDatos = BaseDatos(este, "mapeo_empresas", null, 1)
        err = ""
        try {
            var tabla = baseDatos.writableDatabase
            val resultado = tabla.delete("AREA","IDAREA=?",arrayOf(idAreaEliminar))
            if (resultado == 0){
                return false
            }
        } catch (err : SQLiteException) {
            this.err = err.message!!
            return false
        } finally {
            baseDatos.close()
        }
        return true
    }

    fun actualizar(idArea : String, descripcion: String, division: String, cantidad_Empleados: Int) : Boolean {
        var baseDatos = BaseDatos(este, "mapeo_empresas", null, 1)
        err = ""
        try {
            var tabla = baseDatos.writableDatabase
            val datosActualizados = ContentValues()
            datosActualizados.put("DESCRIPCION", descripcion)
            datosActualizados.put("DIVISION", division)
            datosActualizados.put("CANTIDAD_EMPLEADOS", cantidad_Empleados)
            val resultado = tabla.update("AREA", datosActualizados,
                "IDAREA=?", arrayOf(idArea))
            if (resultado == 0){
                return false
            }
        } catch (err : SQLiteException) {
            this.err = err.message!!
            return false
        } finally {
            baseDatos.close()
        }
        return true
    }
}