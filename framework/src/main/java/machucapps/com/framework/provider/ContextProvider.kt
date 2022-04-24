package machucapps.com.framework.provider

import android.content.Context

interface ContextProvider {
    fun getContext(): Context
    fun getCustomString(id: Int, text: String): String
    fun getString(id:Int):String
}