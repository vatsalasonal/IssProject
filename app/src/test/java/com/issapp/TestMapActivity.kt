package com.issapp

import android.widget.TextView
//import com.google.ar.core.Config
import com.issapp.ui.MapActivity
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner


//@Config(application = TestApplication::class)
@RunWith(RobolectricTestRunner::class)
class TestMapActivity {

    private var mainActivity: MapActivity? = null
    lateinit var textViewDistance: TextView

    @Before
    fun setUp() {
        mainActivity = Robolectric.buildActivity(MapActivity::class.java)
            .create().visible().get()
        textViewDistance = mainActivity!!.findViewById(R.id.textViewDistance) as TextView
    }

    @Test
    fun addition_isCorrect() {
        val text = textViewDistance.text.toString()
        assertNull(text)
        assertEquals("text should be foo", "foo", text)
    }
}