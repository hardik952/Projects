package com.example.newsapi.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.newsapi.Model.Category
import com.example.newsapi.databinding.LayoutCategoryBinding

class CategoryAdapter(var context: Context, var categoryList: MutableList<Category>) :
    Adapter<CategoryAdapter.MyViewHolder>() {

    var categoryClickListner: ((position: Int, category: Category) -> Unit)? = null

    class MyViewHolder(var bind: LayoutCategoryBinding) : ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutCategoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var category = categoryList[position]

        holder.bind.tvCategory.text = category.name

        holder.bind.tvCategory.setOnClickListener {
            categoryClickListner?.invoke(position, category)
        }
    }

}