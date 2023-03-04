package com.issapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.issapp.data.remote.Network
import com.issapp.data.remote.RemoteRepository
import com.issapp.model.IssInfo
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import java.util.*

@HiltAndroidTest
@RunWith(JUnit4::class)
class IssAstronautsInfoApiTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var issInfo: IssInfo

    @Mock
    var network: Network? = null

    @Mock
    lateinit var remoteRepository: RemoteRepository

    @Test
    fun testIssPositionInfoApi() = runBlocking {
        var response = network?.fetchIssPositionInfo()
        var reponseTest = response?.test()
        assertNull(reponseTest)
    }

    @Test
    fun testIssAstronautsListApi() {
        var response = network?.fetchIssAstronautsList()
        var responseTest = response?.test()
        assertNull(responseTest)
    }
}