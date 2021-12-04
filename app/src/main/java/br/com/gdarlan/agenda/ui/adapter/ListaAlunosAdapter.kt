package br.com.gdarlan.agenda.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import br.com.gdarlan.agenda.R
import br.com.gdarlan.agenda.model.Aluno

class ListaAlunosAdapter(private val context: Context) : BaseAdapter() {

    private val alunos = mutableListOf<Aluno>()

    override fun getCount(): Int {
        return alunos.size
    }

    override fun getItem(position: Int): Aluno {
        return alunos[position]
    }

    override fun getItemId(position: Int): Long {
        return alunos[position].id
    }

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val viewCriada = criaView(parent)
        val alunoDevolvido = alunos[position]
        vincula(viewCriada, alunoDevolvido)
        return viewCriada
    }

    private fun vincula(
        view: View,
        aluno: Aluno
    ) {
        val nome = view.findViewById<TextView>(R.id.item_aluno_nome)
        nome.text = aluno.nome

        val telefone = view.findViewById<TextView>(R.id.item_aluno_telefone)
        telefone.text = aluno.telefone
    }

    private fun criaView(parent: ViewGroup?) =
        LayoutInflater.from(context).inflate(R.layout.item_aluno, parent, false)

    fun atualiza(alunos: List<Aluno>) {
        this.alunos.clear()
        this.alunos.addAll(alunos)
        notifyDataSetChanged()
    }

    fun remove(aluno: Aluno?) {
        alunos.remove(aluno)
        notifyDataSetChanged()
    }

}