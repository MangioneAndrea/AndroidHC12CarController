package dev.mangione.andrea.androidhc12carcontroller.ui.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import dev.mangione.andrea.androidhc12carcontroller.R
import dev.mangione.andrea.androidhc12carcontroller.connection.Connection

class MainFragment : Fragment() {
    private var status: TextView? = null;
    private var permission: TextView? = null;
    private var farend: TextView? = null;
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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.main_fragment, container, false)
        this.status = root.findViewById(R.id.status);
        this.permission = root.findViewById(R.id.permission);
        this.farend = root.findViewById(R.id.farend);
        this.start = root.findViewById(R.id.start);
        checkAvailability();
        return root;
    }

    fun checkAvailability(): Boolean {
        val manager = this.activity!!.getSystemService(Context.USB_SERVICE);

        Connection.askForConnection(
            context!!,
        ) { t -> run { usbHub = t; status?.text = Mark.fromBoolean(t.isNotBlank()).utf } };

        
        permission?.text = Mark.fromBoolean(true).utf
        farend?.text = Mark.fromBoolean(true).utf
        return false
    }

}