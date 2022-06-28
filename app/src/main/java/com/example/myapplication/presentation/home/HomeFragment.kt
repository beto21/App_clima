package com.example.myapplication.presentation.home

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.domain.model.Daily
import com.example.myapplication.presentation.base.BaseFragment
import com.example.myapplication.presentation.commons.SpaceItemDecorationVertical
import com.example.myapplication.presentation.commons.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>
    (FragmentHomeBinding::inflate) {
    override val vm: HomeViewModel by viewModels()
    override val TAG: String = HomeFragment::class.java.name



    override fun setupUI() {
        super.setupUI()
        binding.vm = vm
        initRecyclerview()
    }


    private fun initRecyclerview() {
        binding.recycler.apply {
            setHasFixedSize(true)
            setItemViewCacheSize(20)
            addItemDecoration(SpaceItemDecorationVertical(32))
        }
    }



    override fun setupVM() {
        super.setupVM()
        vm.getClimate()
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    if (isActive) {
                        updateUiAdpater()
                    }
                }

            }
        }

    }



    @Suppress("UNCHECKED_CAST")
    private suspend fun updateUiAdpater() {
        vm.moviesFlow.collect {
            if (it is UIState.Success<*>) {
                val dailys = (it.data as List<Daily>)
                vm.climateAdapter.set(dailys)
                vm.climateAdapter.onItemClicked = { daily: Daily ->
                   val id = daily.weather[0].id
                    val action =
                        HomeFragmentDirections.actionHomeFragmentToClimateDetailFragment(id)
                    findNavController().navigate(action)

                }
            } else if (it is UIState.Error) {
                it.message?.let {
                    Toast.makeText(context, it, Toast.LENGTH_LONG).show()

                }
            }
        }
    }


}