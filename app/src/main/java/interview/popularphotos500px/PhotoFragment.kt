package interview.popularphotos500px

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import interview.popularphotos500px.data.Model.Image
import interview.popularphotos500px.data.Model.Photo
import kotlinx.android.synthetic.main.fragment_photo_layout.*
import java.text.SimpleDateFormat

class PhotoFragment : android.support.v4.app.Fragment() {

    private var layout: View? = null
    public lateinit var photo: Photo

    companion object {
        fun newInstance(photo: Photo): android.support.v4.app.Fragment {
            val fragment = PhotoFragment()
            fragment.photo = photo
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (layout == null) {
            layout = inflater.inflate(R.layout.fragment_photo_layout, container, false) as ConstraintLayout
        }
        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back_button.setOnClickListener(View.OnClickListener {
            // pop photo fragment off the backstack on back button clicked
            val activity = activity
            activity!!.supportFragmentManager
                .popBackStackImmediate()
        })

        val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ddZ").parse(photo.createdAt)

        photo_date_text_view.text = SimpleDateFormat("MMM d, yyyy").format(date)
        photo_name_text_view.text = photo.name

        // find 300px image by using a specific sizeId
        val image: Image? = photo.getImageBySizeId(ImageSizing.HEIGHT_300PX.id)
        val factor = activity!!.getResources().getDisplayMetrics().density
        photo_image_view.layoutParams.height = image!!.height * factor.toInt()

        // use glide to async download the image from a url
        Glide.with(photo_image_view)
            .load(image!!.url)
            .into(photo_image_view);
    }
}