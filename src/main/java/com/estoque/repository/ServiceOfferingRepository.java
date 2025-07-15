package com.estoque.repository;

import com.estoque.model.ServiceOffering;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceOfferingRepository extends JpaRepository<ServiceOffering, Long> {
}
