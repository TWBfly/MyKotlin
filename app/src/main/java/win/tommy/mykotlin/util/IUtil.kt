package win.tommy.mykotlin.util

import android.content.Context
import android.webkit.WebView
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso
import okhttp3.Request
import win.tommy.mykotlin.OkClient


fun Context.toast(message:String, length:Int= Toast.LENGTH_LONG){
    Toast.makeText(this,message,length).show()
}
fun getHtml(url: String): String {
    val client = OkClient.instance
    val request = Request.Builder()
            .url(url)
            .build()

    val response = client.newCall(request).execute()
    return response.body()?.string() ?: ""
}
fun WebView.load(html: String) {
    this.loadDataWithBaseURL("http://ishuhui.net/", html, "text/html", "charset=utf-8", null)
}
fun ImageView.loadUrl(url: String) {
    Picasso.with(this.context)
            .load(url)
            .into(this)
}