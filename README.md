# devon-kafka

Read swagger documentation at http://localhost:9080/swagger-ui.html 

This POC expects you to have kafka instance running on default zookeeper and kafka ports 

Please use  postman to send message to kafka service exposed as custom rest service 

one sample endpoint 

  http://localhost:9080/kafka/test/message 

  In above Rest call, test is the topic name which is passed as parameter. 

  JSON from postmane 


   {
	"key" : "1004", 
	"messageId" : "1001",
	"payload" : "Msg1" , 
	"topic" : "test" ,
	"timestamp" : "10010001",
	"offset" : 100000,
	"partition" : 0
 } 


 Message can be received with postman or unit test 


 http://localhost:9080/kafka/consumeobject/test
  

 Topic and payload is required, all other parameteres can be used based on which method you are using

  
  ## Preparation of Docker Instance 

 	1.  Docker-compose.xml and Dockerfile for kafka and zookeeper images will be added to test resources folder and can be copied form there. 
 	2.  In case you are within capgemini network and able to access 10.76.3.49, you can connect to kafka on 9092 port.   
 	NOTE : I am using plaintext API, my spring boot app is configured to run on 9080 port. 
