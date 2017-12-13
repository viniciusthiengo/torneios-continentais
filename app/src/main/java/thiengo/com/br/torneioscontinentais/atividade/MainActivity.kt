package thiengo.com.br.torneioscontinentais.atividade

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import thiengo.com.br.torneioscontinentais.R
import thiengo.com.br.torneioscontinentais.fragment.AfcFragment
import thiengo.com.br.torneioscontinentais.fragment.FragmentAbstract
import thiengo.com.br.torneioscontinentais.fragment.LibertadoresFragment
import thiengo.com.br.torneioscontinentais.fragment.UefaFragment


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var titulo: String = UefaFragment.TITULO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initDrawerNavigation()

        titulo = savedInstanceState?.getString(FragmentAbstract.TITULO) ?: titulo

        /*
         * Caso seja a primeira vez que o onCreate() esteja sendo
         * invocado.
         * */
        if( supportFragmentManager.findFragmentByTag(FragmentAbstract.KEY) == null ) {
            trocaFragmento(UefaFragment())
        }
    }

    private fun initDrawerNavigation(){
        val toggle = ActionBarDrawerToggle(
                this,
                drawer_layout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragmento: Fragment

        when(item.itemId) {
            R.id.nav_libertadores -> {
                fragmento = LibertadoresFragment()
                titulo = LibertadoresFragment.TITULO
            }
            R.id.nav_afc -> {
                fragmento = AfcFragment()
                titulo = AfcFragment.TITULO
            }
            else -> {
                fragmento = UefaFragment()
                titulo = UefaFragment.TITULO
            }
        }

        toolbar.title = titulo
        trocaFragmento( fragmento )

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun trocaFragmento( fragment: Fragment ){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.rl_container, fragment, FragmentAbstract.KEY)
            .commit()
    }

    /*
     * Hackcode para apresentar o título correto do fragmento
     * atual.
     * */
    override fun onResume() {
        super.onResume()
        toolbar.title = titulo
    }

    /*
     * Para que seja possível sempre apresentar o título correto
     * em tela.
     * */
    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString( FragmentAbstract.TITULO, titulo )
        super.onSaveInstanceState( outState )
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        else {
            super.onBackPressed()
        }
    }
}
