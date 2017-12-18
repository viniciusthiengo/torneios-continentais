package thiengo.com.br.torneioscontinentais.atividade

import android.app.FragmentManager
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import thiengo.com.br.torneioscontinentais.R
import thiengo.com.br.torneioscontinentais.fragment.AfcFragment
import thiengo.com.br.torneioscontinentais.fragment.FragmentAbstrato
import thiengo.com.br.torneioscontinentais.fragment.LibertadoresFragment
import thiengo.com.br.torneioscontinentais.fragment.UefaFragment


class MainActivity : AppCompatActivity(),
        BottomNavigationView.OnNavigationItemSelectedListener,
        BottomNavigationView.OnNavigationItemReselectedListener {

    var titulo: String = UefaFragment.TITULO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        bottom_nav_view.setOnNavigationItemSelectedListener(this)
        bottom_nav_view.setOnNavigationItemReselectedListener(this)

        titulo = savedInstanceState?.getString(FragmentAbstrato.TITULO) ?: titulo

        /*
         * Caso seja a primeira vez que o onCreate() esteja sendo
         * invocado.
         * */
        if( supportFragmentManager.findFragmentByTag(FragmentAbstrato.KEY) == null ) {
            trocaFragmento(UefaFragment())
        }
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

        return true
    }

    override fun onNavigationItemReselected(item: MenuItem) {
        Log.i("LOG", "onNavigationItemReselected()");
    }

    private fun trocaFragmento( fragment: Fragment ){

        /*
         * Remove as pilhas de fragmentos para que não haja a
         * possibilidade de navegação entre os itens do
         * BottomNavigation, respeitando assim uma das regras
         * de negócio deste componente.
         * */
        supportFragmentManager
            .popBackStack( null, FragmentManager.POP_BACK_STACK_INCLUSIVE )

        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations( android.R.anim.fade_in, android.R.anim.fade_out )
            .replace(R.id.rl_container, fragment, FragmentAbstrato.KEY)
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
        outState?.putString( FragmentAbstrato.TITULO, titulo )
        super.onSaveInstanceState( outState )
    }


    /*
     * Com esse método será possível apresentar a Toolbar da
     * MainActivity que o usuário estiver na lista de jogos de
     * qualquer campeonato e esconder quando o usuário estiver na
     * tela de detalhes, tela que tem o próprio Toolbar.
     * */
    fun apresentarToolbar( status: Boolean ){
        toolbar.visibility =
            if( status )
                View.VISIBLE
            else
                View.GONE
    }
}