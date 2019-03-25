package interview.popularphotos500px.popular

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import interview.popularphotos500px.data.Model.Photo
import interview.popularphotos500px.data.PhotosApiService
import interview.popularphotos500px.popular.datasource.PhotosDataSourceFactory
import io.reactivex.disposables.CompositeDisposable

class PopularPhotosViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val sourceFactory: PhotosDataSourceFactory
    private val pageSize = 20

    var photoList: LiveData<PagedList<Photo>>

    init {
        sourceFactory = PhotosDataSourceFactory(
            PhotosApiService.getService(),
            compositeDisposable
        )

        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()

        photoList = LivePagedListBuilder<Long, Photo>(sourceFactory, config).build()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}

