package com.example.numberpickersample

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_number_picker.*
import kotlinx.android.synthetic.main.number_picker_dialog.*


class NumberPickerActivity : AppCompatActivity() {
    lateinit var dialog: Dialog
    companion object {
        private val LOG_TAG: String? = NumberPickerActivity::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number_picker)

        pickNumbersBtn.setOnClickListener {
            showNumbersDialog()
        }
    }

    private fun showNumbersDialog() {
        Log.i(LOG_TAG, "Showing numbers dialog")
        dialog = Dialog(this).apply {
            setTitle(getString(R.string.number_picker_dialog_title))
            setContentView(R.layout.number_picker_dialog)

            numberPicker.apply {
                maxValue = 10
                minValue = 1
                wrapSelectorWheel = true
                setOnValueChangedListener { picker, oldValue, newValue ->
                    val toastText = "Number changed from $oldValue to $newValue"
                    Toast.makeText(this@NumberPickerActivity, toastText, Toast.LENGTH_SHORT).show()
                }
            }

            selectBtn.setOnClickListener {
                getTextView().text = getString(R.string.selected_value, numberPicker.value.toString())
            }

            cancelBtn.setOnClickListener {
                dialog.dismiss()
            }
        }

        dialog.show()

    }

    fun getTextView(): TextView {
        return this.findViewById(R.id.numberPickedTxt)
    }
}
