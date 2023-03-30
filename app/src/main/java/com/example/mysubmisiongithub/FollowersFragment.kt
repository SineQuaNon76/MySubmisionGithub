package com.example.mysubmisiongithub

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysubmisiongithub.databinding.FragmentFollowersBinding


class FollowersFragment : Fragment() {


    private lateinit var binding: FragmentFollowersBinding
    private lateinit var detailViewModel: DetailViewModel
    companion object{
        const val SECTION_POSITION = "position"
        const val SECTION_USERNAME = ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFollowersBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var user = arguments?.getString(SECTION_USERNAME)
        var position = 0

        Log.d("arguments: position", position.toString())
        Log.d("arguments: username", user.toString())

        detailViewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)
        arguments?.let {
            position = it.getInt(SECTION_POSITION)
            user = it.getString(SECTION_USERNAME)
        }


        if (position == 1) {
            showLoading(true)
            user?.let { detailViewModel.getFollowers(it) }
            detailViewModel.followers.observe(viewLifecycleOwner, {
                setData(it)
                showLoading(false)
            })
        }else{
            showLoading(true)
            user.let { detailViewModel.getFollowing() }
            detailViewModel.following.observe(viewLifecycleOwner){
                setData(it)
                showLoading(false)
            }
        }
    }

    private fun setData(listUser: List<ItemsItem>) {
        binding.apply {
            binding.rvFrag.layoutManager = LinearLayoutManager(requireActivity())
            val adapter = ListUserAdapter(listUser)
            binding.rvFrag.adapter = adapter
        }
    }

    private fun showLoading(isLoading: Boolean){
        if (isLoading) {
            binding.loadingFrag.visibility = View.VISIBLE
        } else {
            binding.loadingFrag.visibility = View.GONE
        }
    }


}

