package mx.edu.ittepic.ladm_u3_practica1_benjaminmaximilianozepedaibarra.ui.dashboard

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
import mx.edu.ittepic.ladm_u3_practica1_benjaminmaximilianozepedaibarra.Subdepartamento
import mx.edu.ittepic.ladm_u3_practica1_benjaminmaximilianozepedaibarra.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private var idArea="-1"
    private var idSub="-1"
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val area = Area(requireActivity())
        val arreglo : ArrayList<String>
        arreglo = area.consultar()

        binding.listaDatosArea.adapter= ArrayAdapter<String>(requireActivity(),
            R.layout.simple_list_item_1, arreglo)

        binding.bDivision.setOnClickListener {
            var division = binding.tBusqueda.text.toString()
            var arregloSub : ArrayList<String>
            if (division.isEmpty()){
                Toast.makeText(requireContext(), "Escriba algo en el cámpo de búsqueda", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else {
                var subDpto = Subdepartamento(requireActivity())
                arregloSub = subDpto.mostrarPDivision(division)
                if (arregloSub.isEmpty()){
                    Toast.makeText(requireContext(),
                        "No se encontraron datos",
                        Toast.LENGTH_SHORT)
                        .show()
                } else {
                    binding.listaDatosEdificio.adapter= ArrayAdapter<String>(requireActivity(),
                        R.layout.simple_list_item_1, arregloSub)
                    Toast.makeText(requireContext(),
                        "Los datos se han mostrado en el listView derecho, el izquierdo " +
                                "no interactua en este consulta",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        binding.bDescripcion.setOnClickListener {
            var descripcion = binding.tBusqueda.text.toString()
            var arregloSub : ArrayList<String>
            if (descripcion.isEmpty()){
                Toast.makeText(requireContext(), "Escriba algo en el cámpo de búsqueda", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else {
                var subDpto = Subdepartamento(requireActivity())
                arregloSub = subDpto.mostrarPDescripcion(descripcion)
                if (arregloSub.isEmpty()){
                    Toast.makeText(requireContext(),
                        "No se encontraron datos",
                        Toast.LENGTH_SHORT)
                        .show()
                } else {
                    binding.listaDatosEdificio.adapter= ArrayAdapter<String>(requireActivity(),
                        R.layout.simple_list_item_1, arregloSub)
                    Toast.makeText(requireContext(),
                        "Los datos se han mostrado en el listView derecho, el izquierdo " +
                                "no interactua en este consulta",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        binding.bIdEdificio.setOnClickListener {
            var idEdificio = binding.tBusqueda.text.toString()
            var arregloSub : ArrayList<String>
            if (idEdificio.isEmpty()){
                Toast.makeText(requireContext(), "Escriba algo en el cámpo de búsqueda", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else {
                var subDpto = Subdepartamento(requireActivity())
                arregloSub = subDpto.mostrarPIdEdificio(idEdificio)
                if (arregloSub.isEmpty()){
                    Toast.makeText(requireContext(),
                        "No se encontraron datos",
                        Toast.LENGTH_SHORT)
                        .show()
                } else {
                    binding.listaDatosEdificio.adapter= ArrayAdapter<String>(requireActivity(),
                        R.layout.simple_list_item_1, arregloSub)
                    Toast.makeText(requireContext(),
                        "Los datos se han mostrado en el listView derecho, el izquierdo " +
                                "no interactua en este consulta",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        binding.listaDatosArea.setOnItemClickListener{ adapterView, view, i, l ->
            var dato = adapterView.getItemAtPosition(i).toString()
            var datos = dato.split("\n")
            var splits0 = datos[0].split(":")
            idArea = splits0[1]
            mostrarDatos(idArea)
        }

        binding.listaDatosEdificio.setOnItemClickListener { adapterView, view, i, l ->
            var dato = adapterView.getItemAtPosition(i).toString()
            var datos = dato.split("\n")
            var splits0 = datos[0].split(":")
            var splits1 = datos[1].split(":")
            var splits2 = datos[2].split(":")
            var splits3 = datos[3].split(":")
            idSub = splits0[1]
            idArea = splits3[1]
            binding.aIEdificio.setText(splits1[1])
            binding.aPiso.setText(splits2[1])
        }

        binding.bInsertar.setOnClickListener {
            if (idArea.equals("-1")){
                Toast.makeText(requireContext(), "Seleccione un Area primero", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else {
                var subDpto = Subdepartamento(requireActivity())
                var idEdificio = binding.aIEdificio.text.toString()
                var piso = binding.aPiso.text.toString()
                subDpto.insertar(idEdificio,piso,idArea.toInt())
                mostrarDatos(idArea)
                binding.aIEdificio.setText("")
                binding.aPiso.setText("")
                idArea="-1"
                Toast.makeText(requireContext(), "Se ha agregado correctamente", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.bActualizar.setOnClickListener {
            if (idSub.equals("-1")){
                Toast.makeText(requireContext(), "Seleccione un SubDepartamento primero", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else {
                var subDpto = Subdepartamento(requireActivity())
                var idEdificio = binding.aIEdificio.text.toString()
                var piso = binding.aPiso.text.toString()
                subDpto.actualizar(idSub, idEdificio, piso, idArea.toInt())
                mostrarDatos(idArea)
                binding.aIEdificio.setText("")
                binding.aPiso.setText("")
                idSub="-1"
                Toast.makeText(requireContext(), "Se ha actualizado correctamente", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.bEliminar.setOnClickListener {
            if(idSub.equals("-1")){
                Toast.makeText(requireContext(), "Seleccione un SubDepartamento primero", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else {
                var subDpto = Subdepartamento(requireActivity())
                subDpto.eliminar(idSub)
                mostrarDatos(idArea)
                binding.aIEdificio.setText("")
                binding.aPiso.setText("")
                idSub="-1"
                Toast.makeText(requireContext(), "Se ha eliminado correctamente", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        return root
    }

    fun mostrarDatos(idArea : String){
        var subDpto = Subdepartamento(requireActivity())
        var arregloSub = subDpto.mostrarPArea(idArea.toInt())
        binding.listaDatosEdificio.adapter= ArrayAdapter<String>(requireActivity(),
            R.layout.simple_list_item_1, arregloSub)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}