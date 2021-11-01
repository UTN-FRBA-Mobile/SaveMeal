package com.example.savemeal

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.savemeal.active_deliveries.ActiveDeliveriesAdapter
import com.example.savemeal.databinding.FragmentActiveDeliveriesBinding

class ActiveDeliveriesFragment : Fragment() {
    private var _binding: FragmentActiveDeliveriesBinding? = null
    private val binding get() = _binding!!

    private var dataList: ArrayList<String>? = null
    var adapter: ActiveDeliveriesAdapter? = null
    var lista: RecyclerView? = null
    var layoutManager: RecyclerView.LayoutManager? = null

    var seleccion: Int? = null
    private var borrarButton: Button? = null
    private var editarButton: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentActiveDeliveriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataList = ArrayList()
        rellenarLista(dataList!!)
        lista = binding.entregasRecyclerView
        layoutManager = LinearLayoutManager(this.context)
        adapter = ActiveDeliveriesAdapter(dataList!!)
        lista?.layoutManager = layoutManager
        lista?.adapter = adapter

        borrarButton = binding.buttonDelete
        editarButton = binding.buttonEdit

        adapter!!.setOnItemClickListener(object: ActiveDeliveriesAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                seleccion = position
            }
        })

        borrarButton!!.setOnClickListener {
            if(seleccion==null)
            {
                Toast.makeText(this.context, "Seleccione una entrega", Toast.LENGTH_SHORT).show()
            } else {
                dataList!!.removeAt(seleccion!!)
                refreshFragment()
                Toast.makeText(this.context, "Entrega eliminada!", Toast.LENGTH_SHORT).show()


            }
        }


    }

    fun refreshFragment() {


    }

    private fun rellenarLista(dataList: ArrayList<String>) {
        dataList.add("Hamburguesa")
        dataList.add("Pizza")
        dataList.add("Tallarines")
    }


}