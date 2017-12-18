package thiengo.com.br.torneioscontinentais.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_detalhes_time.*

import thiengo.com.br.torneioscontinentais.R
import thiengo.com.br.torneioscontinentais.atividade.MainActivity
import thiengo.com.br.torneioscontinentais.dominio.Time


class DetalhesTimeFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {

        (activity as MainActivity).apresentarToolbar(false) /* Escondendo */
        return inflater!!.inflate(R.layout.activity_detalhes_time, container, false)
    }

    override fun onResume() {
        super.onResume()

        /*
         * Código necessário para que seja possível a inclusão da
         * seta de BackButton na barra de topo.
         * */
        toolbar.setNavigationOnClickListener(this)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)

        val time = arguments.getParcelable<Time>(Time.KEY)
        toolbar.title = time!!.nome
        iv_time.setImageResource( time.idEscudo )
    }

    override fun onClick(p0: View?) {
        activity.onBackPressed()
    }

    /*
     * Melhor método, do ciclo de vida do fragmento de detalhes,
     * para apresentar novamente a Toolbar da atividade principal
     * isso, pois neste método todos os cenários são cobertos.
     * */
    override fun onDetach() {
        (activity as MainActivity).apresentarToolbar(true) /* Apresentando */
        super.onDetach()
    }
}
