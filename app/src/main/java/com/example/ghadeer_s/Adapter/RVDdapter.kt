package com.example.ghadeer_s.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ghadeer_s.Database.TV
import com.example.ghadeer_s.DBFragment
import com.example.ghadeer_s.R
import com.example.ghadeer_s.databinding.DbRowBinding


class RVDdapter(val dbActivity: DBFragment) : RecyclerView.Adapter<RVDdapter.ViewHolder>() {
    private var tvs = emptyList<TV>()

    class ViewHolder(val binding: DbRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        DbRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tv = tvs[position]

        holder.binding.apply {
            if (tv.imageURL.isNotEmpty()) {
                Glide.with(holder.itemView.context)
                    .load(tv.imageURL)
                    .into(imgShow)
            } else {
                imgShow.setImageResource(R.drawable.noimage)
            }

            tvShowName.text = tv.title
            tvShowLanguage.text = tv.language

            llMain.setOnClickListener {
                Toast.makeText(holder.itemView.context, tv.summary, Toast.LENGTH_SHORT).show()
            }

            deleteBtn.setOnClickListener {
                dbActivity.deleteFromDB(tv)
            }
        }
    }

    override fun getItemCount(): Int = tvs.size

    fun updateShows(list: List<TV>) {
        this.tvs = list
        notifyDataSetChanged()
    }

}
