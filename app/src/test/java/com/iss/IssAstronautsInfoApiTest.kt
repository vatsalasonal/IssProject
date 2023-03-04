package com.iss

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.iss.data.remote.Network
import com.iss.data.remote.RemoteRepository
import com.iss.model.IssInfo
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