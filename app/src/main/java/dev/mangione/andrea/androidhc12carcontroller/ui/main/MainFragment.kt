package dev.mangione.andrea.androidhc12carcontroller.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import dev.mangione.andrea.androidhc12carcontroller.R
import dev.mangione.andrea.androidhc12carcontroller.connection.Connection


class MainFragment : Fragment() {
    private var status: TextView? = null;
    private var permission: TextView? = null;
    private var start: Button? = null;
    private var usbHub: String? = "";

    enum class Mark(value: Boolean, utf: String) {
        CHECKMARK(true, "✓"), CROSS(false, "✗");

        val value: Boolean = value;
        val utf: String = utf;

        companion object {
            fun fromBoolean(value: Boolean): Mark {
                return if (value) CHECKMARK else CROSS;
            }
        }
    }


    private fun onPermissionGranted(granted: Boolean) {
        permission?.text = Mark.fromBoolean(granted).utf
        start?.isEnabled = granted;
    }

    private fun onUsbConnected(t: String) {
        usbHub = t;
        status?.text = Mark.fromBoolean(t.isNotBlank()).utf;
        if (t.isNotBlank()) Connection.askForPermission(context!!, ::onPermissionGranted)
    }

    private fun checkAvailability() {
        Connection.askForConnection(
            context!!,
            ::onUsbConnected
        );
    }

    private fun startSession() {
        val manager: FragmentManager? = fragmentManager
        val transaction: FragmentTransaction = manager!!.beginTransaction()
        transaction.replace(R.id.container, DriveModal())
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.main_fragment, container, false)
        this.status = root.findViewById(R.id.status);
        this.permission = root.findViewById(R.id.permission);
        this.start = root.findViewById(R.id.start);
        this.start?.setOnClickListener { startSession() }
        checkAvailability();

        start?.isEnabled = true;
        return root;
    }

}