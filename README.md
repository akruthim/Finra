#Finra-File-Upload

### Todo before execution
* Change property `file.persist.location` in `src/main/java/resources/application.yml` to the directory of your choice. This is where the uploaded files gets persisted.<br/> 
As a best practice, I have configured the directory as a property, for better maintenance in future.
* Optionally you can create the above configured directory before program execution. Or to make it more convenient, I have added logic to create the directory if it doesn't exist. 


### Things to know
* **Java 8** is used for development.
* _com.interview.finra.FinraApplication_ - Main Configuration class
* _com.interview.finra.controller.FileController_ - is the entry point, which has the rest endpoint.
* Program starts at **8080 port**.
* File metadata is persisted in H2 DB.
* Maven is used for build.
* JPA style DB interaction

### Additional Info
* For convenience, I have uploaded my test files at `src/main/resources/input`. Feel free to use them or kindly test with your set of file(s). 


## cURL's

####Upload file

<b>**Note:** Change the file location according to yours

```
curl -X PUT \
    http://localhost:8080/files \
    -H 'Accept: application/json' \
    -H 'Cache-Control: no-cache' \
    -H 'Connection: keep-alive' \
    -H 'Content-Type: multipart/form-data' \
    -F file=@/Users/sss765/Documents/Finra/src/main/resources/input/finance.txt \
    -F file-metadata=@/Users/sss765/Documents/Finra/src/main/resources/input/metadata.json
```

**Response**

    { "fileId": 1 }

####Retrieve metadata
```
curl -X GET \
    http://localhost:8080/files/1/metadata \
    -H 'Accept: application/json' \
```
