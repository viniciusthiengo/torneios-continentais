package thiengo.com.br.torneioscontinentais.atividade

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import thiengo.com.br.torneioscontinentais.R
import thiengo.com.br.torneioscontinentais.adapter.ViewPagerAdapter


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        viewPager.adapter = ViewPagerAdapter(supportFragmentManager)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    /*
     * Hackcode para apresentar o título correto do fragmento
     * atual sem necessidade.
     * */
    override fun onResume() {
        super.onResume()
        toolbar.title = viewPager.adapter.getPageTitle( viewPager.currentItem )
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val position = when(item.itemId){
            R.id.nav_libertadores -> 1
            R.id.nav_afc -> 2
            else -> 0
        }

        toolbar.title = viewPager.adapter.getPageTitle(position)
        viewPager.setCurrentItem(position)

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
