## Location-Server
A restful web service written in Java

## Dependencies
- Spring 4.1
- Hibernate 4.3
- C3P0 0.9
- JPA 2.0
- H2 1.4

## Gradle
- jettyStart  : run server in embedded server
- jettyStop   : stop the server started by command above

## Packages
- controller : spring mvc controller
- converter  : spring type converter
- data       : internal processing data
- entity     : hibernate orm bean
- exception  : spring exception handler
- interceptor: spring mvc interceptor
- service    : spring core service
- task       : spring task

## Resources
resources are divided into two environments for Spring

- develope   : include embedded database and mocked elements
- production : additional configurations, must put the following documents to src/main/resources/production
    - connection.properties
    ```
    min_size = 5
    max_size = 75
    ...or other c3p0 settings
    user     = [ your database user name ]
    password = [ your database user password ]
    ```
    
    - database.properties
    ```
    jdbc.driver = com.mysql.jdbc.Driver
    jdbc.url = jdbc:mysql://localhost/dbname
    init_database = [ true / false ]
    init_database_file = classpath:develope/data.sql
    ```
    
    - hibernate.properties
    ```
    hibernate.dialect  = org.hibernate.spatial.dialect.mysql.MySQLSpatialInnoDBDialect
    hibernate.show_sql = [ true / false ]
    ...or other hibernate setting
    ```
    
    - lucene.properties
    ```
    lucene.index.path = path/to/store/lucene/indexes
    ```
