package interview.popularphotos500px.popular.datasource

import android.arch.paging.ItemKeyedDataSource
import interview.popularphotos500px.data.Model.Photo
import interview.popularphotos500px.data.PhotosApiService
import io.reactivex.disposables.CompositeDisposable

class PhotosDataSource(private val apiService: PhotosApiService,
                       private val compositeDisposable: CompositeDisposable) : ItemKeyedDataSource<Long, Photo>() {

    private var page: Int = 1

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Photo>) {
        // get the initial set of photos from the api
        compositeDisposable.add(apiService.getPopularPhotos(page++)
            .subscribe({ response -> callback.onResult(response.photos) }, { _ -> }))
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Photo>) {
        // get all subsequent sets of photo pages
        compositeDisposable.add(apiService.getPopularPhotos(page++)
            .subscribe({ response -> callback.onResult(response.photos) }, { _ -> }))
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Photo>) { }

    override fun getKey(item: Photo): Long {
        return item.id.toLong()
    }
}