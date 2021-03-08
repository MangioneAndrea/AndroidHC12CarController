package dev.mangione.andrea.androidhc12carcontroller.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dev.mangione.andrea.androidhc12carcontroller.R
import dev.mangione.andrea.androidhc12carcontroller.connection.Connection
import dev.mangione.andrea.androidhc12carcontroller.connection.Firmata
import dev.mangione.andrea.androidhc12carcontroller.util.Gyroscope
import dev.mangione.andrea.androidhc12carcontroller.util.Point3d
import java.util.*


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
        firmata = Firmata.getInstance();
        return root;
    }

    private fun update(point: Point3d) {
        example?.text = point.toString()
    }

}