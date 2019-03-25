package interview.popularphotos500px.data

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import interview.popularphotos500px.BuildConfig
import interview.popularphotos500px.data.Model.PhotosResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosApiService {

    @GET("photos?feature=popular&consumer_key=" + BuildConfig.apiKey + "&image_size=30,31,4,20")
    fun getPopularPhotos(@Query("page") page: Int) : Single<PhotosResponse>

    companion object {
        fun getService(): PhotosApiService {
            val gsonBuilder = GsonBuilder()
            gsonBuilder.serializeNulls()
            gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)

            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.500px.com/v1/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .build()
            return retrofit.create(PhotosApiService::class.java)
        }
    }
}