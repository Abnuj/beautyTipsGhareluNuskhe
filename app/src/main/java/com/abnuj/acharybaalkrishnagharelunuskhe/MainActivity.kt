package com.abnuj.acharybaalkrishnagharelunuskhe

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abnuj.acharybaalkrishnagharelunuskhe.RoomDatabase.Dao
import com.abnuj.acharybaalkrishnagharelunuskhe.RoomDatabase.FavDatabase
import com.abnuj.acharybaalkrishnagharelunuskhe.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.N)
class MainActivity : AppCompatActivity() {
    companion object {
        var toolbar: View? = null
        var bottomco: LinearLayout? = null
        var dao: Dao? = null
        const val playstoreUrl = "https://play.google.com/store/apps/details?id="
    }

    lateinit var binding: ActivityMainBinding
    var drawableNameList = arrayListOf<Int>()
    var headinglist = mutableListOf<HeadingDetails>()
    var finalItemList = mutableListOf<display_item>()
    lateinit var finalAdapter: mainAdapter
    lateinit var headingarray: List<String>
    var imageArray = mutableListOf<Drawable>()
    lateinit var detailsArray: List<String>
    lateinit var heading: HeadingDetailsRecyclerAdapter
    var i = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getDatafromResourse()
        toolbar = findViewById(R.id.toolbar)
        bottomco = findViewById(R.id.bottomco)
        setUserClickListener()
//        toolbar = binding.toolbar
//        heading = HeadingDetailsRecyclerAdapter(headinglist)
        finalAdapter = mainAdapter(finalItemList)
        binding.itemRecycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            this.adapter = finalAdapter
        }

        setScrollListener()
        getDao()
    }

    private fun setScrollListener() {
        binding.itemRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    //Scrolling down
                    Log.d("TAG", "onScrolled: Upword scroll ")
                    if (MainActivity.bottomco?.visibility == View.VISIBLE) {
                        MainActivity.bottomco?.visibility = View.GONE
                        MainActivity.toolbar?.visibility = View.GONE

                    }

                } else if (dy < 0) {
                    //Scrolling up
                    Log.d("TAG", "onScrolled: Downword scroll ")
                    if (MainActivity.bottomco!!.visibility == View.GONE) {
                        MainActivity.bottomco!!.visibility = View.VISIBLE
                        MainActivity.toolbar!!.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun getImageFromDrawable() {
        for (i in 0..detailsArray.size - 1) {
            val drawable = when (i) {
                0 -> ResourcesCompat.getDrawable(resources, R.drawable.one, theme)
                1 -> ResourcesCompat.getDrawable(resources, R.drawable.two, theme)
                2 -> ResourcesCompat.getDrawable(resources, R.drawable.three, theme)
                3 -> ResourcesCompat.getDrawable(resources, R.drawable.four, theme)
                4 -> ResourcesCompat.getDrawable(resources, R.drawable.five, theme)
                5 -> ResourcesCompat.getDrawable(resources, R.drawable.six, theme)
                6 -> ResourcesCompat.getDrawable(resources, R.drawable.seven, theme)
                7 -> ResourcesCompat.getDrawable(resources, R.drawable.eight, theme)
                8 -> ResourcesCompat.getDrawable(resources, R.drawable.nine, theme)
                9 -> ResourcesCompat.getDrawable(resources, R.drawable.ten, theme)
                10 -> ResourcesCompat.getDrawable(resources, R.drawable.eleven, theme)
                11 -> ResourcesCompat.getDrawable(resources, R.drawable.tweleve, theme)
                12 -> ResourcesCompat.getDrawable(resources, R.drawable.thirteen, theme)
                13 -> ResourcesCompat.getDrawable(resources, R.drawable.fourteen, theme)
                14 -> ResourcesCompat.getDrawable(resources, R.drawable.fifteen, theme)
                15 -> ResourcesCompat.getDrawable(resources, R.drawable.sixteen, theme)
                16 -> ResourcesCompat.getDrawable(resources, R.drawable.seventeen, theme)
                17 -> ResourcesCompat.getDrawable(resources, R.drawable.eighteen, theme)
                else -> ResourcesCompat.getDrawable(resources, R.drawable.one, theme)
            }
            imageArray.add(i, drawable!!)
        }
    }

    private fun getImageName() {
        for (i in 0..detailsArray.size - 1) {
            val drawableName: Int = when (i) {
                0 -> R.drawable.one
                1 -> R.drawable.two
                2 -> R.drawable.three
                3 -> R.drawable.four
                4 -> R.drawable.five
                5 -> R.drawable.six
                6 -> R.drawable.seven
                7 -> R.drawable.eight
                8 -> R.drawable.nine
                9 -> R.drawable.ten
                10 -> R.drawable.eleven
                11 -> R.drawable.tweleve
                12 -> R.drawable.thirteen
                13 -> R.drawable.fourteen
                14 -> R.drawable.fifteen
                15 -> R.drawable.sixteen
                16 -> R.drawable.seventeen
                17 -> R.drawable.eighteen
                else -> R.drawable.one
            }
            drawableNameList.add(drawableName)
        }
    }

    private fun getDatafromResourse() {

        headingarray = resources.getStringArray(R.array.heading).toList()
        detailsArray = resources.getStringArray(R.array.details).toList()
        getImageFromDrawable()
        getImageName()

        headingarray.forEach {
            headinglist.add(HeadingDetails(it, detailsArray[i]))
            i++
        }

        for (i in 0..headinglist.size - 1) {
            finalItemList.add(
                    i,
                    display_item(
                            drawable = imageArray[i],
                            drawableName = drawableNameList[i],
                            headingDetails = HeadingDetails(headingarray[i], detailsArray[i])
                    )
            )
        }
    }

    fun setUserClickListener() {
        binding.mainProfileImg.setOnClickListener {
            AlertDialog.Builder(this).setView(layoutInflater.inflate(R.layout.information_layout, null)).create().show()
        }
        binding.mainBookmarkImg.setOnClickListener {
            startActivity(Intent(this, BookMarkActivity::class.java))
        }

        binding.mainStarImg.setOnClickListener {
            val view2: View = layoutInflater.inflate(R.layout.rating_dialog, null)
            val builder = AlertDialog.Builder(this)
            val dialog = builder.setView(view2).create()
            val notnow = view2.findViewById<TextView>(R.id.notNow)
            val ratenow = view2.findViewById<TextView>(R.id.rateNow)

            dialog.show()

            notnow.setOnClickListener {
                dialog.dismiss()
            }

            ratenow?.setOnClickListener {
                dialog.dismiss()
                val uri = Uri.parse(playstoreUrl + application.packageName)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                val createChooser: Intent = Intent.createChooser(intent, "Rate App 5 star ")
                startActivity(createChooser)
            }
        }
    }

    fun getDao() {
        mainAdapter.myscope.launch {
            dao = FavDatabase.getDatabase(applicationContext).dao
        }
    }

}