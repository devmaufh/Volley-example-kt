package com.devmaufh.volley_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.devmaufh.volley_example.API.VolleySingleton
import com.devmaufh.volley_example.Utils.Utils
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    var counter=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun insertaClick(view:View){
        insertaTarea("Tarea $counter","Descripcion numero $counter")
        counter++
    }
    fun consultaTareas(view:View){
        val url=Utils.HOST+Utils.SERVICE+"get.php"
        Log.w("SERVICE",url)
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,url , null,
            Response.Listener { response ->
                for (i in 0..(response.length()-1)){
                    val tarea=response.getJSONObject(i)
                    Log.w("TAREAS: ","${tarea["id_tarea"]}: ${tarea["titulo"]}")
                }

            },
            Response.ErrorListener { error ->
                Log.w("SERVICE: ",error.message)
                Toast.makeText(this, "Mal ):", Toast.LENGTH_SHORT).show();
            }
        )
        VolleySingleton.getInstance(this).addToRequestQueue(jsonArrayRequest)
    }

    fun insertaTarea(titulo:String,descripcion:String){

        val params=HashMap<String,String>()
        params["titulo"]=titulo
        params["descripcion"]=descripcion
        val jsonObject=JSONObject(params as Map<*, *>)
        val url=Utils.HOST+Utils.SERVICE+"insert.php"
        val jsonObjectRequest= JsonObjectRequest(Request.Method.POST,url, jsonObject,Response.Listener { response ->
            Log.w("TAREAS: ","MENSAJE: ${response["message"]} | ID: ${response["id"]}")
            Toast.makeText(this , "EXITO", Toast.LENGTH_SHORT).show();
        },Response.ErrorListener { error ->
            Toast.makeText(this, "Error al insertar", Toast.LENGTH_SHORT).show();
        })
        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)


    }
}
