plugins {
    id("java")
    id("maven-publish")
}

//  Master version value -- update this to conjtrol both publishing and everything else!
val currentVersion = "1.0.2.1"


group = "com.vandenbreemen"
version = currentVersion

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

//  See also
//  https://docs.github.com/en/actions/publishing-packages/publishing-java-packages-with-gradle#publishing-packages-to-github-packages
//  For the moment publishing will only work out on github actions....
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = "com.vandenbreemen"
            artifactId = "video-display-raster"
            version = currentVersion
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/kevinvandenbreemen/video-display-raster")
            credentials {
                username = System.getenv("USERNAME")
                password = System.getenv("TOKEN")
            }
        }
    }
}