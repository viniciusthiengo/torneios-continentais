package thiengo.com.br.torneioscontinentais.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log
import thiengo.com.br.torneioscontinentais.fragment.AfcFragment
import thiengo.com.br.torneioscontinentais.fragment.LibertadoresFragment
import thiengo.com.br.torneioscontinentais.fragment.UefaFragment


class ViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    // Returns total number of pages
    override fun getCount(): Int {
        return 3
    }

    // Returns the fragment to display for that page
    override fun getItem(position: Int): Fragment? {
        when (position) {
            1 -> return LibertadoresFragment()
            2 -> return AfcFragment()
            else -> return UefaFragment()
        }
    }

    // Returns the page title for the top indicator
    override fun getPageTitle(position: Int): CharSequence {
        when (position) {
            1 -> return "Taça Libertadores"
            2 -> return "Liga dos Campeões da AFC"
            else -> return "UEFA Champions League"
        }
    }
}