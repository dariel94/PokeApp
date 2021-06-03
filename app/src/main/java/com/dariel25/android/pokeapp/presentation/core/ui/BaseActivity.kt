package com.dariel25.android.pokeapp.presentation.core.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dariel25.android.pokeapp.databinding.ActivityBaseBinding

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.baseLayoutView.addView(getLayoutView())

        binding.baseErrorView.retryButton.setOnClickListener { onRetry() }
    }

    protected abstract fun getLayoutView(): View

    protected abstract fun onRetry()

    protected fun showLayoutView() {
        binding.baseErrorView.root.visibility = View.GONE
        binding.baseLoadingView.visibility = View.GONE
        binding.baseLayoutView.visibility = View.VISIBLE
    }

    protected fun showErrorView(error: String) {
        binding.baseLoadingView.visibility = View.GONE
        binding.baseLayoutView.visibility = View.GONE
        binding.baseErrorView.errorText.text = error
        binding.baseErrorView.root.visibility = View.VISIBLE
    }

    protected fun showLoadingView() {
        binding.baseErrorView.root.visibility = View.GONE
        binding.baseLayoutView.visibility = View.GONE
        binding.baseLoadingView.visibility = View.VISIBLE
    }
}