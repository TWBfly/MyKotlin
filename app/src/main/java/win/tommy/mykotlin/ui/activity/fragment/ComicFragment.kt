package win.tommy.mykotlin.ui.activity.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import uk.co.senab.photoview.PhotoView
import win.tommy.mykotlin.R
import win.tommy.mykotlin.ui.activity.binder.toBundle
import win.tommy.mykotlin.util.snackbar


class ComicFragment: Fragment() {
    lateinit var  progressBar : ProgressBar
    lateinit var  iv_comic : PhotoView
    lateinit var  url : String

    companion object {
        fun instance(url: String): ComicFragment {
            val fragment = ComicFragment()
            fragment.arguments = mapOf("url" to url).toBundle()
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        url = arguments.getString("url", "")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(R.layout.fragment_comic_page, container, false)
        progressBar = rootView?.find(R.id.progressBar)!!
        iv_comic = rootView?.find(R.id.iv_comic)!!
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        Picasso.with(context)
                .load(url)
                .placeholder(R.color.material_deep_purple_50)
                .into(iv_comic, object : Callback {
                    override fun onSuccess() {
                        progressBar.visibility = View.GONE
                    }

                    override fun onError() {
                        iv_comic.snackbar(R.string.network_error)
                    }
                })
    }
}