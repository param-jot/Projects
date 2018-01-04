# GerbilQA-Benchmarking-Template
A template for QA systems to benchmark with GERBIL. It implements the web service interface as mentioned here, https://github.com/dice-group/gerbil/wiki/Question-Answering#web-service-interface.

### Running the template via Maven
After cloning/downloading the repository, on the terminal run: 
``` 
cd GerbilQA-Benchmarking-Template/ 
mvn clean install 
mvn spring-boot:run
```
The application will be started on http://localhost:8080. Test it by simply sending an HTTP POST request to http://localhost:8080/ask-gerbil with the parameters: 
- `query`: A UTF-8 encoded String <br>
- `lang`: language of the question <br>

An example request would look like: <br>
``` curl -d "query=What is the capital of Germany?&lang=en" -X POST http://localhost:8080/ask-gerbil ```

To test against GERBIL (http://gerbil-qa.aksw.org/gerbil/config), put the public IP address of your system (instead of localhost) in the webservice URI field. 
