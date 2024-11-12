package com.example.doctorapp.moduleDoctor.presentation.doctorHomeContainer.working

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doctorapp.R
import com.example.doctorapp.data.model.DoctorShift
import com.example.doctorapp.databinding.FragmentWorkingCategoryBinding
import com.example.doctorapp.domain.core.base.BaseFragment
import com.example.doctorapp.moduleDoctor.presentation.adapter.DoctorShiftAdapter
import com.example.doctorapp.utils.DateUtils
import com.example.doctorapp.utils.Define
import com.example.doctorapp.utils.Response
import com.example.doctorapp.utils.Utils

class WorkingCategoryFragment :
    BaseFragment<FragmentWorkingCategoryBinding, WorkingCategoryViewModel>(R.layout.fragment_working_category),
    DoctorShiftAdapter.OnShiftClickListener {

    companion object {
        fun newInstance(category: String): WorkingCategoryFragment {
            val fragment = WorkingCategoryFragment()
            val bundle = Bundle()
            bundle.putString(Define.Fields.CATEGORY, category)
            fragment.arguments = bundle
            return fragment
        }
    }

    private val viewModel: WorkingCategoryViewModel by viewModels()
    override fun getVM() = viewModel
    private var tab: String = Define.WorkingTab.REGISTER_NEW_SHIFT
    private var listShift: Response.Success<List<DoctorShift>> = Response.Success(listOf())
    private val shiftAdapter by lazy {
        DoctorShiftAdapter(requireContext(), arguments?.getString(Define.Fields.CATEGORY)!!)
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        tab = arguments?.getString(Define.Fields.CATEGORY).toString()
        viewModel.getListShiftToRegister()
        shiftAdapter.setOnShiftClickListener(this)
        binding.apply {
            rvShift.adapter = shiftAdapter
            rvShift.layoutManager = LinearLayoutManager(requireContext())
            rvShift.itemAnimator = null

        }
    }

    override fun setOnClick() {
        super.setOnClick()
        binding.apply {
            tvSelectAll.setOnClickListener {
                //change text base on isSelectAll
                if (!viewModel.isSelectedAll.value!!) {
                    binding.tvSelectAll.text = getString(R.string.string_clear_all)
                    viewModel.selectAllShift(tab)
                } else {
                    binding.tvSelectAll.text = getString(R.string.string_select_all)
                    viewModel.clearAllShift(tab)
                }
                viewModel.setSelectAll(!viewModel.isSelectedAll.value!!)
            }
        }
    }

    override fun bindingStateView() {
        super.bindingStateView()
        viewModel.apply {
            shiftListResponse.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is Response.Success -> {
                        listShift = response
                        binding.apply {
                            tvFromDate.text = String.format(getString(R.string.string_from_date),
                                listShift.data[0].startTime.let { DateUtils.convertInstantToDate(it) })
                            tvToDate.text = String.format(
                                getString(R.string.string_to_date),
                                listShift.data[listShift.data.size - 1]
                                    .let { DateUtils.convertInstantToDate(it.startTime) })
                            if (listShift.data.all { shift -> shift.isRegistered }) {
                                viewModel.setSelectAll(true)
                            } else {
                                viewModel.setSelectAll(false)
                            }
                            shiftAdapter.submitList(listShift.data)
                            shiftAdapter.notifyDataSetChanged()
                        }
                    }

                    is Response.Error -> {
                        Utils.showSnackBar(response.exception.toString(), binding.root)
                    }

                    is Response.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.rvShift.visibility = View.GONE
                    }
                }

            }

            isSelectedAll.observe(viewLifecycleOwner) {
                binding.tvSelectAll.text =
                    if (it) getString(R.string.string_clear_all) else getString(R.string.string_select_all)
            }
            isLoadingComplete.observe(viewLifecycleOwner) {
                when (it) {
                    true -> {
                        binding.progressBar.visibility = View.GONE
                        binding.rvShift.visibility = View.VISIBLE
                    }

                    false -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.rvShift.visibility = View.GONE
                    }
                }
            }
        }


    }


    override fun onShiftClick(doctorShift: DoctorShift) {
        viewModel.selectShift(doctorShift)
    }

    private fun initShiftList() {
        viewModel.getListShiftToRegister()
    }
}