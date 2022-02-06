package kr.co.company.myproject.fragment

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kr.co.company.myproject.adapter.ViewPagerAdapter
import kr.co.company.myproject.databinding.FragmentMainBinding
import kr.co.company.myproject.domain.category.Category
import kr.co.company.myproject.viewModel.CategoryViewModel
import java.time.LocalDate

class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding
    lateinit var navController : NavController
    private val categoryViewModel : CategoryViewModel by viewModels()
    private var tabPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val sharedPref = activity?.getSharedPreferences("viewInfo", Context.MODE_PRIVATE)
        tabPosition = sharedPref?.getInt("tabPosition",0) ?: 0

        this.binding = FragmentMainBinding.inflate(layoutInflater)
        Log.i("MainFragment","created")
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tabPosition=tab!!.position
                sharedPref?.edit()?.putInt("tabPosition",tabPosition)?.apply()
                Log.i("MainFragment","tab changed positiion ${tabPosition}")
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        binding.pager.adapter = ViewPagerAdapter(requireActivity())
        TabLayoutMediator(binding.tabLayout,binding.pager){
                tab,position->
            when(position){
            0->tab.text="TODO"
            else->tab.text="Category"
        }
        }.attach()
        val args : MainFragmentArgs by navArgs()
        binding.pager.setCurrentItem(args.tabPosition,false)

        Log.i("MainFragment","tab positiion $args")
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController=Navigation.findNavController(view)
        binding.writeIcon.setOnClickListener{
            val today = LocalDate.now()
            val category = Category(today,today)
            val action =
                MainFragmentDirections.actionMainFragmentToCategoryDialogFragment2(
                    category)
////            navController.navigate()
////            findNavController().navigate(action)
//
////            view.findNavController().navigate(action)
            navController.navigate(action)
            Log.i("MainFragment","onViewCreated")

        }
    }

    override fun onResume() {
        super.onResume()
        Log.i("MainFragment","onResume")
    }

}