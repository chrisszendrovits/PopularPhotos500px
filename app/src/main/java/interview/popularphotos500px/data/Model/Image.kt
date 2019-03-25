package interview.popularphotos500px.data.Model

data class Image(var url: String, var format: String, var size: Int) {
    var width: Int = 0
    var height: Int = 0
}