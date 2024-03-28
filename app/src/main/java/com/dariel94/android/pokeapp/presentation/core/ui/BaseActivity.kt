package com.dariel94.android.pokeapp.presentation.core.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dariel94.android.pokeapp.databinding.PokeappActivityBaseBinding

/**
 * Created by dariel94 on 31/10/2021.
 */
abstract class BaseActivity : AppCompatActivity() {

    private lateinit var binding: PokeappActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = PokeappActivityBaseBinding.inflate(layoutInflater)
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