package com.example.springawsdynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final DynamoDBMapper dynamoDBMapper;

    public UserRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public User insertUser(User user) {
        dynamoDBMapper.save(user);
        return user;
    }

    public User findUserById(String id) {
        return dynamoDBMapper.load(User.class, id);
    }

    public String deleteUserById(String id) {
        User emp = dynamoDBMapper.load(User.class, id);
        dynamoDBMapper.delete(emp);
        return "Employee Deleted!";
    }

    public String updateUserById(String id, User user) {
        dynamoDBMapper.save(user,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("id",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(id)
                                )));
        return id;
    }
}