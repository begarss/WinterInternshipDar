package com.example.tictactoe

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.tictactoe.databinding.FragmentNamesBinding

class NamesFragment : Fragment() {

    private var _binding: FragmentNamesBinding? = null
    private val binding get() = _binding!!
    private var player1Name: String = ""
    private var player2Name = ""

    private val gameManager = GameManager.getGameManager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNamesBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





        binding.btnPlay.setOnClickListener {
            if (addPlayer()) {
                Log.d("IPP", "onViewCreated: $player1Name $player2Name")
                val bundle = bundleOf("X" to  player1Name, "O" to  player2Name)
                view.findNavController().navigate(R.id.action_namesFragment_to_gameFragment, bundle)
            }
        }
    }


    private fun addPlayer(): Boolean {
        player1Name = binding.edtUser1.text.toString()
        player2Name = binding.edtUser2.text.toString()
        if (player1Name.isNotEmpty() && player2Name.isNotEmpty()) {
            gameManager.addPlayer(player1Name)
            gameManager.addPlayer(player2Name)
            return true
        }

        return false
    }

}