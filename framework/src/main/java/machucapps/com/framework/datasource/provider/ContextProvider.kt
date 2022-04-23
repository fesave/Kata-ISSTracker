package machucapps.com.framework.datasource.provider

import android.content.Context

interface ContextProvider {
    fun getContext(): Context
    fun getCustomString(id: Int, text: String): String
}