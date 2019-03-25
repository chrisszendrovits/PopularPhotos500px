package interview.popularphotos500px

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.GsonBuilder
import interview.popularphotos500px.data.Model.Photo
import interview.popularphotos500px.data.Model.PhotosResponse
import interview.popularphotos500px.data.PhotosApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_popular_photos_layout.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PopularPhotosFragment : android.support.v4.app.Fragment() {

    private var adapterPhotos: PhotosAdapter? = null
    private var apiCompositeDisposable: CompositeDisposable? = null
    private var lstPhoto: ArrayList<Photo>? = null
    private val BASE_URL = "https://api.500px.com/v1/"
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

        apiCompositeDisposable = CompositeDisposable()
        initRecyclerView()
        getPhotoData()
    }

    private fun initRecyclerView() {
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        popular_photos_list.layoutManager = layoutManager
    }

    private fun getPhotoData() {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.serializeNulls()
        val factory = GsonConverterFactory.create(gsonBuilder.create())

        val requestBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(factory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(PhotosApiService::class.java)

        apiCompositeDisposable?.add(
            requestBuilder.getPopularPhotos()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse)
        )
    }

    private fun handleResponse(response: PhotosResponse) {
        lstPhoto = ArrayList(response.photos)
        adapterPhotos = PhotosAdapter(lstPhoto!!)
        popular_photos_list.adapter = adapterPhotos
    }

    override fun onDestroy() {
        super.onDestroy()
        apiCompositeDisposable?.clear()
    }
}