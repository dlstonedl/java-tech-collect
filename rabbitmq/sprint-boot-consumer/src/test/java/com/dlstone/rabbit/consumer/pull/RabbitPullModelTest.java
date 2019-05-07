package com.dlstone.rabbit.consumer.pull;

import com.dlstone.rabbit.comsumer.pull.RabbitPullModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitPullModelTest {

    @Autowired
    private RabbitPullModel rabbitPullModel;

    @Test
    public void should_get_message_through_pull_model() {
        rabbitPullModel.pullMessage();
    }
}
