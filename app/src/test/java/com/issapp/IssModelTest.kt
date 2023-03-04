package com.issapp

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.issapp.model.IssInfo
import com.issapp.model.IssPosition
import org.junit.Before
import org.junit.Test

class IssModelTest {

    private lateinit var issInfo: IssInfo
    lateinit var gson: Gson

    @Before
    fun setUp() {
        gson = GsonBuilder()
            .setLenient()
            .create()
        val issPosition = IssPosition(0.0, 0.0)
        issInfo = IssInfo("", 1234567, issPosition)
    }

    @Test
    fun `test parser`() {
        val infoIss = gson.fromJson(DATA, IssInfo::class.java)
        assert(infoIss.message.equals("success"))
    }

    private companion object {
        const val DATA = "{\"iss_position\": {\"latitude\": \"-8.2008\", \"longitude\": \"-51.2624\"}, \"timestamp\": 1677806018, \"message\": \"success\"}"
    }


}