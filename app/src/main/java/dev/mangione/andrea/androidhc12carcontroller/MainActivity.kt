package dev.mangione.andrea.androidhc12carcontroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import dev.mangione.andrea.androidhc12carcontroller.ui.main.DriveModal
import dev.mangione.andrea.androidhc12carcontroller.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, MainFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }
}