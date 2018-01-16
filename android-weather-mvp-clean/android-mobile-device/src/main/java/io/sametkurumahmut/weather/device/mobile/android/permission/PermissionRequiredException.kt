package io.sametkurumahmut.weather.device.mobile.android.permission

open class PermissionRequiredException : SecurityException {

    constructor() : super()
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
    constructor(cause: Throwable) : super(cause)
}
