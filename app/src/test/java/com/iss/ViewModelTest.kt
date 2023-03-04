package com.iss

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.iss.model.IssAstronautsInfo
import com.iss.model.IssInfo
import com.iss.model.IssPosition
import com.iss.ui.IssViewModel
import io.reactivex.rxjava3.core.Observer
import org.apache.maven.artifact.ant.RemoteRepository
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock

class ViewModelTest {

    @Mock
    private lateinit var context: Application

    private lateinit var viewModel: IssViewModel
    private lateinit var repository: RemoteRepository

    private lateinit var isViewLoadingObserver: Observer<Boolean>
    private lateinit var onMessageErrorObserver: Observer<Any>
    private lateinit var emptyListObserver: Observer<Boolean>
    private lateinit var onRenderMuseumsObserver: Observer<IssInfo>

    private lateinit var issAstronautsInfo: IssAstronautsInfo

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        repository = RemoteRepository()
        viewModel = IssViewModel(context)

        mockData()
        setupObservers()
    }

    private fun setupObservers() {
        isViewLoadingObserver = mock(Observer::class.java) as Observer<Boolean>
        onMessageErrorObserver = mock(Observer::class.java) as Observer<Any>
        emptyListObserver = mock(Observer::class.java) as Observer<Boolean>
        onRenderMuseumsObserver = mock(Observer::class.java) as Observer<IssInfo>
    }

    private fun mockData() {
        val mockList: MutableList<IssInfo> = mutableListOf()
        var issPosition = IssPosition(0.0, 0.0);
        mockList.add(
           IssInfo(
                "",
                12345678,
                issPosition
            )
        )
        mockList.add(IssInfo("success", 11, issPosition))
        mockList.add(IssInfo("hello", 22, issPosition))

        mockList
    }


}