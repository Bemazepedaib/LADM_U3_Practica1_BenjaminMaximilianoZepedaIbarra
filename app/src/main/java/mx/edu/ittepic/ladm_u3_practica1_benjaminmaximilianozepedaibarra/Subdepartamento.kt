package mx.edu.ittepic.ladm_u3_practica1_benjaminmaximilianozepedaibarra

import android.content.ContentValues
import android.database.sqlite.SQLiteException
import androidx.fragment.app.FragmentActivity

class Subdepartamento(este: FragmentActivity) {
    private var este = este
    private var err = ""

    fun mostrarPDivision(divisionBuscar : String) : ArrayList<String>{
        var baseDatos = BaseDatos(este, "mapeo_empresas", null, 1)
        val arreglo = ArrayList<String>()
        err = ""
        try {
            var tabla = baseDatos.readableDatabase
            var SQL_SELECT = "SELECT * FROM SUBDEPARTAMENTO S JOIN AREA A ON(S.IDAREA = A.IDAREA) WHERE A.DIVISION=?"
            var cursor = tabla.rawQuery(SQL_SELECT, arrayOf(divisionBuscar))
            if(cursor.moveToFirst()){
                do {
                    val areaCad =   "IdSubdepto:${cursor.getString(0)}\n" +
                            "IdEdificio:${cursor.getString(1)}\n" +
                            "Piso:${cursor.getString(2)}\n" +
                            "IdArea:${cursor.getInt(3)}\n"
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
            var SQL_SELECT = "SELECT * FROM SUBDEPARTAMENTO S JOIN AREA A ON(S.IDAREA = A.IDAREA) WHERE A.DESCRIPCION=?"
            var cursor = tabla.rawQuery(SQL_SELECT, arrayOf(descripcionBuscar))
            if(cursor.moveToFirst()){
                do {
                    val areaCad =   "IdSubdepto:${cursor.getString(0)}\n" +
                            "IdEdificio:${cursor.getString(1)}\n" +
                            "Piso:${cursor.getString(2)}\n" +
                            "IdArea:${cursor.getInt(3)}\n"
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

    fun mostrarPIdEdificio(idEdificio: String) : ArrayList<String>{
        var baseDatos = BaseDatos(este, "mapeo_empresas", null, 1)
        val arreglo = ArrayList<String>()
        err = ""
        try {
            var tabla = baseDatos.readableDatabase
            var SQL_SELECT = "SELECT * FROM SUBDEPARTAMENTO WHERE IDEDIFICIO=?"
            var cursor = tabla.rawQuery(SQL_SELECT, arrayOf(idEdificio))
            if(cursor.moveToFirst()){
                do {
                    val areaCad =   "IdSubdepto:${cursor.getString(0)}\n" +
                            "IdEdificio:${cursor.getString(1)}\n" +
                            "Piso:${cursor.getString(2)}\n" +
                            "IdArea:${cursor.getInt(3)}\n"
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

    fun mostrarPArea(idArea: Int) : ArrayList<String>{
        var baseDatos = BaseDatos(este, "mapeo_empresas", null, 1)
        val arreglo = ArrayList<String>()
        err = ""
        try {
            var tabla = baseDatos.readableDatabase
            var SQL_Select = "SELECT * FROM SUBDEPARTAMENTO WHERE IDAREA=?"
            var cursor = tabla.rawQuery(SQL_Select, arrayOf(idArea.toString()))
            if(cursor.moveToFirst()){
                do {
                    val areaCad =   "IdSubdepto:${cursor.getString(0)}\n" +
                            "IdEdificio:${cursor.getString(1)}\n" +
                            "Piso:${cursor.getString(2)}\n" +
                            "IdArea:${cursor.getInt(3)}\n"
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

    fun insertar(idEdificio : String, piso : String, idArea : Int): Boolean{
        var baseDatos = BaseDatos(este, "mapeo_empresas", null, 1)
        err = ""
        try {
            var tabla = baseDatos.writableDatabase
            var datos = ContentValues()
            datos.put("IDEDIFICIO", idEdificio)
            datos.put("PISO", piso)
            datos.put("IDAREA", idArea)
            var resultado = tabla.insert("SUBDEPARTAMENTO", "IDSUBDEPTO", datos)
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

    fun eliminar(idSubEliminar: String) : Boolean{
        var baseDatos = BaseDatos(este, "mapeo_empresas", null, 1)
        err = ""
        try {
            var tabla = baseDatos.writableDatabase
            val resultado = tabla.delete("SUBDEPARTAMENTO","IDSUBDEPTO=?",arrayOf(idSubEliminar))
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
    fun actualizar(idSub : String, idEdificio: String, Piso:String, idArea: Int) : Boolean {
        var baseDatos = BaseDatos(este, "mapeo_empresas", null, 1)
        err = ""
        try {
            var tabla = baseDatos.writableDatabase
            val datosActualizados = ContentValues()
            datosActualizados.put("IDEDIFICIO", idEdificio)
            datosActualizados.put("PISO", Piso)
            datosActualizados.put("IDAREA", idArea)
            val resultado = tabla.update("SUBDEPARTAMENTO", datosActualizados,
                "IDSUBDEPTO=?", arrayOf(idSub))
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