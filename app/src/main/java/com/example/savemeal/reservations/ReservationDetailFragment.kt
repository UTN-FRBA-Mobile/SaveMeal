package com.example.savemeal.reservations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.savemeal.CodeDialogFragment
import com.example.savemeal.databinding.FragmentReservationDetailBinding
import com.example.savemeal.domain.reservation.ReservationViewModel

class ReservationDetailFragment : Fragment() {
    private var _binding: FragmentReservationDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ReservationViewModel by viewModels()

    private var reservationCode: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReservationDetailBinding.inflate(inflater, container, false)
        val reservationId = arguments?.getInt("reservationId")!!
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
            val dialog = CodeDialogFragment(reservationCode)
            dialog.show(parentFragmentManager, "No se que es el tag")
        }
    }
}
