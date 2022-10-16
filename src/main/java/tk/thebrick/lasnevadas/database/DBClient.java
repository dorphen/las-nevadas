package tk.thebrick.lasnevadas.database;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.thebrick.lasnevadas.Config;
import tk.thebrick.lasnevadas.database.model.Guild;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class DBClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBClient.class);
    private static final MongoDatabase database;


    static {
        MongoCredential credential = MongoCredential.createCredential(Config.get("DB_USER"), Config.get("DB_NAME"), Config.get("DB_PASSWORD").toCharArray());
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClient mongoClient = MongoClients.create(
                MongoClientSettings.builder()
                        .applyToClusterSettings(builder ->
                                builder.hosts(List.of(new ServerAddress(Config.get("DB_HOST"), Integer.parseInt(Config.get("DB_PORT"))))))
                        // .credential(credential)
                        .codecRegistry(pojoCodecRegistry)
                        .build());
        LOGGER.info("Connected to database [{}].", Config.get("DB_NAME"));

        database = mongoClient.getDatabase(Config.get("DB_NAME"));
        LOGGER.debug("Credentials: {}", credential);

    }

    public static MongoCollection<Guild> getGuildCollection() {
        return database.getCollection("guild", Guild.class);
    }

    public static void addGuild(long guildID) {
        if (DBClient.getGuildCollection().countDocuments(eq("guildID", guildID)) > 0) {
            LOGGER.warn("Guild {} already in database.", guildID);
            return;
        }

        Guild guild = new Guild(guildID);  // TODO dateJoined
        DBClient.getGuildCollection().insertOne(guild);
        LOGGER.info("Added guild to DB: [{}].", guildID);
    }

    public static Guild getGuild(long guildID) {
        return DBClient.getGuildCollection().find(eq("guildID", guildID)).first();
    }
}
