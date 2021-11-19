package br.com.gdarlan.agenda.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import br.com.gdarlan.agenda.dao.AlunoDao
import br.com.gdarlan.agenda.databinding.ActivityListaAlunosBinding
import br.com.gdarlan.agenda.model.Aluno

class ListaAlunosActivity : AppCompatActivity() {
    companion object {
        private val tituloAppBar = "Lista de Alunos"
        private val CHAVE_ALUNO = "aluno"
    }

    private val alunoDao = AlunoDao()
    private val binding by lazy {
        ActivityListaAlunosBinding.inflate(layoutInflater)
    }
    private lateinit var adapter: ArrayAdapter<Aluno>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = tituloAppBar
        configuraFabNovoAluno()
        configuraLista()
        alunoDao.salva(Aluno(nome = "Alex", telefone = "111111", email = "alex@alura.com.br"))
        alunoDao.salva(Aluno(nome = "Fran", telefone = "222222", email = "fran@alura.com.br"))

    }

    override fun onResume() {
        super.onResume()
        atualizaAlunos()
    }

    private fun atualizaAlunos() {
        adapter.clear()
        adapter.addAll(alunoDao.buscaTodos())
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

        configuraAdapter(listaDeAlunos)
        configuraListernerDeClickPorItem(listaDeAlunos)
        configuraListenerDeCliqueLongoPorItem(listaDeAlunos)
    }

    private fun configuraListenerDeCliqueLongoPorItem(listaDeAlunos: ListView) {
        listaDeAlunos.setOnItemLongClickListener { parent, view, position, id ->
            val alunoEscolhido = parent.getItemAtPosition(position) as Aluno
            remove(alunoEscolhido)
            return@setOnItemLongClickListener (true)
        }
    }

    private fun remove(aluno: Aluno) {
        alunoDao.remove(aluno)
        adapter.remove(aluno)
    }

    private fun configuraListernerDeClickPorItem(
        listaDeAlunos: ListView
    ) {
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

    private fun configuraAdapter(listaDeAlunos: ListView) {
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1)
        listaDeAlunos.adapter = adapter
    }
}

