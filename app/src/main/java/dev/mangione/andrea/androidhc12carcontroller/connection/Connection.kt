package dev.mangione.andrea.androidhc12carcontroller.connection

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager

object Connection {
    var status: String = "Disconnected";
    var isConnected: Boolean = false;

    fun askForConnection(context: Context, callback: (port: String) -> Unit) {
        context.registerReceiver(
            object : BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {
                    val device: UsbDevice = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE)!!

                    status = "Connected"
                    isConnected = true;
                    callback(device.deviceName)
                }
            },
            IntentFilter(UsbManager.ACTION_USB_DEVICE_ATTACHED)
        )

        //Register on usb plugged out
        context.registerReceiver(
            object : BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {
                    status = "Disconnected"
                    isConnected = false;
                    callback("")
                }
            },
            IntentFilter(UsbManager.ACTION_USB_DEVICE_DETACHED)
        )

    }
}