package interview.popularphotos500px.popular.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import interview.popularphotos500px.popular.ImageSizing
import interview.popularphotos500px.R
import interview.popularphotos500px.data.Model.Image
import interview.popularphotos500px.data.Model.Photo
import kotlinx.android.synthetic.main.photo_row_layout.view.*

class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindTo(photo: Photo?, listener: PagedPhotoAdapter.PhotoListItemListener) {
        // find 256px image by using a specific sizeId
        val image: Image? = photo?.getImageBySizeId(ImageSizing.LONGEST_EDGE_256PX.id)

        if (photo != null) {
            itemView.setOnClickListener { listener.onPhotoListItemClick(photo) }
        }

        // use glide to async download the image from a url
        Glide.with(itemView)
            .load(image!!.url)
            .into(itemView.ivPreviewPhoto);
    }

    companion object {
        fun create(parent: ViewGroup): PhotoViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.photo_row_layout, parent, false)
            return PhotoViewHolder(view)
        }
    }
}