package de.taskmaster.activity.util.fragment

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import de.taskmaster.R
import de.taskmaster.activity.app.AppActivity

open class SubFragment<T : ViewDataBinding>(private val layoutResourceId: Int, private val menuId: Int? = R.menu.save) : SavableFragment() {

    private val RESULT_LOAD_IMAGE = 1

    lateinit var binder: T

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as AppActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            show()
        }
        binder = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)
        setHasOptionsMenu(true)
        return binder.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        (activity as AppActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.arrow_back)
        if (menuId != null) {
            inflater.inflate(menuId, menu)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == AppCompatActivity.RESULT_OK && null != data) {
            val selectedImage = data.data
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            val cursor = requireActivity().contentResolver.query(selectedImage!!, filePathColumn, null, null, null)
            cursor!!.moveToFirst()
            val columnIndex = cursor.getColumnIndex(filePathColumn[0])
            val picturePath = cursor.getString(columnIndex)
            cursor.close()
            binder.root.findViewById<ImageView>(R.id.image).setImageBitmap(BitmapFactory.decodeFile(picturePath))
        }
    }

}