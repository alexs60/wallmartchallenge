package com.alessandrofarandagancio.wallmartchallenge.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.alessandrofarandagancio.wallmartchallenge.R
import com.alessandrofarandagancio.wallmartchallenge.databinding.FragmentFirstBinding
import com.alessandrofarandagancio.wallmartchallenge.ui.model.State
import com.alessandrofarandagancio.wallmartchallenge.ui.view.utils.EmptyDataObserver
import com.alessandrofarandagancio.wallmartchallenge.ui.viewmodel.StateViewModel
import okhttp3.internal.notify

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(R.layout.fragment_first) {

    private var _binding: FragmentFirstBinding? = null

    private val stateViewModel: StateViewModel by activityViewModels<StateViewModel>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)


        var stateItemAdapter = StateItemAdapter { state -> adapterOnClick(state) }
        stateItemAdapter.registerAdapterDataObserver(
            EmptyDataObserver(
                binding.recyclerView,
                binding.emptyData.root
            )
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.addItemDecoration(ItemDecoration(16))
        binding.recyclerView.adapter = stateItemAdapter

        stateViewModel.stateListResponse.observe(viewLifecycleOwner, Observer {
            stateItemAdapter.submitList(it)
            stateItemAdapter.notifyDataSetChanged()
        })

        return binding.root

    }

    override fun onResume() {
        super.onResume()
        stateViewModel.refreshCoins()
    }

    private fun adapterOnClick(state: State) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}