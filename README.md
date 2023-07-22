# Technical Challengee - Test
REST API for 'ms-technical-challengee'

Está sección se dan los pases para ejecutar la aplicación de prueba técnica de Marathon

## Install

## Run test app

### With Maven:

For maven, you can run the following command:

    ./mvn clean install
    
## Run the app

    java -jar target\ms-technical-challengee-core-1.0.0.jar
    
### With Docker

For docker build, you must run the following command:

    docker build -t ms-technical-challengee \
    --file Dockerfile .

# REST API
This API have one endpoint, Below is detailed:

Path: POST /aseyco-test/{version}/oauth/token

*_where {version} is a version of Endpoint_

| Path | Operation Name | Details |
|------|----------------|---------|
| /oauth/token | generateToken| Token Generation

### Request:
Example:

    /aseyco-test/v1/oauth/token

|Name|Type|Data Type|Required|
|----|----|---------|--------|
|ruc|Body|String|true|
|businessName|Body|String|true|
|status|Body|String|true|
|address|Body|String|true|
|ubigeo|Body|String|true|
|department|Body|String|true|
|province|Body|String|true|
|district|Body|String|true|


### Response:
|Name|Type|Data Type|Example|
|----|----|---------|-------|
|status||Object[json]|
|code||String|0000|
|message||String|Success|
|data||Object||
|token|String|eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZWNobmljYWwtY2hhbGxlbmdlIiwibmJmIjoxNjkwMDM5NzM5LCJpc3MiOiJBc2V5Y28iLCJjbGFpbXMiOiJUZXN0IiwiZXhwIjoxNjkwMDQwMDM4LCJpYXQiOjE2OTAwMzk3MzgsImp0aSI6IjljYmNkODUzLTk4YjAtNDk2Ny05NGIxLTE3MDFjNzdkZjRhZSJ9.kpcDoCKzSCZmLtJFOlf88d38aN9WLD-Hn4pW4btaHVE|


Path: POST /aseyco-test/{version}/company

*_where {version} is a version of Endpoint_

| Path | Operation Name | Details |
|------|----------------|---------|
| /company | companyInsert| Save Company Data

### Request:
Example:

    /aseyco-test/v1/company

|Name|Type|Data Type|Required|Example|
|----|----|---------|--------|
|Authorization|Header|String|true|Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZWNobmljYWwtY2hhbGxlbmdlIiwibmJmIjoxNjkwMDM5NzM5LCJpc3MiOiJBc2V5Y28iLCJjbGFpbXMiOiJUZXN0IiwiZXhwIjoxNjkwMDQwMDM4LCJpYXQiOjE2OTAwMzk3MzgsImp0aSI6IjljYmNkODUzLTk4YjAtNDk2Ny05NGIxLTE3MDFjNzdkZjRhZSJ9.kpcDoCKzSCZmLtJFOlf88d38aN9WLD-Hn4pW4btaHVE|


### Response:
|Name|Type|Data Type|Example|
|----|----|---------|-------|
|status||Object[json]|
|code||String|0000|
|message||String|Success|
