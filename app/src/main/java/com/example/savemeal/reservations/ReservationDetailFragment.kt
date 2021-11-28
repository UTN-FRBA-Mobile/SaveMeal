package com.example.savemeal.reservations

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.savemeal.CodeDialogFragment
import com.example.savemeal.R
import com.example.savemeal.databinding.FragmentReservationDetailBinding
import com.example.savemeal.domain.reservation.ReservationViewModel

class ReservationDetailFragment : Fragment() {
    private var _binding: FragmentReservationDetailBinding? = null
    private val binding get() = _binding!!
    private var reservationId = 0
    private val viewModel: ReservationViewModel by viewModels()

    private var reservationCode: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReservationDetailBinding.inflate(inflater, container, false)
        reservationId = arguments?.getInt("reservationId")!!
        bindUI(reservationId)

        return binding.root
    }

    private fun bindUI(reservationId: Int) {
        val reservation = viewModel.getReservationDetail(reservationId)
        reservationCode = reservation.token
        _binding?.apply {
            expirationDate.text = reservation.comida.expiracion
            title.text = reservation.comida.nombre
            businessHours.text = reservation.business.businessHours
            businessAddress.text = reservation.business.address
            businessName.text = reservation.business.businessName
            availables.text = reservation.comida.disponibles.toString()
            detail.text = reservation.comida.detalle
        }
    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonObtainCode.setOnClickListener {
<<<<<<< HEAD
            val dialog = CodeDialogFragment(reservationCode)
=======
            val dialog = CodeDialogFragment()
>>>>>>> 0579e92 (Reservation cancel dialog)
            dialog.show(parentFragmentManager, "No se que es el tag")
        }

        binding.buttonCancelar.setOnClickListener {
            val warning_dialog = AlertDialog.Builder(context)
            warning_dialog.setMessage("¿Desea cancelar la reserva?")
                .setNegativeButton("No"){ _, _->}
                .setPositiveButton("Si"){dialog, which ->
                    viewModel.cancelReservation(reservationId)
                    val action = R.id.action_reservationDetailFragment_to_reservationsFragment
                    findNavController().navigate(action)
                    Toast.makeText(context, "Reserva cancelada", Toast.LENGTH_LONG).show()
                }
                .show()

        }

    }
}
