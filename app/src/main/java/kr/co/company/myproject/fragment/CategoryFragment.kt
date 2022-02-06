package kr.co.company.myproject.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.company.myproject.adapter.CategoryAdapter
import kr.co.company.myproject.databinding.FragmentCategoryBinding
import kr.co.company.myproject.domain.todo.Todo
import kr.co.company.myproject.domain.category.Category
import kr.co.company.myproject.viewModel.CategoryViewModel
import kr.co.company.myproject.viewModel.TodoViewModel
import java.time.LocalDateTime

class CategoryFragment : Fragment() {
    lateinit var navController : NavController
    private var binding : FragmentCategoryBinding?=null
    private val categoryViewModel : CategoryViewModel by viewModels()
    private val todoViewModel : TodoViewModel by viewModels()
    private val adapter : CategoryAdapter by lazy { CategoryAdapter(requireContext(),1,viewLifecycleOwner,categoryViewModel,todoViewModel) }
//    private lateinit var getResultCategory : ActivityResultLauncher<Intent>
    lateinit var newCategory: Category
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("CategoryFragment","onCreate")
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentCategoryBinding.inflate(inflater,container,false)
        adapter.setHasStableIds(true)

        binding!!.categoryRecycle.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        binding!!.categoryRecycle.adapter=adapter

        categoryViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })
        return binding!!.root

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}