# rabbitmq

管控台：http://localhost:15672/

###生产端：

publisher-confirms=true：ConfirmCallback
限制：每个RabbitTemplate仅支持一个ConfirmCallback
限制：需提供CorrelationData的唯一id
限制：ConfirmCallback需在发送消息前设置

含义：确认消息是否成功到达Exchange

publisher-returns=true：ReturnCallback
限制：每个RabbitTemplate仅支持一个ReturnCallback
限制：ReturnCallback需要设置**mandatory=true**
限制：ReturnCallback需在发送消息前设置

含义：确认消息是否成功到达队列

####注意：
如果消息成功到达Exchange，但到达队列失败，ack返回true，并触发ReturnCallback

###消费端

default-requeue-rejected: 默认为true(重回队列)
含义：失败不重回队列(生产使用)
注意：生产环境应该设置为false；

ack默认方式：AcknowledgeMode.AUTO

prefetchCount 消息预取: DEFAULT_PREFETCH_COUNT=250

transaction-size 消息ack，默认为1
约束：txSize <= prefetchCount
约束：AcknowledgeMode#AUTO
约束：one ack per txSize. Default is 1

concurrency，默认为1，max-concurrency
含义：消费者个数，并发
注意：每个消费者都有自己消息预取（prefetchCount），
concurrency * prefetchCount为一台机器的消费能力；

retry：默认为false；
含义：消费者重试次数，本地重试；
注意：如果retry=true，requeue设置不生效，强制为false，即不重回队列；

###死信队列(DLX,死信交换机)
component：Exchange, RouteKey, Queue;
用途：当消息成为dead message时，将被publish消息至DLX；
载体：在Queue声明时指定；
注意：必须设置 requeue=false；


###备用交换机(AE)
https://www.rabbitmq.com/ae.html
用途：消息到达Exchange,却无法路由，将被publish消息至AE；
载体：在Exchange声明时指定；
注意：设置AE之后，不再出发ReturnCallback；

###场景

1.场景：5个消费者，prefetchCount=5；action：生产者连续发5条消息；
result：每个消费者一条消息；

2.场景：5个消费者，prefetchCount=5；action：生产者连续发30条消息；
result：第一轮每个消费者5条消息，第二轮其中一个消费者5条消息；

3.场景：5个消费者，prefetchCount=5；action：生产者分批发消息，一次发5个，发2轮；
result：第一轮发消息后，每个消费者一条消息，第二轮发消息后，每个消费者2条消息；

4.场景：1个消费者，prefetchCount=5，txSize=5，requeue=false；
action：生产者连续发5条消息，其中一条报异常；
result：分两次ack，遇到异常立即ack，剩下的消费完后ack，最后队列为空；

5.场景：1个消费者，prefetchCount=5，txSize=5，requeue=true；
action: 生产者连续发5条消息，其中一条报异常；
result：遇到异常立即ack(已消费的)，已消费的全部重回队列，最后队列消息数为5；

6.场景：1个消费者，prefetchCount=5，txSize=5，requeue=false，retry.enabled=true；
action: 生产者连续发5条消息，其中一条报异常；
result: 遇到异常本地重试，默认次数为3次，最后队列为空
例子：比如消息：1，2，3，4，5；3异常；result：1，2，3，3，3，4，5
注意：retry.enabled=true， requeue不生效；block后续消息的处理，直至重试结束；

7.场景：1个queue已经创建，此时需在queue上添加DLX；
注意：首先需要将此queue删除，否则会报错，删除之后重新创建；

8.场景：1个queue(DLX)，prefetchCount=5，txSize=5，requeue=false；
action：生产者连续发5条消息，其中一条异常；（1，2，3，4，5；3，异常）
result：死信队列(1, 2, 3), 正常消费(4, 5)





