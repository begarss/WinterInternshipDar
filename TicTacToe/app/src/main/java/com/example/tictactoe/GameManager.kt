package com.example.tictactoe

class GameManager {

    private val playerManager = PlayerManager.getPlayerManager()
    private var currentPlayer = 1
    private var boxClickCounter = 0
    private var isNoWinner = false
    val currentPlayerMark: Int
        get() {
            return if (currentPlayer == 1) R.drawable.ic_x else R.drawable.ic_sharp_blur_circular_24
        }
    val currentPlayerName: String?
        get() {
            return if (currentPlayer == 1) playerX?.name else playerO?.name
        }
    private var playerX: Player? = null
    private var playerO: Player? = null

    companion object {
        @Volatile
        private var INSTANCE: GameManager? = null

        fun getGameManager(): GameManager {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = GameManager()
                INSTANCE = instance
                return instance
            }
        }
    }


    fun addPlayer(name: String) {
        playerManager.addPlayer(name)
    }

    fun getPlayers():ArrayList<Player>{
        return playerManager.getPlayers()
    }

    fun setPlayers(xName: String?, oName: String?) {
        playerX = playerManager.getPlayer(xName)
        playerO = playerManager.getPlayer(oName)
    }

    fun getPlayersX(): Player? {
        return playerX
    }

    fun getPlayerO(): Player? {
        return playerO
    }

    private var gameState = arrayOf(
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0)
    )

    fun boxClicked(position: Position): WinningType? {
        boxClickCounter++
        gameState[position.row][position.column] = currentPlayer
        var winningType = checkGameOver()
        if (winningType != null) {
            when (currentPlayer) {
                1 -> {
                    playerX?.wins = playerX?.wins?.plus(1)
                    playerO?.loses = playerO?.loses?.plus(1)
                }
                2 -> {
                    playerO?.wins = playerO?.wins?.plus(1)
                    playerX?.loses = playerX?.loses?.plus(1)
                }
            }
        } else {
            if (checkTie()) {
                isNoWinner = true
                playerO?.ties = playerO?.ties?.plus(1)
                playerX?.ties = playerX?.ties?.plus(1)
            } else {
                currentPlayer = 3 - currentPlayer
            }
        }
        return winningType

    }

    private fun checkTie(): Boolean {
        return boxClickCounter == 9
    }

    fun isWinnerNotExists(): Boolean {
        return isNoWinner == true
    }

    fun updatePlayers(){
        playerManager.updatePlayer(playerX)
        playerManager.updatePlayer(playerO)
    }

    private fun checkGameOver(): WinningType? = when {
        valueChecker(gameState[0][0],
            gameState[0][1],
            gameState[0][2],
            currentPlayer) -> WinningType.ROW_1
        valueChecker(gameState[1][0],
            gameState[1][1],
            gameState[1][2],
            currentPlayer) -> WinningType.ROW_2
        valueChecker(gameState[2][0],
            gameState[2][1],
            gameState[2][2],
            currentPlayer) -> WinningType.ROW_3
        valueChecker(gameState[0][0],
            gameState[1][0],
            gameState[2][0],
            currentPlayer) -> WinningType.COLUMN_1
        valueChecker(gameState[0][1],
            gameState[1][1],
            gameState[2][1],
            currentPlayer) -> WinningType.COLUMN_2
        valueChecker(gameState[0][2],
            gameState[1][2],
            gameState[2][2],
            currentPlayer) -> WinningType.COLUMN_3
        valueChecker(gameState[0][0],
            gameState[1][1],
            gameState[2][2],
            currentPlayer) -> WinningType.LEFT_CROSS
        valueChecker(gameState[0][2],
            gameState[1][1],
            gameState[2][0],
            currentPlayer) -> WinningType.RIGHT_CROSS
        else -> null
    }

    private fun valueChecker(first: Int, second: Int, third: Int, current: Int): Boolean {
        return first == second && first == current && first == third && first == current
    }

    fun reset() {
        gameState = arrayOf(
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0)
        )
        boxClickCounter=0
        currentPlayer = 1
        isNoWinner=false
    }

}