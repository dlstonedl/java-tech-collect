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

##消费端

default-requeue-rejected: 默认为true(重回队列)
建议：生产环境一般设置为false
含义：失败不重回队列(生产使用)

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



