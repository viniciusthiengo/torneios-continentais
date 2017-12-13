package thiengo.com.br.torneioscontinentais.fragment

import kotlinx.android.synthetic.main.lista_jogos.*
import thiengo.com.br.torneioscontinentais.adaptador.JogosAdapter
import thiengo.com.br.torneioscontinentais.dados.BancoDados


class LibertadoresFragment : FragmentAbstract() {

    companion object {
        @JvmField val TITULO = "Taça Libertadores"
    }

    override fun onStart() {
        super.onStart()
        rv_jogos.adapter = JogosAdapter(activity, BancoDados.getLibertadoresTimes())
    }
}