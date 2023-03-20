package com.alessandrofarandagancio.wallmartchallenge.ui.view

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alessandrofarandagancio.wallmartchallenge.R
import com.alessandrofarandagancio.wallmartchallenge.ui.model.State

class StateItemAdapter(private val onClick: (State) -> Unit) :
    ListAdapter<State, StateItemAdapter.CoinViewHolder>(CoinDiffCallback) {

    class CoinViewHolder(view: View, val onClick: (State) -> Unit) :
        RecyclerView.ViewHolder(view) {
        private val textViewName: TextView = view.findViewById(R.id.name)
        private val textViewCapital: TextView = view.findViewById(R.id.capital)
        private val textViewCode: TextView = view.findViewById(R.id.code)
        private val imageViewFlag: ImageView = view.findViewById(R.id.flag)
        private lateinit var currentState: State

        init {
            view.setOnClickListener {
                onClick(currentState)
            }
        }

        fun bind(state: State) {
            currentState = state
            textViewName.text = state.name
            textViewCapital.text = state.capital
            textViewCode.text = state.code.uppercase()
            //imageViewFlag.drawable = state.flag
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CoinViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.state_item, viewGroup, false)

        return CoinViewHolder(view, onClick)
    }

    override fun onBindViewHolder(viewHolder: CoinViewHolder, position: Int) {
        val school = getItem(position)
        viewHolder.bind(school)
    }
}

class ItemDecoration(private val spaceSize: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = spaceSize
            }
            left = spaceSize
            right = spaceSize
            bottom = spaceSize
        }
    }
}

object CoinDiffCallback : DiffUtil.ItemCallback<State>() {
    override fun areItemsTheSame(oldItem: State, newItem: State): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: State, newItem: State): Boolean {
        return oldItem.code == newItem.code
    }
}
