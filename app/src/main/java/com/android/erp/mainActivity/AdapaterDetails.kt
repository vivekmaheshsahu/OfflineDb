package com.android.erp.mainActivity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.erp.R
import com.android.erp.database.model.EmpModelClass

class AdapaterDetails() : RecyclerView.Adapter<AdapaterDetails.ViewHolder>() {
    var mContext: Context? = null
    var formId = ""
    private lateinit var mOnItemClickListener: OnItemClickListener
    var mWomenList: List<EmpModelClass>? = null

    constructor(
        mContext: Context,
        mWomenList: List<EmpModelClass>,
        mOnItemClickListener: OnItemClickListener
    ) : this() {
        this.mContext = mContext
        this.mWomenList = mWomenList
        this.mOnItemClickListener = mOnItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(uniqueId: String, name: String, flag: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        holder.bindData(mWomenList!![i])
    }

    override fun getItemCount(): Int {
        return mWomenList!!.size
    }

    fun swapDataList(womenList: List<EmpModelClass>) {
        mWomenList = womenList
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewName: TextView
        var imageEdit: ImageView
        var imageDelete: ImageView
        fun bindData(listModel: EmpModelClass?) {
            if (listModel != null) {
                textViewName.text = listModel.name
                imageEdit.setOnClickListener {
                    mOnItemClickListener.onItemClick(listModel.name, listModel.mobile, "1")
                }
                imageDelete.setOnClickListener {
                    mOnItemClickListener.onItemClick(listModel.name, listModel.mobile, "2")
                }
            }
        }

        init {
            textViewName = itemView.findViewById(R.id.tvEmail)
            imageEdit = itemView.findViewById(R.id.ivEdit)
            imageDelete = itemView.findViewById(R.id.ivDelete)
        }
    }

}
