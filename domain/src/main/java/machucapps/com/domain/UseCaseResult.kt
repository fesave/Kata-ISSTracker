package machucapps.com.domain

sealed class UseCaseResult<out T> {
    class Success<out T>(val data: T) : UseCaseResult<T>()
    class Error<out T>(val throwable: Throwable) : UseCaseResult<T>()
}