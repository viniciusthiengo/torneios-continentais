package thiengo.com.br.torneioscontinentais.atividade

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_detalhes_time.*
import thiengo.com.br.torneioscontinentais.R
import thiengo.com.br.torneioscontinentais.dominio.Time


class DetalhesTimeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_time)
        setSupportActionBar(toolbar)

        /*
         * Para liberar o back button na barra de topo da
         * atividade.
         * */
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }
    }

    /*
     * Hack code para que a atualização de título da atividade
     * seja realizada. Logo foi escolhido para ficar também
     * neste trecho de código a atualização do source do
     * ImageView de topo de tela.
     * */
    override fun onResume() {
        super.onResume()

        val time = intent.getParcelableExtra<Time>(Time.KEY)

        toolbar.title = time!!.nome
        iv_time.setImageResource( time.idEscudo )
    }

    /*
     * Para permitir que o back button tenha a ação de volta para
     * a atividade anterior.
     * */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
