package com.muli.mongo.normal;

import com.mongodb.*;

/**
 * Created by muli on 15-5-27.
 */
public class OperateMongo {
    public static void main(String args[]){
        insertMongo();
        searchAllMongo();
        System.out.println("----------------");
        updataMongo();
        searchAllMongo();
        System.out.println("----------------");
        searchOneMongo();
        System.out.println("----------------");
        delMongo();
        System.out.println("----------------");
        searchAllMongo();
    }

    public static void insertMongo(){
        try {
            MongoClient mongoClient = new MongoClient("10.202.26.41", 8099);
            DB db = mongoClient.getDB("dxbb_test");
//            boolean auth = db.authenticate(null, null);
            DBCollection coll = db.getCollection("mycoll");
            BasicDBObject document = new BasicDBObject();
            document.put("id", 100);
            coll.insert(document);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void searchAllMongo(){
        try {
            MongoClient mongoClient = new MongoClient("10.202.26.41", 8099);
            DB db = mongoClient.getDB("dxbb_test");
            DBCollection coll = db.getCollection("mycoll");
            DBCursor cursor = coll.find();

            while (cursor.hasNext()){
                System.out.println(cursor.next());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * search 某一个 id
     */
    public static void searchOneMongo(){
        try {
            MongoClient mongoClient = new MongoClient("10.202.26.41", 8099);
            DB db = mongoClient.getDB("dxbb_test");
            DBCollection collection = db.getCollection("mycoll");
            BasicDBObject queryDocument = new BasicDBObject();
            queryDocument.put("name", "test");
            DBCursor cursor = collection.find(queryDocument);
            while (cursor.hasNext()){
                System.out.println(cursor.next());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void updataMongo(){
        try {
            MongoClient mongoClient = new MongoClient("10.202.26.41", 8099);
            DB db = mongoClient.getDB("dxbb_test");
            DBCollection collection = db.getCollection("mycoll");
            BasicDBObject neDocument = new BasicDBObject();
            neDocument.put("id", 100);
            neDocument.put("name", "test");
            collection.update(new BasicDBObject().append("id", 100), neDocument);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void delMongo(){
        try {
            MongoClient mongoClient = new MongoClient("10.202.26.41", 8099);
            DB db = mongoClient.getDB("dxbb_test");
            DBCollection collection = db.getCollection("mycoll");
            BasicDBObject delDocument = new BasicDBObject();
            delDocument.put("name", "test");
            collection.remove(delDocument);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
