package dev.mangione.andrea.androidhc12carcontroller.util

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlin.math.floor

class Gyroscope(private val context: Context, private val cb: (Point3d) -> Unit) :
    SensorEventListener {
    private val point = Point3d(0, 0, 0);

    init {
        var sensorManager =
            context.applicationContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val gyroscopeSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
        sensorManager.registerListener(this, gyroscopeSensor, 1000000)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event!!.sensor.type == Sensor.TYPE_ROTATION_VECTOR) {
            val x = floor(event.values[0] * 180).toInt()
            val y = floor(event.values[1] * 180).toInt()
            val z = floor(event.values[2] * 180).toInt()
            if (point.x != x || point.y != y || point.z != z) {
                point.set(x, y, z)
                cb(point)
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}
}