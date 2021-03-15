package dev.mangione.andrea.androidhc12carcontroller.connection

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import java.lang.Exception
import java.util.logging.Logger

object ConnectionProvider {
    var status: String = "Disconnected";
    var isConnected: Boolean = false;
    var device: UsbDevice? = null;
    private val ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION"

    fun askForConnection(context: Context, callback: (port: String) -> Unit) {
        context.registerReceiver(
            object : BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {
                    device = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE)!!

                    status = "Connected"
                    isConnected = true;
                    callback(device!!.deviceName)
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

    fun askForPermission(context: Context, callback: (granted: Boolean) -> Unit) {
        val manager = context.getSystemService(Context.USB_SERVICE) as UsbManager
        val permissionIntent =
            PendingIntent.getBroadcast(context, 0, Intent(ACTION_USB_PERMISSION), 0)
        manager.requestPermission(device!!, permissionIntent)

        context.registerReceiver(
            object : BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {
                    if (ACTION_USB_PERMISSION == intent.action) {
                        if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                            callback(true);
                        } else {
                            callback(false);
                        }
                    }
                }
            },
            IntentFilter(ACTION_USB_PERMISSION)
        )
    }
}