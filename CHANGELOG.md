### Install JSON Web Token dependencies

We need a library to encode, decode, and validate the JWT token in the application. We will use JWT, so open the pom.xml and the code below in the "dependencies" XML tag:

    <dependencies>
        <!---- existing dependencies here....... ---->
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>0.11.5</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>0.11.5</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <version>0.11.5</version>
        </dependency>
    </dependencies>

### Create the JWT service

To generate, decode, or validate a JSON Web token, we must expose the related methods that use the libraries we installed earlier. We will group them into a service class named `JwtService`.

Create a package `services`, then add the file `JwtService.java`.

The methods that will be used are `generateToken()`, `isTokenValid()` and `getExpirationTime()`.

To generate the JWT token, we need a secret key and the token expiration time; these values are read from the application configuration properties file using the annotation `@Value`.

The secret key must be an HMAC hash string of 256 bits; otherwise, the token generation will throw an error. I used this [website](https://www.devglan.com/online-tools/hmac-sha256-online) to generate one.

The token expiration time is expressed in milliseconds, so remember if your token expires too soon.

