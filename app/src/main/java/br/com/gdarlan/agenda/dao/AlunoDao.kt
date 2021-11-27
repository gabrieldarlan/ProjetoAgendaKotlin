package br.com.gdarlan.agenda.dao

import br.com.gdarlan.agenda.model.Aluno

class AlunoDao {

    companion object {
        private val alunos = mutableListOf<Aluno>()
        private var contadorDeIds = 1L
    }

    fun salva(aluno: Aluno?) {
        aluno?.id = contadorDeIds
        if (aluno != null) {
            alunos.add(aluno)
        }
        atualizaIds()
    }

    private fun atualizaIds() {
        contadorDeIds++
    }

    fun edita(aluno: Aluno?) {
        var alunoEncontrado: Aluno? = buscaAlunoPeloId(aluno)
        if (alunoEncontrado != null) {
            val posicaoAluno = alunos.indexOf(alunoEncontrado)
            if (aluno != null) {
                alunos[posicaoAluno] = aluno
            }
        }
    }

    private fun buscaAlunoPeloId(aluno: Aluno?): Aluno? {
        for (a in alunos) {
            if (a.id == aluno?.id) {
                return a
            }
        }
        return null
    }

    fun buscaTodos(): List<Aluno> = alunos.toList()

    fun remove(aluno: Aluno?) {
        val alunoDevolvido = buscaAlunoPeloId(aluno)
        if (alunoDevolvido != null) {
            alunos.remove(alunoDevolvido)
        }
    }
}
