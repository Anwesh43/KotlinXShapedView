package ui.anwesome.com.xshapedview

/**
 * Created by anweshmishra on 11/01/18.
 */
import android.app.Activity
import android.view.*
import android.content.*
import android.graphics.*
class XShapedView(ctx:Context):View(ctx) {
    val paint:Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val renderer = Renderer(this)
    override fun onDraw(canvas:Canvas) {
        renderer.render(canvas,paint)
    }
    override fun onTouchEvent(event:MotionEvent):Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                renderer.handleTap()
            }
        }
        return true
    }
    data class XShaped(var x:Float,var y:Float,var size:Float) {
        val state = State()
        fun draw(canvas:Canvas,paint:Paint) {
            paint.strokeCap = Paint.Cap.ROUND
            paint.strokeWidth = size/30
            paint.color = Color.parseColor("#AD1457")
            canvas.save()
            canvas.translate(x,y)
            for (i in 0..3) {
                canvas.save()
                canvas.rotate(i*90f+45f)
                for (j in 0..1) {
                    canvas.save()
                    canvas.translate(0f,-size+j*size/2)
                    canvas.rotate(90f*state.scale)
                    canvas.drawLine(0f,0f,size/2,0f,paint)
                    canvas.restore()
                }
                canvas.restore()
            }
            canvas.restore()
        }
        fun update(stopcb:(Float)->Unit) {
            state.update(stopcb)
        }
        fun startUpdating(startcb:()->Unit) {
            state.startUpdating(startcb)
        }
    }
    data class State(var scale:Float = 0f,var dir:Float = 0f,var prevScale:Float = 0f) {
        fun update(stopcb:(Float)->Unit) {
            scale += 0.1f*dir
            if (Math.abs(scale - prevScale) > 1) {
                scale = prevScale + dir
                dir = 0f
                prevScale = scale
                stopcb(scale)
            }
        }
        fun startUpdating(startcb:()->Unit) {
            if (dir == 0f) {
                dir = 1-2*scale
                startcb()
            }
        }
    }
    data class Animator(var view:XShapedView,var animated:Boolean = false) {
        fun animate(updatecb:()->Unit) {
            if (animated) {
                updatecb()
                try {
                    Thread.sleep(50)
                    view.invalidate()
                }
                catch(ex:Exception) {

                }
            }
        }
        fun stop() {
            if (animated) {
                animated = false
            }
        }
        fun startAnimation() {
            if (!animated) {
                animated = true
                view.postInvalidate()
            }
        }
    }
    data class Renderer(var view:XShapedView,var time:Int = 0) {
        val animator = Animator(view)
        var xShaped:XShaped?=null
        fun render(canvas:Canvas,paint:Paint) {
            if(time == 0) {
                val w = canvas.width.toFloat()
                val h = canvas.height.toFloat()
                xShaped = XShaped(w/2,h/2,2*w/5)
            }
            canvas.drawColor(Color.parseColor("#212121"))
            xShaped?.draw(canvas,paint)
            time++
            animator.animate{
                xShaped?.update{
                    animator.stop()
                }
            }
        }
        fun handleTap() {
            xShaped?.startUpdating{
                animator.startAnimation()
            }
        }
    }
    companion object {
        fun create(activity:Activity):XShapedView {
            val view = XShapedView(activity)
            activity.setContentView(view)
            return view
        }
    }
}