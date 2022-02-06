package kr.co.company.myproject.fragment

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.company.myproject.adapter.CategoryAdapter
import kr.co.company.myproject.databinding.FragmentTodoBinding
import kr.co.company.myproject.viewModel.CategoryViewModel
import kr.co.company.myproject.viewModel.TodoViewModel
import java.time.LocalDate
import java.time.LocalDateTime

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TodoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TodoFragment : Fragment() {
    private var binding : FragmentTodoBinding?=null
    private val categoryViewModel : CategoryViewModel by viewModels()
    private val todoViewModel : TodoViewModel by viewModels()
    private val adapter : CategoryAdapter by lazy { CategoryAdapter(requireContext(),0, viewLifecycleOwner,categoryViewModel,todoViewModel) }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentTodoBinding.inflate(inflater,container,false)
        adapter.setHasStableIds(true)

        binding!!.categoryRecycle.layoutManager = LinearLayoutManager(activity,
            LinearLayoutManager.VERTICAL,false)
        binding!!.categoryRecycle.adapter=adapter

        categoryViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            categoryViewModel.readAllDateCategory(LocalDate.now())
        })
        categoryViewModel.readAllDateCategory.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })
        binding!!.calendarView.setOnDateChangeListener { calendarView, y, m, d ->
            val date = LocalDate.of(y,m+1,d)
            categoryViewModel.readAllDateCategory((date?: LocalDate.now()) as LocalDate)
        }
        return binding!!.root
    }
    override fun onDestroyView() {
        binding = null

        super.onDestroyView()
    }
}