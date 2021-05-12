package com.example.weatherapp.ui.current

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentCurrentWeatherBinding
import com.example.weatherapp.uriImage
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso


class CurrentWeatherFragment : Fragment() {
    private lateinit var viewModel: CurrentWeatherViewModel
    @SuppressLint("StringFormatMatches")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val binding = FragmentCurrentWeatherBinding.inflate(inflater)
        val application = requireNotNull(activity)
        val viewModelProvider = CurrentWeatherViewModelFactory(application.application)
        viewModel =
            ViewModelProvider(this, viewModelProvider).get(CurrentWeatherViewModel::class.java)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
        viewModel.weather.observe(viewLifecycleOwner, Observer {
            binding.textViewCondition.text = getString(R.string.condition,it.description.capitalize())
            var x = String.format(uriImage, it.icon)
            Picasso.get()
                .load(x)
                .into(binding.imageViewConditionIcon)
        })

        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError(binding.root)
        })

        return binding.root
    }


    private fun onNetworkError(root: View) {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Snackbar.make(
                root,
                "Network error",
                Snackbar.LENGTH_LONG
            ).show()
            viewModel.onNetworkErrorShown()
        }
    }
}