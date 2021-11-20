package br.com.gdarlan.agenda.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import br.com.gdarlan.agenda.R
import br.com.gdarlan.agenda.dao.AlunoDao
import br.com.gdarlan.agenda.databinding.ActivityFormularioAlunoBinding
import br.com.gdarlan.agenda.model.Aluno

class FormularioAlunoActivity : AppCompatActivity() {
    companion object {
        private val tituloAppBarNovoAluno = "Novo Aluno"
        private val CHAVE_ALUNO = "aluno"
        private val tituloAppBarEditaAluno = "Edita Aluno"
    }

    private lateinit var campoNome: EditText
    private lateinit var campoTelefone: EditText
    private lateinit var campoEmail: EditText
    private val alunosDao = AlunoDao()

    private val binding by lazy {
        ActivityFormularioAlunoBinding.inflate(layoutInflater)
    }

    private var aluno: Aluno? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = tituloAppBarNovoAluno
        inicializaCampos()
        carregaAluno()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_formulario_aluno_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.activity_formulario_aluno_menu -> finalizaFormulario()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun carregaAluno() {
        val dados = intent
        when {
            dados.hasExtra(CHAVE_ALUNO) -> {
                title = tituloAppBarEditaAluno
                aluno = dados.getSerializableExtra(CHAVE_ALUNO) as Aluno
                preencheCampos()
            }
            else -> {
                title = tituloAppBarNovoAluno
                aluno = Aluno()
            }
        }
    }

    private fun preencheCampos() {
        campoNome.setText(aluno!!.nome)
        campoTelefone.setText(aluno!!.telefone)
        campoEmail.setText(aluno!!.email)
    }

    private fun finalizaFormulario() {
        preencheAluno()
        when {
            aluno?.temIdValido() == true -> alunosDao.edita(aluno)
            else -> alunosDao.salva(aluno)
        }
        finish()
    }

    private fun inicializaCampos() {
        campoNome = binding.activityFormularioAlunoCampoNome
        campoTelefone = binding.activityFormularioAlunoCampoTelefone
        campoEmail = binding.activityFormularioAlunoCampoEmail
    }


    private fun preencheAluno() {
        val nome = campoNome.text.toString()
        val telefone = campoTelefone.text.toString()
        val email = campoEmail.text.toString()

        aluno?.nome = nome
        aluno?.telefone = telefone
        aluno?.email = email
    }
}