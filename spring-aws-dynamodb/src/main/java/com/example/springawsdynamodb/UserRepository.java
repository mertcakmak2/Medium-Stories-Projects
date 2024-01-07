package com.example.springawsdynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Log4j2
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
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":v1",new AttributeValue().withS(id));

        DynamoDBQueryExpression<User> queryExpression = new DynamoDBQueryExpression<User>()
                .withKeyConditionExpression("id = :v1")
                .withExpressionAttributeValues(eav);

        List<User> users = dynamoDBMapper.query(User.class,queryExpression);
        return users.get(0);
        // return dynamoDBMapper.load(User.class, id);
    }

    void scanUserByBeginsWithUserName(String term){
        HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":v1", new AttributeValue().withS(term));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("begins_with(username,:v1)")
                .withExpressionAttributeValues(eav);

        List<User> users =  dynamoDBMapper.scan(User.class, scanExpression);
       log.info(users.size());
    }

    void scanUserByAgeLessThan(int age){
        HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":v1", new AttributeValue().withN(String.valueOf(age)));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("age < :v1")
                .withExpressionAttributeValues(eav);

        List<User> users =  dynamoDBMapper.scan(User.class, scanExpression);
        log.info(users.size());
    }

    public String deleteUserById(String id, String username) {
        //User user = dynamoDBMapper.load(User.class, id);
        User user = dynamoDBMapper.load(User.class, id, username);
        dynamoDBMapper.delete(user);
        return "User deleted!";
    }

    public String updateUser(String id, User user) {
        var existUser = findUserById(id);
        existUser.setAge(user.getAge());
        existUser.setSurname(user.getSurname());
        existUser.setAddress(user.getAddress());
        dynamoDBMapper.save(existUser);
        return id;
    }
}