package com.example.mongodbtest.repository;

import com.example.mongodbtest.entity.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface MemberRepository extends MongoRepository<Member,String> {
    Member findBy_id(String _id);
}
