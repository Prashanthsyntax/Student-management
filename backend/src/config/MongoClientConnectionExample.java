import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class MongoClientConnectionExample {
    public static void main(String[] args) {
        String connectionString = "mongodb+srv://prashanthpendem2323:pendem2323@cluster0.jaswr.mongodb.net/?appName=Cluster0";

        // Configure MongoClientSettings with server API version
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();

        // Create a new client and connect to the server
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            try {
                // Send a ping to confirm a successful connection
                MongoDatabase database = mongoClient.getDatabase("StudentDB");  // Creating database called "StudentDB"

                // Create or get collection
                MongoCollection<Document> collection = database.getCollection("students");  // Creating collection called "students"

                // Create a document (record) to insert into the collection
                Document student = new Document("name", "Prashanth")
                        .append("rollNumber", "123456")
                        .append("department", "CSE")
                        .append("email", "prashanth@example.com")
                        .append("age", 21);

                // Insert the document into the collection
                collection.insertOne(student);
                System.out.println("Document inserted successfully!");

                // Optional: Fetch the document to verify insertion
                Document fetchedStudent = collection.find().first();
                System.out.println("Inserted Document: " + fetchedStudent);

            } catch (MongoException e) {
                e.printStackTrace();
            }
        }
    }
}
