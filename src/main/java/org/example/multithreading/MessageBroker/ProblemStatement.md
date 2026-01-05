Design message broker which supports multiple subscribers on a topic.

Example:
AdditionSubscriber - 4 seconds - 1 consumer
MinusSubscriber - 2 seconds - 2 consumers

Message: 2 numbers.

kafka -> topic -> 5 consumers of type1 -> one of these 5 consumers should get the message  
4 consumers of type 2: one of these 4 consumers hsould get the message

Extension:
Offset reset.

{1, 2} {2, 3} {4, 5}
