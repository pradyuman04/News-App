package com.example.newshub.fragment.childfragment.childFrafmentAdapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newshub.R
import com.example.newshub.activity.articlesActivity.view.articlesActivity
import com.example.newshub.utils.ArticlesItem2

class IndiaAdapter(val activity: FragmentActivity?, val list: List<ArticlesItem2>) : RecyclerView.Adapter<IndiaAdapter.ViewData>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.india_item,parent,false)
        return ViewData(view)
    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {
        Glide.with(activity!!).load(list[position].urlToImage).into(holder.imageView)
        holder.titleTxt.text = list[position].title
        holder.descriptionTxt.text = list[position].description
        holder.descriptionTxt.text = list[position].content

        holder.indiaCardView.setOnClickListener {

            var intent = Intent(activity, articlesActivity::class.java)
            intent.putExtra("n1",list[position].title)
            intent.putExtra("n2",list[position].author.toString())
            intent.putExtra("n3",list[position].urlToImage)
            intent.putExtra("n4",list[position].description)
            intent.putExtra("n5",list[position].content)

            activity.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewData(itemView: View) : RecyclerView.ViewHolder(itemView){

        var imageView = itemView.findViewById<ImageView>(R.id.imageView)
        var titleTxt = itemView.findViewById<TextView>(R.id.titleTxt)
        var descriptionTxt = itemView.findViewById<TextView>(R.id.descriptionTxt)
        var indiaCardView = itemView.findViewById<CardView>(R.id.indiaCardView)


    }
}

