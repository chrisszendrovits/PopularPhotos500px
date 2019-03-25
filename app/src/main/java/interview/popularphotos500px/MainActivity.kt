package interview.popularphotos500px

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            // show the popular photos fragment
            supportFragmentManager
                .beginTransaction()
                .add(R.id.root_frame_layout, PopularPhotosFragment.newInstance(), "popularPhotosFragment")
                .commit()
        }
    }
}
