package ui.anwesome.com.kotlinxshapedview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ui.anwesome.com.xshapedview.XShapedView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        XShapedView.create(this)
    }
}
