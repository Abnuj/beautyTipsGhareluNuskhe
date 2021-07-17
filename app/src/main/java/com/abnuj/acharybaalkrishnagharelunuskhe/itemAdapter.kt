package com.abnuj.acharybaalkrishnagharelunuskhe

import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.abnuj.acharybaalkrishnagharelunuskhe.RoomDatabase.DataEntitiy
import kotlinx.coroutines.*
import kotlin.random.Random

@RequiresApi(Build.VERSION_CODES.N)
class mainAdapter(var list: List<display_item>) : RecyclerView.Adapter<mainAdapter.viewholder>() {
    companion object {
        val myscope = CoroutineScope(Dispatchers.IO + CoroutineName("favCor"))
    }

    class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindViews(current: display_item) {
            val randomnumber = Random.nextInt(0, 7)
            val drawable = backgroundArray[randomnumber]
            MainActivity.toolbar?.background = toolbarBackground[randomnumber]
            MainActivity.bottomco?.background = toolbarBackground[randomnumber]
            background.background = drawable
            imageview.setImageDrawable(current.drawable)
            bookmarbtn.setImageDrawable(itemView.resources.getDrawable(R.drawable.bookmark_off, itemView.context.theme))
            headingtv.setText(Html.fromHtml(current.headingDetails.heading, 0))
            detailstv.setText(Html.fromHtml(current.headingDetails.details, 0))
        }

        fun savetoDb(current: display_item) {
            myscope.launch {
                MainActivity.dao?.insertData(
                        DataEntitiy(
                                imageName = current.drawableName,
                                heading = current.headingDetails.heading,
                                details = current.headingDetails.details
                        )
                )
                withContext(Dispatchers.Main) {
                    bookmarbtn.setImageDrawable(itemView.resources.getDrawable(
                            R.drawable.bookmark_on, itemView.context.theme
                    ))
                    Toast.makeText(
                            itemView.context,
                            "Data Saved in Bookmarks...",
                            Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        var backgroundArray: List<Drawable?> = listOf(
                ResourcesCompat.getDrawable(
                        itemView.resources,
                        R.drawable.home_gradient,
                        itemView.context.theme
                ),
                ResourcesCompat.getDrawable(
                        itemView.resources,
                        R.drawable.home_green_gradient,
                        itemView.context.theme
                ),
                ResourcesCompat.getDrawable(
                        itemView.resources,
                        R.drawable.login_btn_bg_gradient,
                        itemView.context.theme
                ),
                ResourcesCompat.getDrawable(
                        itemView.resources,
                        R.drawable.login_btn_select_background,
                        itemView.context.theme
                ),
                ResourcesCompat.getDrawable(
                        itemView.resources,
                        R.drawable.login_screen_bg_gradient,
                        itemView.context.theme
                ),
                ResourcesCompat.getDrawable(
                        itemView.resources,
                        R.drawable.red_gradient,
                        itemView.context.theme
                ),
                ResourcesCompat.getDrawable(
                        itemView.resources,
                        R.drawable.slider_green_gradient,
                        itemView.context.theme
                )
        )
        var toolbarBackground: List<Drawable?> = listOf(
                ResourcesCompat.getDrawable(
                        itemView.resources,
                        R.drawable.toolbar_home_gradient,
                        itemView.context.theme
                ),
                ResourcesCompat.getDrawable(
                        itemView.resources,
                        R.drawable.toolbar_green_gradient,
                        itemView.context.theme
                ),
                ResourcesCompat.getDrawable(
                        itemView.resources,
                        R.drawable.toolbar_login_btn_bg_gradient,
                        itemView.context.theme
                ),
                ResourcesCompat.getDrawable(
                        itemView.resources,
                        R.drawable.toolbar_login_btn_select_background,
                        itemView.context.theme
                ),
                ResourcesCompat.getDrawable(
                        itemView.resources,
                        R.drawable.toolbar_login_screen_bg_gradient,
                        itemView.context.theme
                ),
                ResourcesCompat.getDrawable(
                        itemView.resources,
                        R.drawable.toolbar_red_gradient,
                        itemView.context.theme
                ),
                ResourcesCompat.getDrawable(
                        itemView.resources,
                        R.drawable.toolbar_green_gradient,
                        itemView.context.theme
                )
        )
        var imageview = itemView.findViewById<ImageView>(R.id.diseaseImg)
        var headingtv = itemView.findViewById<TextView>(R.id.titleTv)
        var detailstv = itemView.findViewById<TextView>(R.id.finalDetailsTv)
        var background = itemView.findViewById<LinearLayout>(R.id.layoutBackground)
        var bookmarbtn = itemView.findViewById<ImageView>(R.id.bookmarkBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val current = list[position]
        holder.bindViews(current)
        holder.bookmarbtn.setOnClickListener {
            holder.savetoDb(current)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}