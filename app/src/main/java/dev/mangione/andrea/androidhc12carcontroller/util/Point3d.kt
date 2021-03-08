package dev.mangione.andrea.androidhc12carcontroller.util

class Point3d(var x: Int, var y: Int, var z: Int) {
    fun set(x: Int, y: Int, z: Int) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    override fun toString(): String {
        return "X: $x | Y:$y | Z:$z";
    }
}