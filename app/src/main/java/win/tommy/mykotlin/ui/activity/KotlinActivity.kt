package win.tommy.mykotlin.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import win.tommy.mykotlin.R
import win.tommy.mykotlin.bean.UserBean

class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
    }

    //创建一个伴生对象
    companion object {
        val USER = "USER"
        fun startActivity(context: Context,user: UserBean){
            var intent = Intent(context, KotlinActivity::class.java)
            intent.putExtra(USER,user)
            context.startActivity(intent)
            Log.e("twb",user.name+user.id)
        }
    }

    override fun onResume() {
        super.onResume()
        Log.e("twb","onResume")
    }
}
