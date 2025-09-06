package com.storage;

import com.entity.Order;
import net.openhft.chronicle.queue.ChronicleQueue;
import net.openhft.chronicle.queue.ExcerptAppender;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
public class OrderStorageService {
    private final ChronicleQueue queue;
    private final ExcerptAppender appender;

    public OrderStorageService() {
        this.queue = ChronicleQueue.singleBuilder(Path.of("order-queue")).build();
        this.appender = queue.acquireAppender();  // ✅ Correct method for Chronicle Queue 5.27ea3
    }

    public void saveOrder(Order order) {
        appender.writeDocument(w -> w.write("order").object(order));  // ✅ Writing the order object
    }
}
