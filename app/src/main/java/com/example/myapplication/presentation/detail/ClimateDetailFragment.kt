package com.example.myapplication.presentation.detail

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.myapplication.databinding.FragmentDetailClimateBinding
import com.example.myapplication.domain.Weather
import com.example.myapplication.domain.model.Daily
import com.example.myapplication.presentation.base.BaseFragment
import com.example.myapplication.presentation.commons.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ClimateDetailFragment : BaseFragment<ClimateDetailViewModel, FragmentDetailClimateBinding>(FragmentDetailClimateBinding::inflate) {

    override val vm: ClimateDetailViewModel by viewModels()

    private val args by navArgs<ClimateDetailFragmentArgs>()
    override val TAG: String = ClimateDetailFragment::class.java.name

    override fun setupUI() {
        super.setupUI()
    }

    override fun setupVM() {
        super.setupVM()
        vm.getClimateforId(args.id)
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.climateFlow.collect {
                    uiState ->
                        if (uiState is UIState.Success<*>) {
                            binding.weather = uiState.data as Weather
                        } else if (uiState is UIState.Error) {
                            Toast.makeText(requireContext(), uiState.message, Toast.LENGTH_LONG)
                                .show()

                        }

                    }
                }


            }
        }

    }

