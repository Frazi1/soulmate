package validation

data class ValidationFieldError(val property: String,
                                val code: String?,
                                val obj: Any?)