package grechushnikova.natali

import android.graphics.Color
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val showDialogButton: Button = findViewById(R.id.opendialog)
        showDialogButton.setOnClickListener {
            showColorDialog()
        }
    }

    private fun showColorDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_layout, null)
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setView(dialogView)

        val alertDialog = alertDialogBuilder.create()

        val radioGroup: RadioGroup = dialogView.findViewById(R.id.radioGroup)
        val rangeRadioButton: RadioButton = dialogView.findViewById(R.id.rangeRadioButton)
        val sliderRadioButton: RadioButton = dialogView.findViewById(R.id.sliderRadioButton)
        val inputLayout: LinearLayout = dialogView.findViewById(R.id.inputLayout)
        val redEditText: EditText = dialogView.findViewById(R.id.redEditText)
        val greenEditText: EditText = dialogView.findViewById(R.id.greenEditText)
        val blueEditText: EditText = dialogView.findViewById(R.id.blueEditText)
        val redSeekBar: SeekBar = dialogView.findViewById(R.id.redSeekBar)
        val greenSeekBar: SeekBar = dialogView.findViewById(R.id.greenSeekBar)
        val blueSeekBar: SeekBar = dialogView.findViewById(R.id.blueSeekBar)
        val getColorButton: Button = dialogView.findViewById(R.id.getColorButton)

        rangeRadioButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                inputLayout.visibility = LinearLayout.VISIBLE
                redSeekBar.visibility = SeekBar.GONE
                greenSeekBar.visibility = SeekBar.GONE
                blueSeekBar.visibility = SeekBar.GONE
            }
        }

        sliderRadioButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                inputLayout.visibility = LinearLayout.GONE
                redSeekBar.visibility = SeekBar.VISIBLE
                greenSeekBar.visibility = SeekBar.VISIBLE
                blueSeekBar.visibility = SeekBar.VISIBLE
            }
        }

        getColorButton.setOnClickListener {
            val red: Int
            val green: Int
            val blue: Int

            if (rangeRadioButton.isChecked) {
                if (redEditText.text.isNullOrEmpty() || greenEditText.text.isNullOrEmpty() || blueEditText.text.isNullOrEmpty()) {
                    Toast.makeText(this, "Введите число от 0 до 255", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                red = redEditText.text.toString().toIntOrNull() ?: 0
                green = greenEditText.text.toString().toIntOrNull() ?: 0
                blue = blueEditText.text.toString().toIntOrNull() ?: 0

                if (red !in 0..255 || green !in 0..255 || blue !in 0..255) {
                    Toast.makeText(this, "Введите число от 0 до 255", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            } else {
                red = redSeekBar.progress
                green = greenSeekBar.progress
                blue = blueSeekBar.progress
            }


            val colorView = TextView(this)
            colorView.text = "Ваш цвет"
            colorView.setBackgroundColor(Color.rgb(red, green, blue))
            colorView.height = 200

            val alertDialogBuilder = AlertDialog.Builder(this)
                .setView(colorView)
                .setPositiveButton("Закрыть") { dialog, _ ->
                    dialog.dismiss()
                }

            alertDialogBuilder.create().show()
        }

        alertDialog.show()
    }
}