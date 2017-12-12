package thiengo.com.br.torneioscontinentais.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.lista_jogos.*

import thiengo.com.br.torneioscontinentais.R
import thiengo.com.br.torneioscontinentais.adapter.JogosAdapter
import thiengo.com.br.torneioscontinentais.dados.BancoDados


class UefaFragment : FragmentAbstract() {

    override fun onStart() {
        super.onStart()
        rv_jogos.adapter = JogosAdapter(activity, BancoDados.getUefaTimes())
    }
}
