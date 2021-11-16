package br.com.gdarlan.agenda.ui.activity

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import br.com.gdarlan.agenda.dao.AlunosDao
import br.com.gdarlan.agenda.databinding.ActivityFormularioAlunoBinding
import br.com.gdarlan.agenda.model.Aluno

class FormularioAlunoActivity : AppCompatActivity() {
    companion object {
        private val tituloAppBar = "Novo Aluno"
    }

    private lateinit var campoNome: EditText
    private lateinit var campoTelefone: EditText

    private lateinit var campoEmail: EditText
    private val alunosDao = AlunosDao()

    private val binding by lazy {
        ActivityFormularioAlunoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = tituloAppBar
        inicializaCampos()
        configuraBotaoSalvar()
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = binding.activityFormularioAlunoBotaoSalvar
        botaoSalvar.setOnClickListener {
            val aluno = criaAluno()
            salva(aluno)
        }
    }

    private fun inicializaCampos() {
        campoNome = binding.activityFormularioAlunoCampoNome
        campoTelefone = binding.activityFormularioAlunoCampoTelefone
        campoEmail = binding.activityFormularioAlunoCampoEmail
    }

    private fun salva(aluno: Aluno) {
        alunosDao.salva(aluno)
        finish()
    }

    private fun criaAluno(): Aluno {
        val nome = campoNome.text.toString()
        val telefone = campoTelefone.text.toString()
        val email = campoEmail.text.toString()
        return Aluno(nome, telefone, email)
    }
}