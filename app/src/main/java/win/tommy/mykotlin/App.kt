package win.tommy.mykotlin

import android.app.Application


class App : Application() {
//    lateinit var context:Context = null!!

    override fun onCreate() {
        super.onCreate()
//        context = this
    }
//    fun getContext(): Context = context
}