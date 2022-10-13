package com.junior.testepi.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.junior.testepi.ListFragment
import com.junior.testepi.MainViewModel
import com.junior.testepi.R
import com.junior.testepi.databinding.CardLayoutBinding
import com.junior.testepi.model.Postagem

class PostagemAdapter(
    val context: Context,
    val postagemClickListener: PostagemClickListener,
    val mainViewModel: MainViewModel): RecyclerView.Adapter<PostagemAdapter.PostagemViewHolder>() {

    private  var listPostagem = emptyList<Postagem>()

    class PostagemViewHolder(val binding: CardLayoutBinding): RecyclerView.ViewHolder(binding.root){
    }
    // Serve para criar o layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostagemViewHolder {
        return  PostagemViewHolder(CardLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        )
    }

    override fun onBindViewHolder(holder: PostagemViewHolder, position: Int) {
        val postagem = listPostagem[position]

        holder.binding.textImage.text = postagem.imagem
        holder.binding.textDescricao.text = postagem.descricao
        holder.binding.textTema.text = postagem.tema.nome
        Glide
            .with(context)
            .load(postagem.imagem)
            .placeholder(R.drawable.ic_load)
            .into(holder.binding.imageView4)
        holder.itemView.setOnClickListener{
            postagemClickListener.onPostagemClickListener(postagem)
        }
        holder.binding.buttonDel.setOnClickListener {
            openDialogDel(postagem.id)
        }

    }

    override fun getItemCount(): Int {
        return listPostagem.size
    }

    fun setList(list: List<Postagem>){
        listPostagem = list.sortedByDescending { it.id }
        notifyDataSetChanged()
    }

    private fun openDialogDel(id: Long){
        AlertDialog.Builder(context)
            .setTitle("Deletar essa postagem?")
            .setPositiveButton("Sim"){ _,_ ->
                mainViewModel.deletarPostagem(id)
            }
            .setNegativeButton("Não"){
                    _,_ ->
            }
            .show()
    }
}