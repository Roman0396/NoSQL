package app.connection;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;

public class DBClient {

    private static MongoClient mongo;
    private static DB db;

    static {
        try {
            mongo = new MongoClient();
            db = mongo.getDB("myDb");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static DBCollection getCollection(String collectionName) {
        return db.getCollection(collectionName);
    }

    public void closeConnection() {
        mongo.close();
    }
}
