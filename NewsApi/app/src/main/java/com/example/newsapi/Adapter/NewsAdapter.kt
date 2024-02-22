package com.example.newsapi.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.newsapi.Model.Article
import com.example.newsapi.databinding.LayoutNewsBinding
import com.squareup.picasso.Picasso


class NewsAdapter (var context: Context,var articleList: MutableList<Article>,private var onItemClickListener: OnItemClickListener):Adapter<NewsAdapter.MyViewHolder>() {
    interface OnItemClickListener{
        fun onItemClick(url:String)
    }
    inner class MyViewHolder(var bind: LayoutNewsBinding) : ViewHolder(bind.root){
        init {
            bind.layoutNews.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val article = articleList[position]
                    onItemClickListener.onItemClick(article.url)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutNewsBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var article = articleList[position]

        holder.bind.tvTitle.text = article.title
        holder.bind.tvContent.text = article.content
        holder.bind.createdAt.text = article.publishedAt
        Picasso.get().load(article.image).into(holder.bind.ivNews);

//        holder.bind.layoutNews.setOnClickListener {
//            openLinkInBrowser(context, position, article.url)
//        }
    }
//    fun openLinkInBrowser(context: Context,position: Int,url:String){
//        var article = articleList[position]
//        var browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
//        context.startActivity(browserIntent)
//    }
}