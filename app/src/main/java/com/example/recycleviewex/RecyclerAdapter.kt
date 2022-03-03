package com.example.recycleviewex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlin.collections.ArrayList

class RecyclerAdapter(private var chapterList: List<HeroList.Data>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var mObjects: MutableList<HeroList.Data>? = ArrayList()// top level declaration



    fun setData(objects: List<HeroList.Data>){
        this.mObjects=objects as MutableList<HeroList.Data>
        notifyDataSetChanged()

    }

    private lateinit var mListener:OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnClickListener(mListener: OnItemClickListener){
        this.mListener=mListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false)
         return ViewHolder(v, mListener)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem=chapterList[position]

        holder.itemTitle.text=currentItem.name
        holder.itemDetails.text=currentItem.superhero_name
        Picasso.get().load(chapterList[position].photo).into(holder.itemImage)


    }

    override fun getItemCount() :Int{
        return if(chapterList !=null && chapterList.isNotEmpty()){
            chapterList.size
        } else{
            0
        }

    }


    class ViewHolder(itemView: View, private val mListener: OnItemClickListener):RecyclerView.ViewHolder(
        itemView){
        var itemImage:ImageView=itemView.findViewById(R.id.item_image)
        var itemTitle:TextView=itemView.findViewById(R.id.item_title)
        var itemDetails:TextView=itemView.findViewById(R.id.item_details)

        init {
            itemView.setOnClickListener{
                @Suppress("DEPRECATION")
                mListener.onItemClick(adapterPosition)
            }
        }

    }


    fun getList(): List<HeroList.Data>? {
        return chapterList
    }


}