package com.pingr.Accounts.Accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

// forma de se comunicar com o Kakfa
// eventos que vamos publicar
// - accountCreated
// - accountUpdated
// - accountDeleted

@Service
public class ProducerService {

    @Value(value = "${topics.acc_created}")
    private String accountCreatedTopic;

    @Autowired // injeção de dependências
    private KafkaTemplate<String, Object> template;

    public void emitAccountCreatedEvent(Account account) {
        this.template.send(
                this.accountCreatedTopic,
                AccountCreatedEvent.of(account));
    }
}
