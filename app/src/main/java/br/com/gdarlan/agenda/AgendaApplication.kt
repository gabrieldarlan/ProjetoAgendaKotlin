package br.com.gdarlan.agenda

import android.app.Application
import br.com.gdarlan.agenda.dao.AlunoDao
import br.com.gdarlan.agenda.model.Aluno

class AgendaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        criaAlunosDeTeste()

    }

    private fun criaAlunosDeTeste() {
        val alunoDao = AlunoDao()
        alunoDao.salva(Aluno(nome = "Alex", telefone = "111111", email = "alex@alura.com.br"))
        alunoDao.salva(Aluno(nome = "Fran", telefone = "222222", email = "fran@alura.com.br"))
    }
}