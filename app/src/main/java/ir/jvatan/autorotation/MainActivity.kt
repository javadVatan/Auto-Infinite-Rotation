package ir.jvatan.autorotation

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.tomoima.infiniterotation.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initList()
    }

    private fun initList() {
        recyclerView.apply {
            layoutManager = ScrollingLinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false, 9000)
            adapter = SimpleAdapter(createItems())
            post { smoothScrollToPosition(Integer.MAX_VALUE - 1) }
        }
    }

    private fun createItems() = Array(8) { i ->
        ItemInfo(i.toString(), Color.rgb((Math.random() * 255).toInt(),
                (Math.random() * 255).toInt(),
                (Math.random() * 255).toInt()))
    }.toList()

}
