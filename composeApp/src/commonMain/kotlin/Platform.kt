interface Platform {
    val name: String
}

enum class PlatformName {
    ANDROID,
    IOS,
    DESKTOP
}

expect fun getPlatform(): Platform
expect fun getPlatformName(): PlatformName