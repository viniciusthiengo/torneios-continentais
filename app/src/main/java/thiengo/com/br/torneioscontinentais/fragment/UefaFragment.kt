package thiengo.com.br.torneioscontinentais.fragment

import kotlinx.android.synthetic.main.lista_jogos.*
import thiengo.com.br.torneioscontinentais.adaptador.JogosAdapter
import thiengo.com.br.torneioscontinentais.dados.BancoDados


class UefaFragment : FragmentAbstract() {

    companion object {
        @JvmField val TITULO = "UEFA Champions League"
    }

    override fun onStart() {
        super.onStart()
        rv_jogos.adapter = JogosAdapter(activity, BancoDados.getUefaTimes())
    }
}
