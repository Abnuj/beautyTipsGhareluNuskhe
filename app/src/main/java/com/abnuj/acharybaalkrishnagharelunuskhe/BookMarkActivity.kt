package com.abnuj.acharybaalkrishnagharelunuskhe

import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abnuj.acharybaalkrishnagharelunuskhe.RoomDatabase.DataEntitiy
import com.abnuj.acharybaalkrishnagharelunuskhe.databinding.ActivityBookMarkBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@RequiresApi(Build.VERSION_CODES.N)
class BookMarkActivity : AppCompatActivity() {
    lateinit var binding: ActivityBookMarkBinding
    lateinit var bookMarkAdapter: BookMarkAdapter
    var list = mutableListOf<DataEntitiy>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookMarkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getdata()
    }


    private fun getdata() {
        mainAdapter.myscope.launch {
            list = MainActivity.dao?.getAllData() as MutableList<DataEntitiy>

            withContext(Dispatchers.Main) {
                if (list.size >= 1) {
                    bookMarkAdapter = BookMarkAdapter(list)
                    binding.bookMarkRecycler.apply {
                        layoutManager =
                            LinearLayoutManager(this@BookMarkActivity, RecyclerView.VERTICAL, false)
                        this.adapter = bookMarkAdapter
                    }
                } else {
                    val dialog = AlertDialog.Builder(this@BookMarkActivity)
                        .setTitle("You don't have any bookMarks").setPositiveButton(
                            "Dismiss",
                            DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
                        .create().show()
                }

            }
        }
    }
}