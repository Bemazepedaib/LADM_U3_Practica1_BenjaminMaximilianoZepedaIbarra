package mx.edu.ittepic.ladm_u3_practica1_benjaminmaximilianozepedaibarra.ui.home

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.edu.ittepic.ladm_u3_practica1_benjaminmaximilianozepedaibarra.Area
import mx.edu.ittepic.ladm_u3_practica1_benjaminmaximilianozepedaibarra.MainActivity
import mx.edu.ittepic.ladm_u3_practica1_benjaminmaximilianozepedaibarra.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private var id = "-1"
    private var forma = ""
    private var criterio = ""
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.bDescripcion.setOnClickListener {
            forma = "Descripcion"
            criterio = binding.tBusqueda.text.toString()
            mostrarDatos(forma, criterio)
        }

        binding.bDivision.setOnClickListener {
            forma = "Division"
            criterio = binding.tBusqueda.text.toString()
            mostrarDatos(forma, criterio)
        }

        binding.bInsertar.setOnClickListener {
            var area = Area(requireActivity())
            var descripcion = binding.aDescripcion.text.toString()
            var division = binding.aDivision.text.toString()
            var nEmpleados = binding.aCEmpleados.text.toString().toInt()
            if (area.insertar(descripcion,division,nEmpleados)){
                Toast.makeText(requireContext(), "Se ha agregado correctamente", Toast.LENGTH_SHORT)
                    .show()
                if (criterio.equals("")){

                } else {
                    mostrarDatos(forma, criterio)
                }
            } else {
                Toast.makeText(requireContext(), "No se ha agregado el registro", Toast.LENGTH_SHORT)
                    .show()
            }
            binding.aDescripcion.setText("")
            binding.aDivision.setText("")
            binding.aCEmpleados.setText("")
        }

        binding.bEliminar.setOnClickListener {
            if (id.equals("-1")){
                Toast.makeText(requireContext(),
                    "Elije un dato del listView",
                    Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else {
                var area = Area(requireActivity())
                if (area.eliminar(id)){
                    Toast.makeText(requireContext(),
                        "Dato eliminado exitosamente",
                        Toast.LENGTH_SHORT)
                        .show()
                    binding.aDescripcion.setText("")
                    binding.aDivision.setText("")
                    binding.aCEmpleados.setText("")
                    mostrarDatos(forma, criterio)
                    return@setOnClickListener
                } else {
                    Toast.makeText(requireContext(),
                        "No se ha eliminado de forma correcta",
                        Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }
            }
        }

        binding.bActualizar.setOnClickListener {
            if (id.equals("-1")){
                Toast.makeText(requireContext(),
                    "Elije un dato del listView",
                    Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else {
                var area = Area(requireActivity())
                var descripcion = binding.aDescripcion.text.toString()
                var division = binding.aDivision.text.toString()
                var nEmpleados = binding.aCEmpleados.text.toString().toInt()
                if (area.actualizar(id, descripcion,division,nEmpleados)){
                    Toast.makeText(requireContext(),
                        "Dato actualizado exitosamente",
                        Toast.LENGTH_SHORT)
                        .show()
                    binding.aDescripcion.setText("")
                    binding.aDivision.setText("")
                    binding.aCEmpleados.setText("")
                    mostrarDatos(forma, criterio)
                    return@setOnClickListener
                } else {
                    Toast.makeText(requireContext(),
                        "No se ha actualizado de forma correcta",
                        Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }
            }
        }

        binding.listaDatos.setOnItemClickListener{ adapterView, view, i, l ->
            var dato = adapterView.getItemAtPosition(i).toString()
            var datos = dato.split("\n")
            var splits0 = datos[0].split(":")
            var splits1 = datos[1].split(":")
            var splits2 = datos[2].split(":")
            var splits3 = datos[3].split(":")
            id = splits0[1]
            binding.aDescripcion.setText(splits1[1])
            binding.aDivision.setText(splits2[1])
            binding.aCEmpleados.setText(splits3[1])
        }
        return root
    }

    fun mostrarDatos(forma : String, criterio : String){
        var area = Area(requireActivity())
        val arreglo : ArrayList<String>
        when (forma){
            "Division" -> {
                arreglo = area.mostrarPDivision(criterio)
                if (!arreglo.isEmpty()){
                    binding.listaDatos.adapter= ArrayAdapter<String>(requireActivity(),
                        R.layout.simple_list_item_1, arreglo)
                } else {
                    binding.listaDatos.adapter= ArrayAdapter<String>(requireActivity(),
                        R.layout.simple_list_item_1, arreglo)
                    Toast.makeText(requireContext(),
                        "No se encontraron datos",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            }
            "Descripcion" -> {
                val arreglo = area.mostrarPDescripcion(criterio)
                if (!arreglo.isEmpty()){
                    binding.listaDatos.adapter= ArrayAdapter<String>(requireActivity(),
                        R.layout.simple_list_item_1, arreglo)
                } else {
                    binding.listaDatos.adapter= ArrayAdapter<String>(requireActivity(),
                        R.layout.simple_list_item_1, arreglo)
                    Toast.makeText(requireContext(),
                        "No se encontraron datos",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}