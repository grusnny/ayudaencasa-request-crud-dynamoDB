package com.ayudaencasa.request.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.ayudaencasa.request.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

@Repository
public class RequestRepository {

    @Autowired
    private DynamoDBMapper mapper;

    public Request addRequest(Request request) {
        mapper.save(request);
        return request;
    }

    public Request findRequestById(String wId) {
        return mapper.load(Request.class, wId);
    }

    public PaginatedScanList<Request> findAllRequests() {
        return mapper.scan(Request.class, new DynamoDBScanExpression());
    }

    public String deleteRequest(Request request) {
        mapper.delete(request);
        return "Solicitud eliminada";
    }

    public String editRequest(Request request) {
        mapper.save(request, buildExpression(request));
        return "Registro actualizado";
    }

    private DynamoDBSaveExpression buildExpression(Request request) {

        DynamoDBSaveExpression dynamoDBSaveExpression = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> expectedMap = new HashMap<>();
        expectedMap.put("wId", new ExpectedAttributeValue(new AttributeValue().withS(request.getwId())));
        dynamoDBSaveExpression.setExpected(expectedMap);
        return dynamoDBSaveExpression;
    }
}
