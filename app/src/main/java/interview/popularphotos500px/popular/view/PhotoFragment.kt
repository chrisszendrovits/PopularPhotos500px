package interview.popularphotos500px.popular.view

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import interview.popularphotos500px.popular.ImageSizing
import interview.popularphotos500px.R
import interview.popularphotos500px.data.Model.Image
import interview.popularphotos500px.data.Model.Photo
import interview.popularphotos500px.popular.PhotoDetailsViewModel
import interview.popularphotos500px.databinding.FragmentPhotoDetailsLayoutBinding
import kotlinx.android.synthetic.main.fragment_photo_details_layout.*

class PhotoFragment : android.support.v4.app.Fragment() {

    protected lateinit var viewModel: PhotoDetailsViewModel;
    protected lateinit var photo: Photo;

    companion object {
        fun newInstance(photo: Photo): android.support.v4.app.Fragment {
            val fragment = PhotoFragment()
            fragment.photo = photo
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var binding : FragmentPhotoDetailsLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_photo_details_layout, container, false)
        var layout = binding.root

        viewModel = ViewModelProviders.of(this, PhotoDetailsViewModel.VMFactory(photo)).get(PhotoDetailsViewModel::class.java)
        binding.viewModel = viewModel

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

        // find 300px image by using a specific sizeId
        val image: Image? = viewModel.getImage(ImageSizing.HEIGHT_300PX)
        val factor = activity!!.getResources().getDisplayMetrics().density
        photo_image_view.layoutParams.height = image!!.height * factor.toInt()

        // use glide to async download the image from a url
        Glide.with(photo_image_view)
            .load(image.url)
            .into(photo_image_view);
    }
}