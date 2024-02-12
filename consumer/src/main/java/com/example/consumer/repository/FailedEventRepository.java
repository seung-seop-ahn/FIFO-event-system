package com.example.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.consumer.domain.FailedEvent;

@Repository
public interface FailedEventRepository extends JpaRepository<FailedEvent, Long> {
}
