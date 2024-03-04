package dev.redfox.android_native_memoneet_assigment.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import dev.redfox.android_native_memoneet_assigment.R
import dev.redfox.android_native_memoneet_assigment.databinding.ItemQuizOptionBinding

class QuizRecyclerViewAdapter(
    private val selectListener: (Int) -> Unit
) : RecyclerView.Adapter<QuizRecyclerViewAdapter.ViewHolder>() {


    private val options = ArrayList<String>()
    private var selectedItemPosition: Int = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemQuizOptionBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_quiz_option, parent, false)
        return ViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return options.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(options[position], selectListener, position)
    }

    fun setList(items: List<String>) {
        options.clear()
        options.addAll(items)

    }

    fun setSelection(position: Int) {
        selectedItemPosition = position

    }

    inner class ViewHolder(val binding: ItemQuizOptionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(option: String, selectListener: (Int) -> Unit, position: Int) {
            binding.textview.text = option


            if (selectedItemPosition == position) {

                binding.textview.setBackgroundResource(R.color.blue)
            } else {

                binding.textview.setBackgroundResource(R.color.yellow)
            }
            binding.textview.setOnClickListener {
                // Reset background of previously selected item
                val previousSelectedItemPosition = selectedItemPosition
                selectedItemPosition = position
                notifyItemChanged(previousSelectedItemPosition)
                it.setBackgroundResource(R.color.blue)

                // Notify listener about item selection
                selectListener(position)

            }
        }


    }
}
