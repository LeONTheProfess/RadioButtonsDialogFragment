import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

/**
 * Created by OLM on 15.09.2023.
 * Dialog with radio buttons
 */

class RadioButtonsDialogFragment(private val title : String,
    private val radioValues : Array<String>
) : DialogFragment() {

    private var choiceItem = 0
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(title)
                .setSingleChoiceItems(radioValues, 0
                ) { dialog, item ->
                    Toast.makeText(activity, "Выбрано:  ${radioValues[item]}",
                        Toast.LENGTH_SHORT).show()
                    choiceItem = item
                }
                .setPositiveButton("OK"
                ) { dialog, id ->
                    onClick?.onOkClickListener(radioValues[choiceItem]);
                }
                .setNegativeButton("Отмена") {
                        dialog, id ->
                    onClick?.onCancelClickListener()
                }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    //#OLM Interface to set when creating a dialog
    interface OnButtonClick {
        fun onOkClickListener(radioValue: String)
        fun onCancelClickListener()
    }

    var onClick: OnButtonClick? = null

    override fun onDetach() {
        super.onDetach()
        onClick = null
    }

}