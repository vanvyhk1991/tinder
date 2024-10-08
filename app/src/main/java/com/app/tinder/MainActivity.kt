package com.app.tinder

import android.content.res.Resources.Theme
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.app.tinder.databinding.ActivityMainBinding
import com.app.tinder.dialog.BoltDialogFragment
import com.app.tinder.dialog.StarDialogFragment
import com.app.tinder.model.Profile
import com.app.tinder.model.StatusMove
import com.app.tinder.swipe_card.CardViewAdapter
import com.app.tinder.swipe_card.SwipeFlingAdapterView

class MainActivity : AppCompatActivity() {
    private val profiles = mutableListOf<Profile>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Utils.loadProfiles(this)?.let { profiles.addAll(it) }
        binding.apply {
            val adapter = CardViewAdapter(this@MainActivity, profiles)
            flingContainer.adapter = adapter
            flingContainer.setFlingListener(object : SwipeFlingAdapterView.onFlingListener {
                override fun removeFirstObjectInAdapter() {
                    profiles.removeFirstOrNull()
                    adapter.notifyDataSetChanged()
                    ivUnlike.setColorImageview(R.color.color_FF1FE9, R.color.color_20262D)
                    ivFavorite.setColorImageview(R.color.white, R.color.color_20262D)
                }

                override fun onLeftCardExit(dataObject: Any?) {
                    Log.e("Xxx", "onLeftCardExit----")
                }

                override fun onRightCardExit(dataObject: Any?) {
                }

                override fun onAdapterAboutToEmpty(itemsInAdapter: Int) {
                    Utils.loadProfiles(this@MainActivity)?.let { profiles.addAll(it) }
                    adapter.notifyDataSetChanged()
                }

                override fun onScroll(scrollProgressPercent: Float) {
                    binding.apply {
                        if (scrollProgressPercent == 0.0f) {
                            ivUnlike.setColorImageview(R.color.color_FF1FE9, R.color.color_20262D)
                            ivFavorite.setColorImageview(R.color.white, R.color.color_20262D)
                        } else if (scrollProgressPercent < 0.0f) {
                            ivUnlike.setColorImageview(R.color.color_20262D, R.color.color_FF1FE9)
                            ivFavorite.setColorImageview(R.color.white, R.color.color_20262D)
                        } else {
                            ivUnlike.setColorImageview(R.color.color_FF1FE9, R.color.color_20262D)
                            ivFavorite.setColorImageview(R.color.color_20262D, R.color.color_53C045)
                        }
                    }

                    Log.e("xxx", "onScroll: ${profiles[0].statusMove}")
                }
            })

            flingContainer.setOnItemClickListener { itemPosition, dataObject ->
                Log.e("xxx", "itemPosition: $itemPosition")
            }

            ivUndo.setOnClickListener {
                Toast.makeText(this@MainActivity, "Nothing", Toast.LENGTH_LONG).show()
            }
            ivStar.setOnClickListener {
                StarDialogFragment().show(
                    supportFragmentManager, StarDialogFragment::class.java.simpleName
                )
            }

            ivBolt.setOnClickListener {
                BoltDialogFragment().show(
                    supportFragmentManager, BoltDialogFragment::class.java.simpleName
                )
            }
            ivUnlike.setOnClickListener {
                ivUnlike.setColorImageview(R.color.color_20262D, R.color.color_FF1FE9)
                binding.flingContainer.topCardListener.selectLeft()
            }

            ivFavorite.setOnClickListener {
                ivFavorite.setColorImageview(R.color.color_20262D, R.color.color_53C045)
                binding.flingContainer.topCardListener.selectRight()
            }
        }
    }


    private fun ImageView.setColorImageview(colorTint: Int, backgroundTint: Int) {
        this.setColorFilter(
            ContextCompat.getColor(this@MainActivity, colorTint),
            android.graphics.PorterDuff.Mode.MULTIPLY
        )
        this.backgroundTintList = resources.getColorStateList(backgroundTint)
    }
}