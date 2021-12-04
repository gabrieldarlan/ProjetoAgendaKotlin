package br.com.gdarlan.agenda.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import br.com.gdarlan.agenda.R
import br.com.gdarlan.agenda.databinding.ActivityListaAlunosBinding
import br.com.gdarlan.agenda.model.Aluno
import br.com.gdarlan.agenda.ui.ListaAlunosView

class ListaAlunosActivity : AppCompatActivity() {
    companion object {
        private const val tituloAppBar = "Lista de Alunos"
        private const val CHAVE_ALUNO = "aluno"
    }

    private val listaAlunosView = ListaAlunosView(this)

    private val binding by lazy {
        ActivityListaAlunosBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = tituloAppBar
        configuraFabNovoAluno()
        configuraLista()
    }

    override fun onResume() {
        super.onResume()
        listaAlunosView.atualizaAlunos()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.activity_lista_alunos_menu, menu)

    }

    private fun configuraFabNovoAluno() {
        val botaoNovoAluno = binding.activityListaAlunosFabNovoAluno
        botaoNovoAluno.setOnClickListener {
            abreFormularioModoInsereAluno()
        }
    }

    private fun abreFormularioModoInsereAluno() {
        val intent = Intent(this, FormularioAlunoActivity::class.java)
        startActivity(intent)
    }

    private fun configuraLista() {
        val listaDeAlunos = binding.activityListaAlunosListView
        listaAlunosView.configuraAdapter(listaDeAlunos)
        configuraListernerDeClickPorItem(listaDeAlunos)
        registerForContextMenu(listaDeAlunos)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        if (itemId == R.id.activity_lista_alunos_menu_remover) {
            listaAlunosView.confirmaRemocao(item)
        }
        return super.onContextItemSelected(item)
    }

    private fun configuraListernerDeClickPorItem(listaDeAlunos: ListView) {
        listaDeAlunos.setOnItemClickListener { parent, view, position, id ->
            val alunoEscolhido = parent.getItemAtPosition(position) as Aluno
            abreFormularioModoEditaAluno(alunoEscolhido)
        }
    }

    private fun abreFormularioModoEditaAluno(aluno: Aluno) {
        val vaiParaFormularioActivity = Intent(this, FormularioAlunoActivity::class.java)
        with(vaiParaFormularioActivity) {
            putExtra(CHAVE_ALUNO, aluno)
            startActivity(this)
        }
    }

}

