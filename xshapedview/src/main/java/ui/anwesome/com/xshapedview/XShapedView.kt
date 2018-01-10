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
    data class XShaped(var x:Float,var y:Float,var size:Float) {
        fun draw(canvas:Canvas,paint:Paint) {
            paint.strokeCap = Paint.Cap.ROUND
            paint.strokeWidth = size/30
            paint.color = Color.parseColor("#AD1457")
            canvas.save()
            canvas.translate(x,y)
            for(i in 0..3) {
                canvas.save()
                canvas.rotate(i*90f+45f)
                for(j in 0..1) {
                    canvas.save()
                    canvas.translate(0f,-size+j*size/2)
                    canvas.rotate(90f)
                    canvas.drawLine(0f,0f,size/2,0f,paint)
                    canvas.restore()
                }
                canvas.restore()
            }
            canvas.restore()
        }
        fun update(stopcb:(Float)->Unit) {
        
        }
        fun startUpdating(startcb:()->Unit) {

        }
    }
}