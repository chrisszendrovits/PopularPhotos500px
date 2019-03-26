package interview.popularphotos500px.popular.adapters

import android.arch.paging.PagedListAdapter
import android.graphics.Rect
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.view.ViewGroup
import interview.popularphotos500px.data.Model.Photo

class PagedPhotoAdapter(private val listener: PhotoListItemListener) : PagedListAdapter<Photo, RecyclerView.ViewHolder>(PhotoDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PhotoViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PhotoViewHolder).bindTo(getItem(position), listener)
    }

    fun getViewItemDecoration(): RecyclerView.ItemDecoration {
        return object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
                super.getItemOffsets(outRect, view, parent, state)

                val layoutParams = view.layoutParams as StaggeredGridLayoutManager.LayoutParams

                val spanIndex = layoutParams.spanIndex
                val index = parent.getChildLayoutPosition(view)
                val itemCount = parent.adapter.itemCount

                when (spanIndex) {
                    0 -> {
                        outRect.left = 16
                        outRect.right = 8
                    }
                    1 -> {
                        outRect.left = 8
                        outRect.right = 16
                    }
                }

                if (index < 2) {
                    outRect.top = 16
                    outRect.bottom = 8
                } else if (index == itemCount - 1) {
                    outRect.top = 8
                    outRect.bottom = 16
                } else if (index == itemCount - 2 && layoutParams.isFullSpan) {
                    outRect.top = 8
                    outRect.bottom = 16
                } else {
                    outRect.top = 8
                    outRect.bottom = 8
                }
            }
        }
    }

    interface PhotoListItemListener {
        fun onPhotoListItemClick(photo: Photo)
    }

    companion object {
        val PhotoDiffCallback = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem == newItem
            }
        }
    }
}