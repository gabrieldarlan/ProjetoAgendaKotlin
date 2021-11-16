package br.com.gdarlan.agenda.dao

import br.com.gdarlan.agenda.model.Aluno

class AlunosDao {

    companion object {
        private val alunos = mutableListOf<Aluno>()
    }

    fun salva(aluno: Aluno) {
        alunos.add(aluno)
    }

    fun buscaTodos(): List<Aluno> = alunos.toList()
}
