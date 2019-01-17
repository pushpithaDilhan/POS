package com.pos.util;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Iterator;

public class MongoConnector {

    private static MongoDatabase mongoDatabase;

    public MongoConnector(){
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("pos");
    }

    public static MongoDatabase getMongoDatabaseConnector(){
        return mongoDatabase;
    }

}
