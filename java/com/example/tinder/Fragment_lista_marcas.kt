package com.example.tinder

import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.tinder.databinding.FragmentListaBrandBinding

class ListaMarcasFragment : Fragment() {
    private var _binding: FragmentListaBrandBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MarcasViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListaBrandBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fotosAdapter = FotosPagerAdapter(this)
        // Configurando el ViewPager con las fotos
        binding.viewPager.adapter = fotosAdapter
        viewModel.listaMarcas.observe(viewLifecycleOwner) { marcas ->
            fotosAdapter.updateFotos(marcas[0].fotos, marcas[0].nombre) // Actualizando el adapter con las fotos de la primera marca
            setupIndicator(marcas[0].fotos.size)
        }
        binding.btnMisLikes.setOnClickListener {
            // Navegar al fragmento MisLikes
            findNavController().navigate(R.id.misLikesFragment)
        }
        // Configurando los botones like y dislike
        binding.btnLike.setOnClickListener {
            viewModel.likeActual()  // Añade la marca a la lista de likes
        }
        binding.btnDislike.setOnClickListener {
            viewModel.dislikeActual()
        }
        viewModel.marcaActual.observe(viewLifecycleOwner) { marca ->  // Observando el cambio de marca actual
            fotosAdapter.updateFotos(marca.fotos, marca.nombre)
            setupIndicator(marca.fotos.size)
        }
        // Cambio de página en el ViewPager
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateIndicator(position)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupIndicator(count: Int) {
        val indicatorLayout = binding.indicator
        indicatorLayout.removeAllViews()
        val indicatorWidth = Resources.getSystem().displayMetrics.widthPixels
        val squareWidth = indicatorWidth / count // Ancho proporcional
        // Creamos un cuadrado por cada imagen
        for (i in 0 until count) {
            val square = View(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(
                    squareWidth,
                    20
                ).apply {
                    marginStart = 4
                    marginEnd = 4
                }
                setBackgroundColor(Color.GRAY)
            }
            indicatorLayout.addView(square)
        }
    }

    private fun updateIndicator(selectedPosition: Int) {
        val indicatorLayout = binding.indicator
        for (i in 0 until indicatorLayout.childCount) {
            val square = indicatorLayout.getChildAt(i)
            square.setBackgroundColor(if (i == selectedPosition) Color.BLACK else Color.GRAY)
        }
    }
}
