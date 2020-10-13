package de.brainlezz.poe.helpers

import de.brainlezz.poe.services.PoeService

class RetrofitUtils {
    companion object{
        fun createSessionCookie(sessionId: String) : String = "POESESSID=$sessionId"
    }
}