package thiengo.com.br.torneioscontinentais.fragment

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.lista_jogos.*
import thiengo.com.br.torneioscontinentais.R


/*
 * Classe responsável por evitar códigos repetidos em
 * todas as subclasses caso ela não fosse criada.
 * */
abstract class FragmentAbstract : Fragment() {
    var rvState: Parcelable? = null

    companion object {
        @JvmField val KEY = "fragment"
        @JvmField val TITULO = "titulo"
    }

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle? ): View? {

        return inflater!!.inflate(R.layout.lista_jogos, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rv_jogos.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(activity)
        rv_jogos.layoutManager = layoutManager
        val divider = DividerItemDecoration(activity, layoutManager.orientation)
        rv_jogos.addItemDecoration(divider)

        rvState()
    }

    override fun onPause() {
        super.onPause()
        rvState()
    }

    override fun onResume() {
        super.onResume()
        rv_jogos.getLayoutManager().onRestoreInstanceState(rvState);
    }

    /*
     * Junto ao código em onActivityCreated(), onResume() e onPause()
     * rvState permite salvar o status do RecyclerView e então voltar
     * com ele na mesma posição.
     * */
    private fun rvState(){
        rvState = rv_jogos.getLayoutManager().onSaveInstanceState()
    }
}