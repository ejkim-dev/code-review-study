package com.devhoony.baseandroidapp.sample.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.devhoony.baseandroidapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavigationActivity : AppCompatActivity(), BottomNavController.NavGraphProvider {

    companion object {
        fun callingIntent(context: Context) = Intent(context, NavigationActivity::class.java)
    }


    private val navController by lazy(LazyThreadSafetyMode.NONE) {
        Navigation.findNavController(this, R.id.nav_host_fragment)
    }

    private val bottomNavController by lazy(LazyThreadSafetyMode.NONE) {
        BottomNavController(this, R.id.nav_host_fragment, R.id.home_graph)
    }

    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        bottomNavigationView = findViewById(R.id.bottomNavigation)

        bottomNavController.setNavGraphProvider(this)
        bottomNavigationView.setUpNavigation(bottomNavController)
        if (savedInstanceState == null) bottomNavController
            .onNavigationItemSelected()

        // do your things...
    }

    override fun getNavGraphId(itemId: Int) = when (itemId) {
        R.id.home_graph -> R.navigation.home_graph
        R.id.list_graph -> R.navigation.list_graph
        R.id.profile_graph -> R.navigation.profile_graph
        else -> R.navigation.home_graph
    }

    override fun onSupportNavigateUp(): Boolean = navController
        .navigateUp()

    override fun onBackPressed() = bottomNavController.onBackPressed()
}
