package devx.app.datalistapp.base

import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import com.bumptech.glide.Glide
import devx.app.datalistapp.R

fun ImageView.setImage(imageUrl: String?) {

    if (!imageUrl.isNullOrEmpty()) {

        Glide.with(context).load(imageUrl).centerCrop()
            .placeholder(R.color.placeholder_grey_light)
            .into(this)
    } else {
        setImageDrawable(AppCompatResources.getDrawable(context, R.color.placeholder_grey_light))
    }


}