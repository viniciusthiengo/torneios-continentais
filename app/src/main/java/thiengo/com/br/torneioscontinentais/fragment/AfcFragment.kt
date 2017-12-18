package thiengo.com.br.torneioscontinentais.fragment

import kotlinx.android.synthetic.main.lista_jogos.*
import thiengo.com.br.torneioscontinentais.adaptador.JogosAdapter
import thiengo.com.br.torneioscontinentais.dados.BancoDados


class AfcFragment : FragmentAbstrato() {

    companion object {
        @JvmField val TITULO = "Liga dos Campeões da AFC"
    }

    override fun onStart() {
        super.onStart()
        rv_jogos.adapter = JogosAdapter(activity, BancoDados.getAfcTimes())
    }
}