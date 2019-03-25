package interview.popularphotos500px.data.Model

data class PhotosResponse(var currentPage: Int, var totalPages: Int, var totalItems: Int, var photos: List<Photo>)