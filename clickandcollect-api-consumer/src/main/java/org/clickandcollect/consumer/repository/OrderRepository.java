package org.clickandcollect.consumer.repository;

import org.clickandcollect.model.entity.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<ClientOrder, Long> {
}
