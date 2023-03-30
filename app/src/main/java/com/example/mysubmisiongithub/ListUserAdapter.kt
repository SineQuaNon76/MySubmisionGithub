package com.example.mysubmisiongithub

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class ListUserAdapter(private val listUser: List<ItemsItem>): RecyclerView.Adapter<ListUserAdapter.ViewHolder>()  {


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val ivUser : ImageView = view.findViewById(R.id.iv_user)
        val tvUsername: TextView = view.findViewById(R.id.tv_username)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_user, parent, false))

    override fun getItemCount() = listUser.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = listUser[position]
        holder.tvUsername.text = user.login
        Glide.with(holder.itemView)
            .load(user.avatarUrl)
            .circleCrop()
            .into(holder.ivUser)


        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, DetailUserActivity::class.java)
            intent.putExtra(DetailUserActivity.LOGIN, user.login)
            holder.itemView.context.startActivity(intent)
        }

    }



}






