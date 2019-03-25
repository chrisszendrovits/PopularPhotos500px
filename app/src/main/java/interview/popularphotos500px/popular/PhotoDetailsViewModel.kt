package interview.popularphotos500px.popular

import android.arch.lifecycle.ViewModel
import interview.popularphotos500px.data.Model.Photo
import android.arch.lifecycle.ViewModelProvider
import android.databinding.Bindable
import interview.popularphotos500px.data.Model.Image
import interview.popularphotos500px.data.Model.User
import java.text.SimpleDateFormat

data class PhotoDetailsViewModel(private val photo: Photo) : ViewModel() {

    @Bindable
    public fun getPhotoName(): String { return photo.name }

    @Bindable
    public fun getUser(): User { return photo.user }

    @Bindable
    public fun getUploadedDate(): String {
        val date = SimpleDateFormat("yyyy-MM-dd").parse(photo.createdAt)
        return SimpleDateFormat("MMM d, yyyy").format(date)
    }

    @Bindable
    public fun getVotesCount(): Int { return photo.votesCount }

    public fun getImage(imageSizing: ImageSizing): Image? {
        return photo.getImageBySizeId(imageSizing.id)
    }

    public class VMFactory(private val photo: Photo) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PhotoDetailsViewModel(photo) as T
        }
    }
}

