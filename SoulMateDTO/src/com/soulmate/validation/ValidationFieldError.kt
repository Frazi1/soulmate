package validation

@Suppress("unused", "MemberVisibilityCanBePrivate")
class ValidationFieldError {
    var code: String? = null
    var obj: Any? = null
    var property: String? = null

    constructor(property: String?, code: String?, obj: Any?) {
        this.code = code
        this.obj = obj
        this.property = property
    }

    constructor()
}