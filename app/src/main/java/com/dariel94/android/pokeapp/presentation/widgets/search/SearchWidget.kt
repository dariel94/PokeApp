/*
 * Created by dariel94 on 23/7/24 2:51
 * Last modified 23/7/24 2:50
 */

package com.dariel94.android.pokeapp.presentation.widgets.search

import android.content.Context
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import com.dariel94.android.pokeapp.R

class SearchWidget @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    LinearLayout(context, attrs, defStyleAttr) {

    private val editText: EditText
    private val editTextContainer: ConstraintLayout
    private val clearButton: ImageView
    private var listener: SearchViewListener? = null

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.pokeapp_search_view, this)

        editText = findViewById(R.id.search_edit_text)
        editTextContainer = findViewById(R.id.search_edit_text_container)
        clearButton = findViewById(R.id.image_clear)
        //clearButton.contentDescription = context.getString(R.string.multiplayer_commons_accessibility_clear_text)

        init()
    }

    private fun init() {
        ViewCompat.setNestedScrollingEnabled(this, false)

        //editText.isFocusableInTouchMode = false
        //editText.isFocusable = false

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            editText.importantForAutofill = IMPORTANT_FOR_AUTOFILL_NO
        }

        editText.onFocusChangeListener = OnFocusChangeListener { _: View?, hasFocus: Boolean ->
            if (hasFocus) {
                //editTextContainer.setBackgroundResource(R.drawable.multiplayer_commons_search_view_rounded_background_selected)
            } else {
                //editTextContainer.setBackgroundResource(R.drawable.multiplayer_commons_search_view_rounded_background)
            }
        }

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                // Do nothing
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                val text = editText.text.toString()
                if (text.isEmpty()) {
                    clearButton.visibility = GONE
                } else {
                    clearButton.visibility = VISIBLE
                }
                listener?.onSearchText(text)
            }

            override fun afterTextChanged(editable: Editable) {
                // Do nothing
            }
        })
        clearButton.setOnClickListener { editText.setText("") }
    }

    val text: String
        get() = editText.text.toString()

    fun show() {
        editText.isFocusableInTouchMode = true
        editText.isFocusable = true
        visibility = VISIBLE
    }

    fun setHint(text: String) {
        editText.hint = text
    }

    fun setListener(listener: SearchViewListener) {
        this.listener = listener
    }
}
