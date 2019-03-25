package interview.popularphotos500px

enum class ImageSizing(val id: Int, val value: Int) {
    LONGEST_EDGE_256PX(30, 256),
    LONGEST_EDGE_900PX(4, 900),
    HEIGHT_300PX(20, 300),
    HEIGHT_450PX(31, 450);

    companion object {
        fun getAllImageSizing() : List<ImageSizing> {
            val list: ArrayList<ImageSizing> = arrayListOf<ImageSizing>()

            list.add(LONGEST_EDGE_256PX)
            list.add(LONGEST_EDGE_900PX)
            list.add(HEIGHT_300PX)
            list.add(HEIGHT_450PX)

            return list
        }
    }
}