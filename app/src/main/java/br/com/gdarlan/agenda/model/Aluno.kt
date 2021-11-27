package br.com.gdarlan.agenda.model

import java.io.Serializable

class Aluno(
    var id: Long = 0,
    var nome: String = "",
    var telefone: String = "",
    var email: String = ""
) : Serializable {

    override fun toString(): String {
        return "$nome $telefone"
    }

    fun temIdValido(): Boolean {
        return id > 0
    }

}
