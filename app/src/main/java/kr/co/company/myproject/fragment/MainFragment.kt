package kr.co.company.myproject.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kr.co.company.myproject.adapter.ViewPagerAdapter
import kr.co.company.myproject.databinding.FragmentMainBinding
import kr.co.company.myproject.domain.category.Category
import java.time.LocalDateTime

class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding
    lateinit var navController : NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentMainBinding.inflate(layoutInflater)
        Log.i("MainFragment","created")
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        binding.pager.adapter = ViewPagerAdapter(requireActivity())
        TabLayoutMediator(binding.tabLayout,binding.pager){
                tab,position->when(position){
            0->tab.text="TODO"
            else->tab.text="Category"
        }
        }.attach()
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController=Navigation.findNavController(view)
        binding.writeIcon.setOnClickListener{
            val today = LocalDateTime.now()
            val category = Category(today,today)
            val action =
                MainFragmentDirections.actionMainFragmentToCategoryDialogFragment2(
                    category
                )
////            navController.navigate()
////            findNavController().navigate(action)
//
////            view.findNavController().navigate(action)
            navController.navigate(action)
        }
    }
}