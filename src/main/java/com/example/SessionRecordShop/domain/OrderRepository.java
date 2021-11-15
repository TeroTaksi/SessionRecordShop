package com.example.SessionRecordShop.domain;

import org.springframework.data.repository.CrudRepository;


public interface OrderRepository extends CrudRepository<MyOrder, Long> {

}