package ui.anwesome.com.kotlinxshapedview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import ui.anwesome.com.xshapedview.XShapedView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = XShapedView.create(this)
        view.addOnXShapeListener({Toast.makeText(this,"Opened",Toast.LENGTH_SHORT).show()},{Toast.makeText(this,"Closed",Toast.LENGTH_SHORT).show()})
        fullScreen()
    }
}
fun AppCompatActivity.fullScreen() {
    window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    supportActionBar?.hide()
}
