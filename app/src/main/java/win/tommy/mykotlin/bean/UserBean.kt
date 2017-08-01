package win.tommy.mykotlin.bean

import java.io.Serializable


class UserBean:Serializable  {
    var name : String?=null
    var id : Int?=null
    constructor(name:String){
        this.name = name
    }
    constructor(name: String,id:Int){
        this.name=name
        this.id=id
    }
}