package interview.popularphotos500px.data.Model

import interview.popularphotos500px.ImageSizing

data class Photo(var name: String,
                 var description: String,
                 var width: Int,
                 var height: Int,
                 var createdAt: String,
                 var images: List<Image>) {

    public fun getImageBySizeId(sizeId: Int): Image? {
        var imageSizing: ImageSizing? = null

        for (imgSize in ImageSizing.getAllImageSizing()) {
            if (imgSize.id == sizeId) {
                imageSizing = imgSize
                break;
            }
        }

        var image: Image? = null

        for (img in images) {
            if (img.size == sizeId) {
                image = img
                break
            }
        }

        if (image != null && imageSizing != null) {
            // calculate the image dimensions
            if (width == imageSizing.value) {
                image.width = imageSizing.value
                image.height = (imageSizing.value * height) / width
            }
            else {
                image.height = imageSizing.value
                image.width = (imageSizing.value * width) / height
            }
        }
        return image
    }
}