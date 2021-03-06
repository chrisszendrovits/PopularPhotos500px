package interview.popularphotos500px.popular.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import interview.popularphotos500px.popular.PopularPhotosViewModel
import interview.popularphotos500px.data.Model.Photo
import interview.popularphotos500px.popular.adapters.PagedPhotoAdapter
import kotlinx.android.synthetic.main.fragment_popular_photos_layout.*
import android.support.v7.widget.StaggeredGridLayoutManager
import interview.popularphotos500px.R

class PopularPhotosFragment : android.support.v4.app.Fragment(), PagedPhotoAdapter.PhotoListItemListener {

    private lateinit var adapterPhotos: PagedPhotoAdapter
    private lateinit var viewModel: PopularPhotosViewModel
    private var layout: View? = null

    companion object {
        fun newInstance(): android.support.v4.app.Fragment {
            return PopularPhotosFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (layout == null) {
            layout = inflater.inflate(R.layout.fragment_popular_photos_layout, container, false) as ConstraintLayout
        }
        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(PopularPhotosViewModel::class.java)
        initAdapter()
    }

    private fun initAdapter() {
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        adapterPhotos = PagedPhotoAdapter(this)

        popular_photos_list.setLayoutManager(StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL))
        popular_photos_list.addItemDecoration(adapterPhotos.getViewItemDecoration())
        popular_photos_list.adapter = adapterPhotos

        viewModel.photoList.observe(this, Observer<PagedList<Photo>> { adapterPhotos.submitList(it) })
    }

    override fun onPhotoListItemClick(photo: Photo) {
        val activity = activity

        // show the photo fragment on photo item clicked
        activity!!.supportFragmentManager
            .beginTransaction()
            .add(R.id.root_frame_layout, PhotoDetailsFragment.newInstance(photo), "photoFragment")
            .addToBackStack(null)
            .commit()
    }
}