plugins {
    java
}

group = "xbc.tut"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    compile("org.springframework", "spring-orm", "5.0.6.RELEASE");
    compile("org.springframework", "spring-context", "5.0.6.RELEASE");

    compile("org.hibernate:hibernate-agroal:5.3.0.Final")
    compile("org.hibernate:hibernate-c3p0:5.3.0.Final")
    compile("com.h2database:h2:1.4.197")

    //testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}