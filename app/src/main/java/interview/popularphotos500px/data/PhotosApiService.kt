package interview.popularphotos500px.data

import interview.popularphotos500px.BuildConfig
import interview.popularphotos500px.data.Model.PhotosResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface PhotosApiService {

    @GET("photos?feature=popular&consumer_key=" + BuildConfig.apiKey + "&image_size=30,31,4,20")
    fun getPopularPhotos() : Observable<PhotosResponse>
}