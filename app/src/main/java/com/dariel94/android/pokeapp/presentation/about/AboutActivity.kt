/*
 * Created by dariel94 on 22/7/24 6:03
 * Last modified 22/7/24 6:03
 */

package com.dariel94.android.pokeapp.presentation.about

import android.R.id.message
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.dariel94.android.pokeapp.BuildConfig
import com.dariel94.android.pokeapp.R
import com.dariel94.android.pokeapp.databinding.PokeappActivityAboutBinding
import com.dariel94.android.pokeapp.presentation.core.ui.BaseActivity


class AboutActivity : BaseActivity() {

    private val binding: PokeappActivityAboutBinding by lazy {
        PokeappActivityAboutBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = ""
        binding.appVersion.text = "v" + BuildConfig.VERSION_NAME

        binding.contactButton.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_SENDTO, Uri.fromParts(
                    MAILTO, CONTACT_EMAIL, null
                )
            )
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.pokeapp_app_name))
            intent.putExtra(Intent.EXTRA_TEXT, message)
            startActivity(Intent.createChooser(intent, getString(R.string.pokeapp_contact_message)))
        }
        binding.githubButton.setOnClickListener { goToUrl(GITHUB_URL) }
        binding.pokeapiButton.setOnClickListener { goToUrl(POKEAPI_URL) }

        showLayoutView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun goToUrl(url: String) {
        val uriUrl = Uri.parse(url)
        val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
        startActivity(launchBrowser)
    }
    override fun getLayoutView() : View = binding.root

    override fun onRetry() {

    }

    companion object {
        private const val GITHUB_URL = "https://github.com/dariel94/PokeApp"
        private const val POKEAPI_URL = "https://pokeapi.co/"
        private const val MAILTO = "mailto"
        private const val CONTACT_EMAIL = "davarieldiaz@gmail.com"
    }
}
