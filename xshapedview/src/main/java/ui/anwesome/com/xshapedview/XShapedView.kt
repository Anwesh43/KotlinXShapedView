package ui.anwesome.com.xshapedview

/**
 * Created by anweshmishra on 11/01/18.
 */
import android.view.*
import android.content.*
import android.graphics.*
class XShapedView(ctx:Context):View(ctx) {
    val paint:Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    override fun onDraw(canvas:Canvas) {

    }
    override fun onTouchEvent(event:MotionEvent):Boolean {
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }
}