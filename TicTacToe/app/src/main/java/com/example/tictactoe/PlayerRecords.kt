package com.example.tictactoe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tictactoe.databinding.FragmentGameBinding
import com.example.tictactoe.databinding.FragmentPlayerRecordsBinding

class PlayerRecords : Fragment() {


    private var _binding: FragmentPlayerRecordsBinding? = null
    private val binding get() = _binding!!
    private val gameManager = GameManager.getGameManager()
    private lateinit var adapter: RecordAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentPlayerRecordsBinding.inflate(inflater, container, false)
        setAdapters()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fbBack.setOnClickListener {
            gameManager.reset()
            findNavController().navigate(PlayerRecordsDirections.clearBackStack())
        }
    }

    private fun setAdapters(){
        adapter = RecordAdapter()
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.layoutManager = layoutManager
        adapter.submitList(gameManager.getPlayers())
        binding.recycler.adapter = adapter
    }
}