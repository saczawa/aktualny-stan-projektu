package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Lesson

class StudentAdapter(private val lessonList: MutableList<Lesson>) :
    RecyclerView.Adapter<StudentAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_student,
            parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = lessonList[position]

        holder.studentPhoto.setImageResource(currentItem.titleImage)
        holder.studentName.text = currentItem.name
        holder.studentSurname.text = currentItem.surname
        holder.studentPhone.text = currentItem.mobile_number.toString()
        holder.studentEmail.text = currentItem.email
        holder.studentLessonDay.text = currentItem.daysOfWeek.joinToString(", ")
        holder.studentLessonTime.text = currentItem.time.toString()

    }

    override fun getItemCount(): Int {

        return lessonList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val studentPhoto : ImageView = itemView.findViewById(R.id.student_photo_imageView)
        val studentName : TextView = itemView.findViewById(R.id.student_name_textView)
        val studentSurname : TextView = itemView.findViewById(R.id.student_surname_textView)
        val studentPhone : TextView = itemView.findViewById(R.id.student_phone_textView)
        val studentEmail : TextView = itemView.findViewById(R.id.student_email_textView)
        val studentLessonDay : TextView = itemView.findViewById(R.id.student_lesson_day_textView)
        val studentLessonTime : TextView = itemView.findViewById(R.id.student_lesson_time_textView)


    }

}