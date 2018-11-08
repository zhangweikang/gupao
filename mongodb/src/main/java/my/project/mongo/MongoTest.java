package my.project.mongo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.Block;
import com.mongodb.Function;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * mongo
 *
 * @author ZhangWeiKang
 * @create 2018/9/6
 */
public class MongoTest {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws JsonProcessingException {
        MongoClient mongoClient = MongoClients.create("mongodb://master:27017");

        mongoClient.listDatabaseNames().forEach(new Block<String>() {
            @Override
            public void apply(String s) {

            }
        });
        //MongoClient mongoClient = new MongoClient("192.168.56.200");
        /*MongoIterable<String> strings =
                mongoClient.listDatabaseNames();
        for (String string : strings) {
            System.out.println("databaseName = " + string);
        }*/

        MongoDatabase myNewDatabase = mongoClient.getDatabase("myNewDatabase");

        myNewDatabase.listCollections().forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {

            }
        });
        /*MongoIterable<String> strings1 = myNewDatabase.listCollectionNames();
        for (String string : strings1) {
            System.out.println("CollectionName = " + string);
        }*/


        MongoCollection<Document> collection =
                myNewDatabase.getCollection("ceshi");

        /*Document doc = new Document("name", "MongoDB")
                .append("type", "database")
                .append("count", 1)
                .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
                .append("info", new Document("x", 203).append("y", 102));
        collection.insertOne(doc);*/

        //System.out.println("collection.countDocuments() = " + collection.countDocuments());

        collection.find().map(new Function<Document, Document>() {
            @Override
            public Document apply(Document document) {
                return document;
            }
        }).forEach(new Block<Document>() {
            @Override
            public void apply(Document o) {
                System.out.println("first.toJson() = " + o.toJson());
            }
        });

        //System.out.println("first.toJson() = " + first.toJson());
    }
}
