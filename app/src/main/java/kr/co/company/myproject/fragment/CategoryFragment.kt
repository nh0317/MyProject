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
    private val adapter : CategoryAdapter by lazy { CategoryAdapter(requireContext(),viewLifecycleOwner,categoryViewModel,todoViewModel) }
//    private lateinit var getResultCategory : ActivityResultLauncher<Intent>
    lateinit var newCategory: Category
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("CategoryFragment","onCreate")
//        val todo = Todo(1, LocalDateTime.now(),LocalDateTime.now())
//        todoViewModel.addTodo(todo)
//        getResultCategory = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//            if (it.resultCode==RESULT_OK){
//                newCategory= it.data?.getParcelableExtra("category") ?:Category()
//                categoryViewModel.addCategory(newCategory)
//            }
            // Handle the returned Uri
//        }
//        val category = Category(2022,1,1,2022,2,22,"Test","TestMemo");
//        categoryViewModel.addCategory(category)
//        categoryViewModel.addCategory(category)
//        categoryViewModel.readAllData
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
//        categoryViewModel.todoOfCategory.observe(viewLifecycleOwner) {
//            adapter.setTodos(it)
//        }
//        val navHostFragment =  parentFragmentManager.findFragmentById(R.id.nav_category) as NavHostFragment
//        parentFragmentManager.
//        val navController = navHostFragment.navController

//        binding!!.writeIcon.setOnClickListener{
//            view:View->
//            val today = LocalDateTime.now()
//            val category = Category(today,today)
//            val action = CategoryDialogFragmentDirections.actionCategoryDialogFragmentToCategoryFragment(category)
////            navController.navigate()
////            findNavController().navigate(action)
//
////            view.findNavController().navigate(action)
//            navController.navigate(action)
//            val args : CategoryFragmentArgs by navArgs()
//            newCategory=args.newCategory

//            val intent = Intent(activity, CategoryDialogFragment::class.java).apply {
//                putExtra("category", category)
//                Log.i("CategoryFragment",category.startDate.toString())
//            }
//            getResultCategory.launch(intent)

//        }
        // Inflate the layout for this fragment
        return binding!!.root

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        navController = Navigation.findNavController(view)
//        val action = CategoryFragmentDirections.actionCategoryFragmentToCategoryDialogFragment2(adapter.getCategory())
//        binding!!.
//            view:View->
//            val today = LocalDateTime.now()
//            val category = Category(today,today)
//            val action = CategoryDialogFragmentDirections.actionCategoryDialogFragmentToCategoryFragment(category)
////            navController.navigate()
////            findNavController().navigate(action)
//
////            view.findNavController().navigate(action)
//            navController.navigate(action)
//            val args : CategoryFragmentArgs by navArgs()
//            newCategory=args.newCategory
//        }
    }
}