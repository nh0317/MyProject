package kr.co.company.myproject.fragment
//
//import android.os.Build
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.annotation.RequiresApi
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import androidx.lifecycle.Observer
//import androidx.navigation.NavController
//import androidx.recyclerview.widget.LinearLayoutManager
//import kr.co.company.myproject.adapter.TodoAdapter
//import kr.co.company.myproject.databinding.CategoryItemLayoutBinding
//import kr.co.company.myproject.domain.todo.Todo
//import kr.co.company.myproject.viewModel.TodoViewModel
//
//class TodoListFragment : Fragment() {
//    lateinit var navController : NavController
//    private var binding : CategoryItemLayoutBinding?=null
//    private val todoViewModel : TodoViewModel by viewModels()
//    private val adapter : TodoAdapter by lazy { TodoAdapter(todoViewModel) }
//    //    private lateinit var getResultTodo : ActivityResultLauncher<Intent>
//    lateinit var newTodo: Todo
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        Log.i("TodoFragment","onCreate")
//        val todo = Todo(1)
//        todoViewModel.addTodo(todo)
////        getResultTodo = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
////            if (it.resultCode==RESULT_OK){
////                newTodo= it.data?.getParcelableExtra("todo") ?:Todo()
////                todoViewModel.addTodo(newTodo)
////            }
//        // Handle the returned Uri
////        }
////        val todo = Todo(2022,1,1,2022,2,22,"Test","TestMemo");
////        todoViewModel.addTodo(todo)
////        todoViewModel.addTodo(todo)
////        todoViewModel.readAllData
//    }
//    @RequiresApi(Build.VERSION_CODES.O)
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        binding= CategoryItemLayoutBinding.inflate(inflater,container,false)
//        adapter.setHasStableIds(true)
//
//        binding!!.todoRecycle.layoutManager = LinearLayoutManager(activity,
//            LinearLayoutManager.VERTICAL,false)
//        binding!!.todoRecycle.adapter=adapter
//
//        todoViewModel.setData(binding)
//
//        todoViewModel.todoOfCategory
//            .observe(viewLifecycleOwner, Observer {
//            adapter.setData(it)
//        })
////        todoViewModel.todoOfTodo.observe(viewLifecycleOwner) {
////            adapter.setTodos(it)
////        }
////        val navHostFragment =  parentFragmentManager.findFragmentById(R.id.nav_todo) as NavHostFragment
////        parentFragmentManager.
////        val navController = navHostFragment.navController
//
////        binding!!.writeIcon.setOnClickListener{
////            view:View->
////            val today = LocalDateTime.now()
////            val todo = Todo(today,today)
////            val action = TodoDialogFragmentDirections.actionTodoDialogFragmentToTodoFragment(todo)
//////            navController.navigate()
//////            findNavController().navigate(action)
////
//////            view.findNavController().navigate(action)
////            navController.navigate(action)
////            val args : TodoFragmentArgs by navArgs()
////            newTodo=args.newTodo
//
////            val intent = Intent(activity, TodoDialogFragment::class.java).apply {
////                putExtra("todo", todo)
////                Log.i("TodoFragment",todo.startDate.toString())
////            }
////            getResultTodo.launch(intent)
//
////        }
//        // Inflate the layout for this fragment
//        return binding!!.root
//
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
////        navController = Navigation.findNavController(view)
////        val action = TodoFragmentDirections.actionTodoFragmentToTodoDialogFragment2(adapter.getTodo())
////        binding!!.
////            view:View->
////            val today = LocalDateTime.now()
////            val todo = Todo(today,today)
////            val action = TodoDialogFragmentDirections.actionTodoDialogFragmentToTodoFragment(todo)
//////            navController.navigate()
//////            findNavController().navigate(action)
////
//////            view.findNavController().navigate(action)
////            navController.navigate(action)
////            val args : TodoFragmentArgs by navArgs()
////            newTodo=args.newTodo
////        }
//    }
//}