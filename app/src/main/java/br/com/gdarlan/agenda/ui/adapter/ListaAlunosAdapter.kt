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
        return alunos[position].id;
    }

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val viewCriada = LayoutInflater.from(context).inflate(R.layout.item_aluno, parent, false)
        val alunoDevolvido = alunos[position]

        val nome = viewCriada.findViewById<TextView>(R.id.item_aluno_nome)
        nome.text = alunoDevolvido.nome

        val telefone = viewCriada.findViewById<TextView>(R.id.item_aluno_telefone)
        telefone.text = alunoDevolvido.telefone

        return viewCriada
    }

    fun clear() {
        alunos.clear()
    }

    fun addAll(alunos: List<Aluno>) {
        this.alunos.addAll(alunos)
    }

    fun remove(aluno: Aluno?) {
        alunos.remove(aluno)
    }

}