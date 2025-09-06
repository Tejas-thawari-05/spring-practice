package com.mongoDB.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.entity.mongoDB.MongoDB;

public interface MongoDBRepository extends MongoRepository<MongoDB, Integer>{

}
