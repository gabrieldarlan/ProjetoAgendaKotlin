package br.com.gdarlan.agenda.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import br.com.gdarlan.agenda.R
import br.com.gdarlan.agenda.dao.AlunoDao
import br.com.gdarlan.agenda.databinding.ActivityListaAlunosBinding

import br.com.gdarlan.agenda.model.Aluno
import br.com.gdarlan.agenda.ui.adapter.ListaAlunosAdapter

class ListaAlunosActivity : AppCompatActivity() {
    companion object {
        private val tituloAppBar = "Lista de Alunos"
        private val CHAVE_ALUNO = "aluno"
    }

    private val alunoDao = AlunoDao()
    private val adapter = ListaAlunosAdapter(this)


    private val binding by lazy {
        ActivityListaAlunosBinding.inflate(layoutInflater)
    }

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

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.activity_lista_alunos_menu, menu)

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
        registerForContextMenu(listaDeAlunos)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        if (itemId == R.id.activity_lista_alunos_menu_remover) {
            val menuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
            val aluno: Aluno = adapter.getItem(menuInfo.position)
            remove(aluno)
        }
        return super.onContextItemSelected(item)
    }

    private fun remove(aluno: Aluno?) {
        alunoDao.remove(aluno)
        adapter.remove(aluno)
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

    private fun configuraAdapter(listaDeAlunos: ListView) {
        listaDeAlunos.adapter = adapter
    }

}

