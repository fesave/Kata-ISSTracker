package machucapps.com.framework.datasource.provider.impl

import android.content.Context
import machucapps.com.framework.datasource.provider.ContextProvider

class ContextProviderImpl(
    private val context: Context
) : ContextProvider {
    override fun getContext(): Context = context
    override fun getCustomString(id: Int, text: String) = String.format(context.getString(id), text)

}