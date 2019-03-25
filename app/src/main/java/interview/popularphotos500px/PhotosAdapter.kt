package interview.popularphotos500px

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import interview.popularphotos500px.data.Model.Image
import interview.popularphotos500px.data.Model.Photo
import kotlinx.android.synthetic.main.photo_row_layout.view.*
import kotlin.math.roundToInt

class PhotosAdapter(private val lstPhoto: ArrayList<Photo>, private val listener: PhotoClickListener) : RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(lstPhoto[position], listener, position)
    }

    override fun getItemCount(): Int = lstPhoto.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_row_layout, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bind(photo: Photo, listener: PhotoClickListener, position: Int) {
            // find 256px image by using a specific sizeId
            val image: Image? = photo.getImageBySizeId(ImageSizing.LONGEST_EDGE_256PX.id)
            val factor = itemView.getContext().getResources().getDisplayMetrics().density

            // set the dimension of the imageView
            itemView.ivPreviewPhoto.layoutParams.width = image!!.width * factor.toInt()
            itemView.ivPreviewPhoto.layoutParams.height = image!!.height * factor.toInt()
            itemView.setOnClickListener{ listener.onPhotoClick(photo) }

            // use glide to async download the image from a url
            Glide.with(itemView)
                .load(image.url)
                .into(itemView.ivPreviewPhoto);
        }
    }

    interface PhotoClickListener {
        fun onPhotoClick(photo: Photo)
    }
}