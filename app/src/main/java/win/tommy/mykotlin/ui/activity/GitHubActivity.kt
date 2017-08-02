package win.tommy.mykotlin.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_git_hub.*
import win.tommy.mykotlin.R

class GitHubActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_git_hub)
        web_view.loadUrl(MainActivity.url)
    }
}
