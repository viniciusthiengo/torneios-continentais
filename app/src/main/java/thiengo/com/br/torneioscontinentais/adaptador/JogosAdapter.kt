package thiengo.com.br.torneioscontinentais.adaptador

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.flexbox.FlexboxLayout
import thiengo.com.br.torneioscontinentais.R
import thiengo.com.br.torneioscontinentais.atividade.DetalhesTimeActivity
import thiengo.com.br.torneioscontinentais.dominio.Jogo
import thiengo.com.br.torneioscontinentais.dominio.Time


class JogosAdapter(
    private val context: Context,
    private val jogos: List<Jogo>) :
        RecyclerView.Adapter<JogosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int) : JogosAdapter.ViewHolder {

        val v = LayoutInflater
            .from(context)
            .inflate(R.layout.jogo, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setDados(jogos[position])
    }

    override fun getItemCount(): Int {
        return jogos.size
    }

    inner class ViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView),
            View.OnClickListener {

        /* Time casa */
        var llTc: LinearLayout
        var ivTcBandeiraPais: ImageView
        var ivTcEscudo: ImageView
        var tvTcNome: TextView

        /* Time visitante */
        var llTv: LinearLayout
        var ivTvBandeiraPais: ImageView
        var ivTvEscudo: ImageView
        var tvTvNome: TextView

        var tvData: TextView
        var tvHorario: TextView
        var tvEstadio: TextView

        init {
            llTc = itemView.findViewById(R.id.ll_tc)
            llTc.setOnClickListener(this)
            ivTcBandeiraPais = itemView.findViewById(R.id.iv_tc_bandeira_pais)
            ivTcEscudo = itemView.findViewById(R.id.iv_tc_escudo)
            tvTcNome = itemView.findViewById(R.id.tv_tc_nome)

            llTv = itemView.findViewById(R.id.ll_tv)
            llTv.setOnClickListener(this)
            ivTvBandeiraPais = itemView.findViewById(R.id.iv_tv_bandeira_pais)
            ivTvEscudo = itemView.findViewById(R.id.iv_tv_escudo)
            tvTvNome = itemView.findViewById(R.id.tv_tv_nome)

            tvData = itemView.findViewById(R.id.tv_data)
            tvHorario = itemView.findViewById(R.id.tv_horario)
            tvEstadio = itemView.findViewById(R.id.tv_estadio)
        }

        fun setDados(jogo: Jogo) {
            ivTcBandeiraPais.setImageResource( jogo.timeCasa.idBandeiraPais )
            ivTcEscudo.setImageResource( jogo.timeCasa.idEscudo )
            tvTcNome.text = jogo.timeCasa.nome
            llTc.tag = jogo.timeCasa /* Hackcode para recuperar o objeto correto no listener de clique. */
            estrelasCampeonatosGanhos(llTc, jogo.timeCasa.qtdCampeonatos)

            ivTvBandeiraPais.setImageResource( jogo.timeVisitante.idBandeiraPais )
            ivTvEscudo.setImageResource( jogo.timeVisitante.idEscudo )
            tvTvNome.text = jogo.timeVisitante.nome
            llTv.tag = jogo.timeVisitante /* Hackcode para recuperar o objeto correto no listener de clique. */
            estrelasCampeonatosGanhos(llTv, jogo.timeVisitante.qtdCampeonatos)

            tvData.text = jogo.data
            tvHorario.text = jogo.horario
            tvEstadio.text = jogo.estadio
        }

        /*
         * O número de ImageViews, com o ícone de estrela, presentes no
         * layout estrelas.xml é o mesmo número do time que mais ganhou
         * títulos continentais, 12. Assim o algoritmo abaixo somente
         * tem de trabalhar o apresentar / esconder dos ImageViews. Essa
         * é uma abordagem mais eficiente do que ter de criar e destruir
         * ImageViews em tempo de execução.
         * */
        private fun estrelasCampeonatosGanhos(ll: LinearLayout, qtdCampeonatos: Int){

            for ( i in 0..ll.childCount ) {

                if( ll.getChildAt(i) is FlexboxLayout ){
                    val view = ll.getChildAt(i) as FlexboxLayout

                    for ( j in 0..(qtdCampeonatos - 1) ) {
                        view.getChildAt(j).visibility = View.VISIBLE
                    }
                    for ( j in qtdCampeonatos..(view.childCount - 1) ) {
                        view.getChildAt(j).visibility = View.GONE
                    }
                }
            }
        }

        /* O parâmetro view é na verdade o componente LinearLayout que
         * recebeu o listener de clique em init{}. Na propriedade tag
         * há o objeto time correto.
         * */
        override fun onClick(view: View?) {
            val intent = Intent(context, DetalhesTimeActivity::class.java)
            intent.putExtra(Time.KEY, view!!.tag as Time)
            context.startActivity(intent)
        }
    }
}