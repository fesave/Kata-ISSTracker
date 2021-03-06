package machucapps.com.framework.provider.impl

import android.content.Context
import machucapps.com.framework.provider.ContextProvider

class ContextProviderImpl(
    private val context: Context
) : ContextProvider {
    override fun getContext(): Context = context
    override fun getCustomString(id: Int, text: String) = String.format(context.getString(id), text)
    override fun getCustomDetailString(id: Int, number: Int, text: String) =
        String.format(context.getString(id), number, text)

    override fun getString(id: Int): String = context.getString(id)

}