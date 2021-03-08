package dev.mangione.andrea.androidhc12carcontroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.mangione.andrea.androidhc12carcontroller.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}