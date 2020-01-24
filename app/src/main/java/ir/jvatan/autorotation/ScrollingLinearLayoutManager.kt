package ir.jvatan.autorotation

import android.content.Context
import android.graphics.PointF
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSmoothScroller
import android.support.v7.widget.RecyclerView
import kotlin.math.abs

/**
 * Created by Javad Vatan on 1/24/2020
 * Sites: http://Jvatan.ir && http://JavadVatan.ir
 *
 * How to implement an horizontal timed scrolling recyclerview in Android.
 * From :
 * https://medium.com/@tejumoladavid_91868/how-to-implement-an-horizontal-timed-scrolling-recyclerview-in-android-e4da369532f0
 */
class ScrollingLinearLayoutManager(context: Context?, orientation: Int, reverseLayout: Boolean, private val duration: Int)
    : LinearLayoutManager(context, orientation, reverseLayout) {

    override fun smoothScrollToPosition(recyclerView: RecyclerView, state: RecyclerView.State,
                                        position: Int) {

        val firstVisibleChild = recyclerView.getChildAt(0)
        val itemHeight = firstVisibleChild.height
        val currentPosition = recyclerView.getChildLayoutPosition(firstVisibleChild)
        var distanceInPixels = abs((currentPosition - position) * itemHeight)
        if (distanceInPixels == 0) {
            distanceInPixels = abs(firstVisibleChild.y).toInt()
        }
        val smoothScroller = SmoothScroller(recyclerView.context, distanceInPixels, duration)
        smoothScroller.targetPosition = position
        startSmoothScroll(smoothScroller)
    }

    private inner class SmoothScroller(context: Context?, distanceInPixels: Int, duration: Int) : LinearSmoothScroller(context) {
        private val distanceInPixels: Float = distanceInPixels.toFloat()
        private val duration: Float = duration.toFloat()

        override fun computeScrollVectorForPosition(targetPosition: Int): PointF? {
            return this@ScrollingLinearLayoutManager.computeScrollVectorForPosition(targetPosition)
        }

        override fun calculateTimeForScrolling(dx: Int): Int {
            val proportion = dx.toFloat() / distanceInPixels
            return (duration * proportion).toInt()
        }

    }

}