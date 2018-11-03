package app.repository;

import app.connection.DBClient;
import app.model.User;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class UserRepository {

    public void createUser(User user) {
        DBObject userDb = userToDbObject(user);
        DBClient.getCollection("users").insert(userDb);
    }

    public User getUserById(int id) {
        DBObject query = BasicDBObjectBuilder.start().add("_id", id).get();
        DBObject userDb = DBClient.getCollection("users").findOne(query);
        return dbObjectToUser(userDb);
    }

    public void updateUser(User user) {
        DBObject query = BasicDBObjectBuilder.start().add("_id", user.getId()).get();
        DBObject userDb = userToDbObject(user);
        DBClient.getCollection("users").update(query, userDb);
    }

    public void deleteUser(User user) {
        DBObject query = BasicDBObjectBuilder.start().add("_id", user.getId()).get();
        DBClient.getCollection("users").remove(query);
    }

    private DBObject userToDbObject(User user) {
        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();

        docBuilder.append("_id", user.getId());
        docBuilder.append("name", user.getName());
        docBuilder.append("role", user.getRole());
        docBuilder.append("isEmployee", user.isEmployee());
        return docBuilder.get();
    }

    private User dbObjectToUser(DBObject object) {
        User user = null;
        if (object != null) {
            user = new User();
            user.setName((String) object.get("name"));
            user.setRole((String) object.get("role"));
            user.setId((Integer) object.get("_id"));
            user.setEmployee((Boolean) object.get("isEmployee"));
        }
        return user;
    }
}
