package com.example.tictactoe

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.tictactoe.databinding.FragmentGameBinding
import kotlinx.android.synthetic.main.fragment_game.*
import org.w3c.dom.Text

class GameFragment : Fragment() {

    lateinit var argsX: String
    var argsO: String = ""

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    private val gameManager = GameManager.getGameManager()
    private lateinit var one: ImageView
    private lateinit var two: ImageView
    private lateinit var three: ImageView
    private lateinit var four: ImageView
    private lateinit var five: ImageView
    private lateinit var six: ImageView
    private lateinit var seven: ImageView
    private lateinit var eight: ImageView
    private lateinit var nine: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        argsX = arguments?.getString("X").toString()
        argsO = arguments?.getString("O").toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        setPlayers(argsX, argsO)
        binding.tvTurn?.text = "Turn of: ${gameManager.currentPlayerName}"

        return binding.root
    }


    private fun setPlayers(xName: String?, oName: String?) {
        gameManager.setPlayers(xName, oName)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBoxes()
        setBoxClick()
        binding.btnStartNew?.setOnClickListener {
            resetBoxes()
            findNavController().navigate(R.id.action_gameFragment_to_playerRecords)
        }
    }

    private fun resetBoxes() {
        one.isEnabled = true
        two.isEnabled = true
        three.isEnabled = true
        four.isEnabled = true
        five.isEnabled = true
        six.isEnabled = true
        seven.isEnabled = true
        eight.isEnabled = true
        nine.isEnabled = true
        one.setImageResource(0)
        two.setImageResource(0)
        three.setImageResource(0)
        four.setImageResource(0)
        five.setImageResource(0)
        six.setImageResource(0)
        seven.setImageResource(0)
        eight.setImageResource(0)
        nine.setImageResource(0)
    }

    private fun setBoxes() {
        one = view?.findViewById(R.id.one)!!
        two = view?.findViewById(R.id.two)!!
        three = view?.findViewById(R.id.three)!!
        four = view?.findViewById(R.id.four)!!
        five = view?.findViewById(R.id.five)!!
        six = view?.findViewById(R.id.six)!!
        seven = view?.findViewById(R.id.seven)!!
        eight = view?.findViewById(R.id.eight)!!
        nine = view?.findViewById(R.id.nine)!!
    }

    private fun setBoxClick() {
        //first row
        one.setOnClickListener { onBoxClick(one, Position(0, 0)) }
        two.setOnClickListener { onBoxClick(two, Position(0, 1)) }
        three.setOnClickListener { onBoxClick(three, Position(0, 2)) }
        //second row
        four.setOnClickListener { onBoxClick(four, Position(1, 0)) }
        five.setOnClickListener { onBoxClick(five, Position(1, 1)) }
        six.setOnClickListener { onBoxClick(six, Position(1, 2)) }
        //third row
        seven.setOnClickListener { onBoxClick(seven, Position(2, 0)) }
        eight.setOnClickListener { onBoxClick(eight, Position(2, 1)) }
        nine.setOnClickListener { onBoxClick(nine, Position(2, 2)) }

    }

    private fun onBoxClick(box: ImageView, position: Position) {

        box.setImageResource(gameManager.currentPlayerMark)
        box.isEnabled = false
        val winningType = gameManager.boxClicked(position)
        binding.tvTurn?.text = "Turn of: ${gameManager.currentPlayerName}"

        if (winningType != null) {
            disableBoxes()
            drawLine(winningType)
            gameManager.updatePlayers()
            Log.d("IPP", "onBoxClick: ${gameManager.getPlayerO()}")
            binding.btnStartNew?.visibility = View.VISIBLE
        } else {
            if (gameManager.isWinnerNotExists()) {
                binding.btnStartNew?.visibility = View.VISIBLE
                gameManager.updatePlayers()
                Toast.makeText(requireContext(), "Tie", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun drawLine(winningLine: WinningType) {
        val (winningBoxes, background) = when (winningLine) {
            WinningType.ROW_1 -> Pair(listOf(one, two, three), R.drawable.horizontal_line)
            WinningType.ROW_2 -> Pair(listOf(four, five, six), R.drawable.horizontal_line)
            WinningType.ROW_3 -> Pair(listOf(seven, eight, nine), R.drawable.horizontal_line)
            WinningType.COLUMN_1 -> Pair(listOf(one, four, seven), R.drawable.vertical_line)
            WinningType.COLUMN_2 -> Pair(listOf(two, five, eight), R.drawable.vertical_line)
            WinningType.COLUMN_3 -> Pair(listOf(three, six, nine), R.drawable.vertical_line)
            WinningType.LEFT_CROSS -> Pair(listOf(one, five, nine), R.drawable.left_diagonal_line)
            WinningType.RIGHT_CROSS -> Pair(listOf(three, five, seven),
                R.drawable.right_diagonal_line)
        }

        winningBoxes.forEach { box ->
            box.background = ContextCompat.getDrawable(requireContext(), background)
        }
    }

    private fun disableBoxes() {
        one.isEnabled = false
        two.isEnabled = false
        three.isEnabled = false
        four.isEnabled = false
        five.isEnabled = false
        six.isEnabled = false
        seven.isEnabled = false
        eight.isEnabled = false
        nine.isEnabled = false
    }


}