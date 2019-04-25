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

default-requeue-rejected: false
含义：失败不重回队列(生产使用)
