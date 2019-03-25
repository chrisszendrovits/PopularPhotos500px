package interview.popularphotos500px

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import interview.popularphotos500px.data.Model.Photo
import kotlinx.android.synthetic.main.photo_row_layout.view.*

class PhotosAdapter(private val lstData: ArrayList<Photo>) : RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(lstData[position], position)
    }

    override fun getItemCount(): Int = lstData.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_row_layout, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bind(photo: Photo, position: Int) {

            // use glide to async download the image using url
            Glide.with(itemView)
                .load(photo.images.get(0).url)
                .into(itemView.ivPreviewPhoto);
        }
    }
}