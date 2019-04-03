# devon-kafka

This POC expects you to have kafka instance running on default zookeeper and kafka ports 

Please use  postman to send message to kafka service exposed as custom rest service 

one sample endpoint 

  http://localhost:9080/kafka/test/message 

 # here test is topic name created on kafka. 

  JSON from postmane 

  {
	"Key" : "1001" ,
	"value" : "Example of Pting Pting Pting" , 
	"topic" : "test" ,
	"timestamp" : -1,
	"offset" : 100000,
	"partition" : 0
  } 

  topic and value is required, all other parameteres can be used based on which method you are using

  ## docker-compose.xml and Dockerfile for kafka and zookeeper images will be added to test resources folder and can be copied form there. 
  ### In case you are within capgemini network and able to access 10.76.3.49, you can connect to kafka on 9092 port.   

 NOTE :  I am using plaintext API, my spring boot app is configured to run on 9080 port. 
