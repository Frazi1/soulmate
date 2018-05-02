package validation

@Suppress("unused")
class ValidationResponse {
    lateinit var fieldErrors: List<ValidationFieldError>
    lateinit var globalErrors: List<ValidationGlobalError>

    constructor(fieldErrors: List<ValidationFieldError>, globalErrors: List<ValidationGlobalError>) {
        this.fieldErrors = fieldErrors
        this.globalErrors = globalErrors
    }

    //For deserialization
    constructor()
}