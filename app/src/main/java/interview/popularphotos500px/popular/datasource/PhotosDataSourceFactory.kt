package interview.popularphotos500px.popular.datasource

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import interview.popularphotos500px.data.Model.Photo
import interview.popularphotos500px.data.PhotosApiService
import io.reactivex.disposables.CompositeDisposable

class PhotosDataSourceFactory(private val apiService: PhotosApiService, private val compositeDisposable: CompositeDisposable) :
    DataSource.Factory<Long, Photo>() {

    val mutableLiveData: MutableLiveData<PhotosDataSource>
    private lateinit var dataSource: PhotosDataSource

    init {
        this.mutableLiveData = MutableLiveData()
    }

    override fun create(): DataSource<Long, Photo> {
        dataSource = PhotosDataSource(apiService, compositeDisposable)
        mutableLiveData.postValue(dataSource)
        return dataSource
    }
}
