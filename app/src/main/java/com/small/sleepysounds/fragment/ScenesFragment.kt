package com.small.sleepysounds.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.small.sleepysounds.R
import com.small.sleepysounds.adapter.ScenesAdapter
import com.small.sleepysounds.model.DataModel

class ScenesFragment(private val width: Int) : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var photoAdapter: ScenesAdapter
    private var dataList = mutableListOf<DataModel>()
    private lateinit var contextParent: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.contextParent = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)
        if (width > 600) {
            recyclerView.layoutManager = GridLayoutManager(context, 3)
        } else {
            recyclerView.layoutManager = GridLayoutManager(context, 2)
        }
        photoAdapter = ScenesAdapter(width)
        recyclerView.adapter = photoAdapter

        if (dataList.isEmpty()) {
            dataList.add(DataModel("Title", R.drawable.bg_beach))
            dataList.add(DataModel("Title", R.drawable.bg_beach))
            dataList.add(DataModel("Title", R.drawable.bg_beach))
            dataList.add(DataModel("Title", R.drawable.bg_beach))
            dataList.add(DataModel("Title", R.drawable.bg_beach))
            dataList.add(DataModel("Title", R.drawable.bg_beach))
            dataList.add(DataModel("Title", R.drawable.bg_beach))
            dataList.add(DataModel("Title", R.drawable.bg_beach))
            dataList.add(DataModel("Title", R.drawable.bg_beach))
            dataList.add(DataModel("Title", R.drawable.bg_beach))
            dataList.add(DataModel("Title", R.drawable.bg_beach))
            dataList.add(DataModel("Title", R.drawable.bg_beach))
            dataList.add(DataModel("Title", R.drawable.bg_beach))
            dataList.add(DataModel("Title", R.drawable.bg_beach))
            dataList.add(DataModel("Title", R.drawable.bg_beach))
            dataList.add(DataModel("Title", R.drawable.bg_beach))
            dataList.add(DataModel("Title", R.drawable.bg_beach))
        }
        photoAdapter.setDataList(dataList)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_scenes, container, false)
    }
}