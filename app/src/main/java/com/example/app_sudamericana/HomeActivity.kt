package com.example.app_sudamericana

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.app_sudamericana.fragments.HomeFragment
import com.example.app_sudamericana.fragments.PerfilFragment
import com.example.app_sudamericana.fragments.ReservaFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val perfilFragment = PerfilFragment()
    private val reservaFragment = ReservaFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        RepetirFragment(homeFragment)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.navigation_home -> RepetirFragment(homeFragment)
                R.id.navigation_reserva -> RepetirFragment(reservaFragment)
                R.id.navigation_Perfil -> RepetirFragment(perfilFragment)
            }
            true
        }

    }


//===================================================================================================
    private fun  RepetirFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
        replace(R.id.fl_wrapper, fragment)
        commit()
    }


    }
