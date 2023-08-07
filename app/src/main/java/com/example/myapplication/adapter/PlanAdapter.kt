package com.example.myapplication.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.example.myapplication.R
import com.example.myapplication.model.Lesson

class PlanAdapter(private val lessonList: MutableList<Lesson>) :
    RecyclerView.Adapter<PlanAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_lesson,
            parent, false
        )

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = lessonList[position]

        holder.titleImage.setImageResource(currentItem.titleImage)
        holder.studentName.text = currentItem.name
        holder.studentNumber.text = currentItem.mobile_number.toString()
        holder.studentTime.text = currentItem.time.toString()
    }

    override fun getItemCount(): Int {

        return lessonList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titleImage: ShapeableImageView = itemView.findViewById(R.id.title_image)
        val studentName: TextView = itemView.findViewById(R.id.studentName)
        val studentNumber: TextView = itemView.findViewById(R.id.phoneNumber)
        val studentTime: TextView = itemView.findViewById(R.id.lessonTime)

    }

}