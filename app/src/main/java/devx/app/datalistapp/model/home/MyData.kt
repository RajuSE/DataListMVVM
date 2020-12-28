package devx.app.datalistapp.model.home

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MyData(
    val dataImage: String,
    val dataTitle: String,
    val dataText: String
) : Parcelable