package validation

data class ValidationResponse(val fieldsErrors: List<ValidationFieldError>,
                              val globalErrors: List<ValidationGlobalError>)