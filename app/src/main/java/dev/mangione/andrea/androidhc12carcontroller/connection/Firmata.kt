package dev.mangione.andrea.androidhc12carcontroller.connection

import org.firmata4j.firmata.FirmataDevice

class Firmata {
    val device = FirmataDevice(Connection.device!!.deviceName);

    init {
        device.start();
        device.ensureInitializationIsDone()
    }

    companion object {
        private var inst: Firmata? = null;

        fun getInstance(): Firmata {
            if (inst == null) inst = Firmata()
            return inst as Firmata;
        }
    }
}