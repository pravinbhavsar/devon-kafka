# devon-kafka

This POC expects you to have kafka instance running on default zookeeper and kafka ports 

Please use  postman to send message to kafka service exposed as custom rest service 

one sample endpoint 

  http://localhost:9080/kafka/test/message 

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

 NOTE :  I am using plaintext API, my spring boot app is configured to run on 9080 port. 
