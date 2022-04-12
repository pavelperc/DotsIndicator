package com.example.dotsindicator

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout.TabView
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = PagerAdapter()
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//            tab.setIcon(ColorDrawable(Color.RED))
//            tab.setIcon(R.drawable.tab_indicator_dot_simple)
//            tab.icon?.applyApi21Tint(this, R.color.black_30)
//            tab.view.background?.mutate()?.setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY)
//            tab.view.setBackgroundResource(R.drawable.tab_indicator_dot_ring)
        }.attach()

    }

    inner class PagerAdapter : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            TextView(this@MainActivity).apply {
                textSize = 120f
                gravity = Gravity.CENTER
                text = viewType.toString()
                layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            }.let { object : ViewHolder(it) {} }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {}

        override fun getItemCount(): Int = 2
        override fun getItemViewType(position: Int): Int = position
    }

    fun Drawable.applyApi21Tint(context: Context, @ColorRes colorRes: Int) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {
            mutate().setColorFilter(ContextCompat.getColor(context, colorRes), PorterDuff.Mode.MULTIPLY)
        }
    }

    fun View.setBackgroundTintCompat(@ColorInt color: Int) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            ViewCompat.setBackgroundTintList(this, ColorStateList.valueOf(color))
        } else {
            background?.mutate()?.setColorFilter(color, PorterDuff.Mode.MULTIPLY)
        }
    }
}