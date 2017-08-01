package win.tommy.mykotlin.domain.net


interface Source<out T> {
    fun obtain(url: String): T
}