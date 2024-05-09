package domain

import adpmeetups.composeapp.generated.resources.Res
import adpmeetups.composeapp.generated.resources.error_generic
import adpmeetups.composeapp.generated.resources.error_internet
import io.ktor.utils.io.errors.IOException
import org.jetbrains.compose.resources.getString

/**
 * Encapsulates an error. We can add more
 * attributes like error type, expected action
 * to be taken (retry, cancel, nothing, etc).
 */
data class AdpError(
    val code: String? = null,
    val message: String
)

/**
 * Maps any exception throw or returned
 * through a [Result.failure] and returns
 * an [AdpError] object.
 *
 * This interface is expected to be implemented
 * by a inner layer like domain layer to map
 * business errors and can be inherited by outer
 * layers like the data layer in order to map more
 * types of errors like Network, HTTP, etc.
 */
interface ErrorMapper {

    /**
     * Maps any throwable and returns a [AdpError].
     */
    suspend fun map(error: Throwable): AdpError
}

/**
 * Domain Layer Error Mapper.
 */
class ErrorMapperImpl : ErrorMapper {

    override suspend fun map(error: Throwable): AdpError {
        return when (error) {
            is IOException -> AdpError(
                code = null,
                message = getString(Res.string.error_internet)
            )

            else -> AdpError(
                code = null,
                message = getString(Res.string.error_generic)
            )
        }
    }

}