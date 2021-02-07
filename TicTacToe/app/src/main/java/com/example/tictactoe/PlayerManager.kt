package com.example.tictactoe

class PlayerManager {

    val playersList = arrayListOf<Player>()
    private var hashList: LinkedHashSet<Player> = linkedSetOf<Player>()

    private var id = 0
        get() {
            field++

            return field
        }

    companion object {
        @Volatile
        private var INSTANCE: PlayerManager? = null

        fun getPlayerManager(): PlayerManager {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = PlayerManager()
                INSTANCE = instance
                return instance
            }
        }
    }

    fun addPlayer(name: String) {
        val p = Player(id, name, 0, 0, 0)
        hashList.add(p)
    }



    fun getPlayers(): ArrayList<Player> {
        return ArrayList(hashList)
    }


    fun updatePlayer(player: Player?) {
        hashList?.find {
            it.id == player?.id
        }.apply {
            this?.ties = player?.ties
            this?.wins = player?.wins
            this?.loses = player?.loses
        }
    }

    fun getPlayer(name: String?): Player? {

        return hashList.find {
            it.name == name
        }
    }


}