Design and implement an in-memory, multi-threaded queue system similar to Amazon Simple Queue Service (SQS) that allows for asynchronous message processing. The system should provide a reliable way to transfer messages between different parts of an application, ensuring that each message is processed by a consumer. The queue should support multiple producers and consumers operating concurrently.

Example: AdditionWorker - 5 seconds

Message: 2 numbers.



sqs -> 5 consumers -> only one of them will process the message

kafka -> topic -> 5 consumers of type1 -> one of these 5 consumers should get the message  
4 consumers of type 2: one of these 4 consumers hsould get the message
