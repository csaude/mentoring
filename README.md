# Mentoring
Mentoring Project (Back-End)

This project as only one dependency that is frameworks-core. All you need to do is to clone this project and build or install on your local machine.

# Database Configurations
 - You need to create 2 databases schemas (one for test and another for development) with names mentoring_test and mentoring respectively

# JWT Authentication Configuration
These projects relies on Account Manager for authentication. The Account manager components authenticates users by issuing JWT tokes using HMAC
algorithm which uses a secret key for encryption. The key has to be shared between both the Account Manager application and the instance of mentoring
application. The runtime properties file is `/opt/mentoring_runtime.properties`, this can be changed however by setting the environment variable
`MENTORING_RUNTIME_PROPERTIES_PATH`. The file should have the JWT key used by the Account Manager application to issue the tokes, the name of the
property is `jwt.key`. So the entry in the runtime properties file should look as below.

```$xslt
jwt.key=secret
```
* The application intercepts all requests, checks for the header `Authorization Bearer {JWT token}` where `{JWT token}` is a place holder for an actual
token. Then it runs the algorithm to verify if the token is authentic, allowing the request through if it is or sending back an HTTP `401 Unauthorized`
along with a message if it is not.
