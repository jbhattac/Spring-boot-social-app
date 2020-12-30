#Concepts covered in the poc

- Understanding  REST concepts and implementing with Spring boot.
- Implementing CRUD operations for REST with spring boot.
- Spring boot auto configuration and Dispatcher Servlet.
- Using HTTP Status codes and location for POST methods
- Exception Handeling with REST services using Spring boot.
- Validation for Request.
- Implementing HATEOS for REST API using spring boot.
- Internationalization for REST Services.
- Content negotiation and implementing xml service.
- Auto configuration of swagger documentation.
- Monitoring api with actuator.
- Implementing static and dynamic filtering.
- Implementing JPA repository for social media user and post.

## Useful Links

- POSTMAN - http://www.getpostman.com

### Links from poc examples
- Basic Resources
  - http://localhost:8080/hello-world
  - http://localhost:8080/hello-world-bean
  - http://localhost:8080/hello-world/path-variable/Ranga
  - http://localhost:8080/users/
  - http://localhost:8080/users/1
- JPA Resources
  - http://localhost:8080/jpa/users/
  - http://localhost:8080/jpa/users/1
  - http://localhost:8080/jpa/users/10001/posts
- Filtering
  - http://localhost:8080/filtering
  - http://localhost:8080/filtering-list
- Actuator
  - http://localhost:8080/actuator
- Versioning
  - http://localhost:8080/v1/person
  - http://localhost:8080/v2/person
  - http://localhost:8080/person/param
     - params=[version=1]
  - http://localhost:8080/person/param
     - params=[version=2]
  - http://localhost:8080/person/header
     - headers=[X-API-VERSION=1]
  - http://localhost:8080/person/header
     - headers=[X-API-VERSION=2]
  - http://localhost:8080/person/produces
     - produces=[application/vnd.company.app-v1+json]
  - http://localhost:8080/person/produces
  	 - produces=[application/vnd.company.app-v2+json]
- Swagger
  - http://localhost:8080/swagger-ui.html
  - http://localhost:8080/v2/api-docs
- H2-Console
  - http://localhost:8080/h2-console

## Table Structure

```sql
create table user (
id integer not null, 
birth_date timestamp, 
name varchar(255), 
primary key (id)
);

create table post (
id integer not null, 
description varchar(255), 
user_id integer, 
primary key (id)
);

alter table post 
add constraint post_to_user_foreign_key
foreign key (user_id) references user;
```

## Example Requests

### GET http://localhost:8080/users
```json
[
    {
        "id": 1,
        "name": "Adam",
        "birthDate": "2017-07-19T04:40:20.796+0000"
    },
    {
        "id": 2,
        "name": "Eve",
        "birthDate": "2017-07-19T04:40:20.796+0000"
    },
    {
        "id": 3,
        "name": "Jack",
        "birthDate": "2017-07-19T04:40:20.796+0000"
    }
]
```

### GET http://localhost:8080/users/1
```json
{
    "id": 1,
    "name": "Adam",
    "birthDate": "2017-07-19T04:40:20.796+0000"
}
```

### POST http://localhost:8080/users
```json
{
    "name": "Ranga",
    "birthDate": "2000-07-19T04:29:24.054+0000"
}
```

### GET http://localhost:8080/users/1000
- Get request to a non existing resource. 
- The response shows default error message structure auto configured by Spring Boot.

```json
{
    "timestamp": "2017-07-19T05:28:37.534+0000",
    "status": 404,
    "error": "Not Found",
    "message": "id-500",
    "path": "/users/500"
}
```

### GET http://localhost:8080/users/1000
- Get request to a non existing resource. 
- The response shows a Customized Message Structure
```json
{
    "timestamp": "2017-07-19T05:31:01.961+0000",
    "message": "id-500",
    "details": "Any details you would want to add"
}
```

### POST http://localhost:8080/users with Validation Errors

#### Request

```json
{
    "name": "R",
    "birthDate": "2000-07-19T04:29:24.054+0000"
}
```

#### Response - 400 Bad Request

```json
{
    "timestamp": "2017-07-19T09:00:27.912+0000",
    "message": "Validation Failed",
    "details": "org.springframework.validation.BeanPropertyBindingResult: 1 errors\nField error in object 'user' on field 'name': rejected value [R]; codes [Size.user.name,Size.name,Size.java.lang.String,Size]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [user.name,name]; arguments []; default message [name],2147483647,2]; default message [Name should have atleast 2 characters]"
}
```

### GET http://localhost:8080/users/1 with HATEOAS
```json
{
    "id": 1,
    "name": "Adam",
    "birthDate": "2017-07-19T09:26:18.337+0000",
    "_links": {
        "all-users": {
            "href": "http://localhost:8080/users"
        }
    }
}
```

### XML Representation of Resources

#### GET http://localhost:8080/users

- Accept application/xml

```xml
<List>
    <item>
        <id>2</id>
        <name>Eve</name>
        <birthDate>2017-07-19T10:25:20.450+0000</birthDate>
    </item>
    <item>
        <id>3</id>
        <name>Jack</name>
        <birthDate>2017-07-19T10:25:20.450+0000</birthDate>
    </item>
    <item>
        <id>4</id>
        <name>Ranga</name>
        <birthDate>2017-07-19T10:25:20.450+0000</birthDate>
    </item>
</List>
```

#### POST http://localhost:8080/users
- Accept : application/xml
- Content-Type : application/xml

##### Request

```xml
<item>
        <name>Ranga</name>
        <birthDate>2017-07-19T10:25:20.450+0000</birthDate>
</item>
```

##### Response
- Status - 201 Created


### Versioning
 - Media type versioning (a.k.a “content negotiation” or “accept header”)
   - GitHub
 - (Custom) headers versioning
   - Microsoft
 - URI Versioning
   - Twitter
 - Request Parameter versioning 
   - Amazon
 - Factors
  - URI Pollution
  - Misuse of HTTP Headers
  - Caching
  - Can we execute the request on the browser?
  - API Documentation
 - No Perfect Solution 

#### More
- https://www.mnot.net/blog/2011/10/25/web_api_versioning_smackdown
- http://urthen.github.io/2013/05/09/ways-to-version-your-api/
- http://stackoverflow.com/questions/389169/best-practices-for-api-versioning
- http://www.lexicalscope.com/blog/2012/03/12/how-are-rest-apis-versioned/
- https://www.3scale.net/2016/06/api-versioning-methods-a-brief-reference/

## Resources and URI Mappings

- Retrieve all Users      - GET  /users
- Create a User           - POST /users
- Retrieve one User       - GET  /users/{id} -> /users/1   
- Delete a User           - DELETE /users/{id} -> /users/1

- Retrieve all posts for a User - GET /users/{id}/posts 
- Create a posts for a User - POST /users/{id}/posts
- Retrieve details of a post - GET /users/{id}/posts/{post_id}