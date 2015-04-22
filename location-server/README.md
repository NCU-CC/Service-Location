## Location-Server
A restful web service

## Dependencies
- Spring MVC
- Spring Boot
- Spring Data
- Spring Security
- Lucene

## Gradle
- appStart  : run server in production env
- appStop   : stop server in production env

## Packages
- entity     : hibernate orm bean
- exception  : spring exception handler
- interceptor: spring interceptor
- web        : spring controller
- converter  : spring converter
- repository : spring data repository
- service    : spring service
- task       : spring task
- config     : spring java config

## Resources
There are two environments for this project

- test : `application-test.yml`
- production : `application-production.yml`

You have to write your own production config. See `.example` file in resources root path


## API

This api server is protected by api token.

See https://github.com/NCU-CC/API-Documentation for further information