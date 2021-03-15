package dev.mangione.andrea.androidhc12carcontroller.ui.main

import UsbSerialConnection
import android.content.Context
import android.hardware.usb.UsbManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import board.Pin
import dev.mangione.andrea.androidhc12carcontroller.R
import dev.mangione.andrea.androidhc12carcontroller.connection.ConnectionProvider
import dev.mangione.andrea.androidhc12carcontroller.util.Gyroscope
import dev.mangione.andrea.androidhc12carcontroller.util.Point3d
import firmata.Firmata
import firmata.Firmata.Companion.Led


class DriveModal : Fragment() {
    private var example: TextView? = null;
    private var gyroscope: Gyroscope? = null;
    private var firmata: Firmata? = null;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_drive_modal, container, false)
        gyroscope = Gyroscope(requireContext(), ::update);
        this.example = root.findViewById(R.id.example);

        val manager = context!!.getSystemService(Context.USB_SERVICE) as UsbManager
        val connection = UsbSerialConnection(ConnectionProvider.device!!, manager);
        connection.connect()
        firmata = Firmata(connection);
        val led = firmata!!.Led(13);
        (root.findViewById(R.id.button) as Button).setOnClickListener {
            led.turnOn()
        }
        return root;
    }

    private fun update(point: Point3d) {
        example?.text = point.toString()
    }

}