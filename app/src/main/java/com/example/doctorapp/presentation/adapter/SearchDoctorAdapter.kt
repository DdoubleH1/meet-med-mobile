package com.example.doctorapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.doctorapp.R
import com.example.doctorapp.data.model.Doctor
import com.example.doctorapp.databinding.DoctorItemBinding
import com.example.doctorapp.presentation.diffUtil.DoctorDiffUtil

class SearchDoctorAdapter(
    private val context: Context,
    private val onDoctorClickListener: ((Doctor) -> Unit)? = null
) :
    ListAdapter<Doctor, SearchDoctorAdapter.SearchDoctorViewHolder>(DoctorDiffUtil()) {
    inner class SearchDoctorViewHolder(private val binding: DoctorItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(doctor: Doctor) {
            binding.apply {
                Glide.with(context)
                    .load(doctor.imageUrl)
                    .into(ivAvatar)
                tvDoctorName.text = doctor.name
                tvSpeciality.text = doctor.speciality
                tvRating.text = doctor.rating.toString()
                tvReview.text = when (doctor.reviewCount) {
                    1 -> String.format(
                        context.getString(R.string.string_review_num),
                        doctor.reviewCount
                    )
                    else -> String.format(
                        context.getString(R.string.string_review_nums),
                        doctor.reviewCount
                    )
                }
                itemView.setOnClickListener {
                    onDoctorClickListener?.invoke(doctor)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchDoctorViewHolder {
        val binding = DoctorItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SearchDoctorViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: SearchDoctorViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    interface onDoctorClickListenr {
        fun onDoctorClick(doctor: Doctor)
    }
}