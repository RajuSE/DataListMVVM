package devx.app.datalistapp.ui.home


import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import devx.app.datalistapp.R
import devx.app.datalistapp.base.setImage
import devx.app.datalistapp.databinding.ItemDataListBinding
import devx.app.datalistapp.model.home.MyData

@BindingAdapter("image")
fun setImage(image: ImageView, imageUrl: String?) {
    image.setImage(imageUrl)
}

class HomeAdapter(val action: (String) -> Unit, private val items: MutableList<MyData>) :
    RecyclerView.Adapter<HomeAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataBindingUtil.inflate<ItemDataListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_data_list,
            parent,
            false
        ).let { MainViewHolder(it) }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) =
        holder.bind(items[position])

    inner class MainViewHolder(private val binding: ItemDataListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MyData) {
            binding.apply {
                myData = item
                executePendingBindings()
                root.setOnClickListener { action(item.dataText) }
            }
        }
    }
}