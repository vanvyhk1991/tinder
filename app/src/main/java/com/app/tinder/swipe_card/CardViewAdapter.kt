package com.app.tinder.swipe_card

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.app.tinder.R
import com.app.tinder.model.Profile
import com.app.tinder.model.StatusMove
import com.bumptech.glide.Glide


@Suppress("UNREACHABLE_CODE")
class CardViewAdapter(private val context: Context, private val profiles: MutableList<Profile>) :
    ArrayAdapter<Profile>(context, R.layout.tinder_card_view, profiles) {

    class ItemCardViewHolder {
        var imageView: ImageView? = null
        var location: TextView? = null
        var nameAge: TextView? = null
        var textAccept: TextView? = null
        var textReject: TextView? = null
    }

    fun setItemData(data: MutableList<Profile>){
        profiles.clear()
        profiles.addAll(data)
        notifyDataSetChanged()
    }

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val context = parent.context
        var listItem: View? = convertView
        val viewHolder: ItemCardViewHolder
        if (listItem == null) {
            listItem =
                LayoutInflater.from(context).inflate(R.layout.tinder_card_view, parent, false)
            viewHolder = ItemCardViewHolder()
            listItem?.apply {
                viewHolder.imageView = findViewById<ImageView>(R.id.profileImageView)
                viewHolder.location = findViewById<TextView>(R.id.locationNameTxt)
                viewHolder.nameAge = findViewById<TextView>(R.id.nameAgeTxt)
                viewHolder.textAccept = findViewById<TextView>(R.id.tv_accept)
                viewHolder.textReject = findViewById<TextView>(R.id.tv_reject)
            }

            listItem.tag = viewHolder
        } else {
            viewHolder = (listItem.tag as ItemCardViewHolder)
        }
        listItem?.apply {
            val itemProfile = getItem(position)

            Glide.with(context).load(itemProfile?.url)
                .into(viewHolder.imageView!!)

            viewHolder.location?.text = itemProfile?.location

            viewHolder.nameAge?.text = "${itemProfile?.name} ${itemProfile?.age}"

            Log.e("xxx", "itemProfile?.statusMove: ${itemProfile?.statusMove}")
            when (itemProfile?.statusMove) {
                StatusMove.LEFT -> {
                    viewHolder.textAccept?.visibility = View.VISIBLE
                    viewHolder.textReject?.visibility = View.GONE
                }

                StatusMove.RIGHT -> {
                    viewHolder.textAccept?.visibility = View.VISIBLE
                    viewHolder.textReject?.visibility = View.GONE
                }

                else -> {
                    viewHolder.textAccept?.visibility = View.GONE
                    viewHolder.textReject?.visibility = View.GONE
                }
            }
        }

        return listItem!!
    }
}