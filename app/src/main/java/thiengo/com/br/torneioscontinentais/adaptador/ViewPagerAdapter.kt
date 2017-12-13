package thiengo.com.br.torneioscontinentais.adaptador

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import thiengo.com.br.torneioscontinentais.fragment.AfcFragment
import thiengo.com.br.torneioscontinentais.fragment.FragmentAbstract
import thiengo.com.br.torneioscontinentais.fragment.LibertadoresFragment
import thiengo.com.br.torneioscontinentais.fragment.UefaFragment


class ViewPagerAdapter(fragmentManager: FragmentManager)
    : FragmentPagerAdapter(fragmentManager) {

    override fun getCount(): Int {
        return FragmentAbstract.TOTAL_SUBCLASSES
    }

    /*
     * Retorna o fragmento que será apresentado na tela do
     * device.
     * */
    override fun getItem(position: Int): Fragment? {
        when (position) {
            LibertadoresFragment.POS -> return LibertadoresFragment()
            AfcFragment.POS -> return AfcFragment()
            else -> return UefaFragment()
        }
    }

    /*
     * Retorna o título que poderá ser utilizado como título
     * de tela.
     * */
    override fun getPageTitle(position: Int): CharSequence {
        when (position) {
            LibertadoresFragment.POS -> return LibertadoresFragment.TITULO
            AfcFragment.POS -> return AfcFragment.TITULO
            else -> return UefaFragment.TITULO
        }
    }
}