package br.com.gdarlan.agenda.ui.activity

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import br.com.gdarlan.agenda.dao.AlunosDao
import br.com.gdarlan.agenda.databinding.ActivityListaAlunosBinding

class ListaAlunosActivity : AppCompatActivity() {
    companion object {
        private val tituloAppBar = "Lista de Alunos"
    }

    private val alunosDao = AlunosDao()
    private val binding by lazy {
        ActivityListaAlunosBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = tituloAppBar
        configuraFabNovoAluno()

    }

    private fun configuraFabNovoAluno() {
        val botaoNovoAluno = binding.activityListaAlunosFabNovoAluno
        botaoNovoAluno.setOnClickListener {
            abreFormularioAlunoActivity()
        }
    }

    private fun abreFormularioAlunoActivity() {
        val intent = Intent(this, FormularioAlunoActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        configuraLista()
    }

    private fun configuraLista() {
        val listaDeAlunos = binding.activityListaAlunosListView
        listaDeAlunos.adapter = ArrayAdapter(
            this,
            R.layout.simple_list_item_1,
            alunosDao.buscaTodos()
        )
    }
}

