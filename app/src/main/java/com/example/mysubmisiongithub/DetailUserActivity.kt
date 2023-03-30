package com.example.mysubmisiongithub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.mysubmisiongithub.databinding.ActivityDetailUserBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var detailViewModel: DetailViewModel
    private val follower = ArrayList<String>()
    private val following = ArrayList<String>()


    companion object{
        const val LOGIN = "extra"

        @StringRes
        private val TAB_T = intArrayOf(
            R.string.followers,
            R.string.following,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)

        val user = intent.getStringExtra(LOGIN)
        val sectionPagerAdapter = SectionPagerAdapter(this)
        sectionPagerAdapter.user = user.toString()

        val sectionsPagerAdapter = SectionPagerAdapter(this)
        val viewPager2: ViewPager2 = findViewById(R.id.viewPager2)
        viewPager2.adapter = sectionsPagerAdapter
        val tabs : TabLayout = findViewById(R.id.tablayout)
        TabLayoutMediator(tabs, viewPager2) {tab, posisiton ->
            tab.text = resources.getString(TAB_T[posisiton])
        }.attach()



//        if (user != null){
//            showLoading(true)
//            detailViewModel.detailUserRespone(user)
//            showLoading(false)
//        }
//        detailViewModel.detailUser.observe(this, {detail ->
//            setData(detail)
//        })
//        detailViewModel.isLoading.observe(this, {
//            showLoading(it)
//        })

        detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)
        user?.let { detailViewModel.detailUserRespone(it) }

        detailViewModel.isLoading.observe(this, {
            showLoading(it)
        })

        detailViewModel.detailUser.observe(this, {
            if(it != null ){
                binding.tvUsername.text = it.login
                binding.tvName.text = it.name
                binding.tvFollowers.text = "${it.followers} Pengikut"
                binding.tvFollowing.text = "${it.following} Mengikuti"
                Glide.with(this@DetailUserActivity)
                    .load(it.avatarUrl)
                    .circleCrop()
                    .into(binding.ivProfile)
            }
        })


    }

//    private fun setData(detail: DetailUserResponse?) {
//        Glide.with(this@DetailUserActivity)
//            .load(detail?.avatarUrl)
//            .into(binding.ivProfile)
//        binding.tvUsername.text = detail?.login
//        binding.tvName.text = detail?.name
//        binding.tvFollowers.text = detail?.followers.toString()
//        binding.tvFollowing.text = detail?.following.toString()
//    }


    private fun showLoading(loading: Boolean){
        if (loading){
            binding.progressBar3.visibility = View.VISIBLE
        }else{
            binding.progressBar3.visibility = View.GONE
        }
    }
}