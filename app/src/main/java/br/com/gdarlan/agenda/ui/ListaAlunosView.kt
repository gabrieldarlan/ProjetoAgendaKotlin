package br.com.gdarlan.agenda.ui

import android.app.AlertDialog
import android.content.Context
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ListView
import br.com.gdarlan.agenda.dao.AlunoDao
import br.com.gdarlan.agenda.model.Aluno
import br.com.gdarlan.agenda.ui.adapter.ListaAlunosAdapter

class ListaAlunosView(val context: Context) {

    private val adapter: ListaAlunosAdapter by lazy {
        ListaAlunosAdapter(context)
    }

    private val alunoDao = AlunoDao()

    fun confirmaRemocao(item: MenuItem) {
        AlertDialog
            .Builder(context)
            .setTitle("Removendo aluno")
            .setMessage("Tem certeza que deseja remover o aluno?")
            .setPositiveButton("Sim") { _, _ ->
                val menuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val aluno: Aluno = adapter.getItem(menuInfo.position)
                remove(aluno)
            }
            .setNegativeButton("Não", null)
            .show()
    }

    private fun remove(aluno: Aluno?) {
        alunoDao.remove(aluno)
        adapter.remove(aluno)
    }

    fun atualizaAlunos() {
        adapter.atualiza(alunoDao.buscaTodos())
    }

    fun configuraAdapter(listaDeAlunos: ListView) {
        listaDeAlunos.adapter = adapter
    }

}