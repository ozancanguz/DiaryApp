package com.ozancanguz.diaryapp.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ozancanguz.diaryapp.R
import com.ozancanguz.diaryapp.data.model.Diary
import com.ozancanguz.diaryapp.ui.fragments.list.ListFragmentDirections
import kotlinx.android.synthetic.main.list_row_layout.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {


    var todolist= emptyList<Diary>()


    // update ui
    fun updateData(newData:List<Diary>){
        this.todolist=newData
        notifyDataSetChanged()
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.list_row_layout,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem=todolist[position]
        holder.itemView.title_txt.text=currentItem.title
        holder.itemView.description_txt.text=currentItem.description


        // send current diary to update diary
        holder.itemView.setOnClickListener {
            val directions=ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(directions)

        }
    }

    override fun getItemCount(): Int {
        return todolist.size
    }


}