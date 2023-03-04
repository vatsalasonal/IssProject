package com.iss

import android.widget.TextView
import androidx.core.view.size
import androidx.recyclerview.widget.RecyclerView
import com.iss.ui.MapActivity
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants=BuildConfig::class, packageName = "com.iss")
class RobolelectricTest {

    var mainActivity: MapActivity = null!!

    @Before
    fun init(){
        mainActivity = Robolectric.setupActivity(MapActivity::class.java)
    }

    @Test
    fun checkTextViewString_presentOrNot(){
        val textViewDistance= mainActivity.findViewById<TextView>(R.id.textViewDistance)
        assertNotNull(textViewDistance)
        val stringValue = textViewDistance.text.toString()
        assert(stringValue.equals("Hello World!", true))
    }

    @Test
    fun checkRecyclerView() {
        val recyclerView = mainActivity.findViewById<RecyclerView>(R.id.recyclerView)
        assertNotNull(recyclerView)
        assert(recyclerView.size > 0)
    }
}