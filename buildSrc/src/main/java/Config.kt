import org.gradle.api.JavaVersion

object Config {
    const val minSdk = 21
    const val compileSdk = 32
    const val targetSdk = 32

    val javaVersion = JavaVersion.VERSION_11

    const val testInstrumentationRunner = "com.capiter.core.testing.runner.CapiterTestRunner"
}
