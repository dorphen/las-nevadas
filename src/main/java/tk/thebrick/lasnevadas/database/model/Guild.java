package tk.thebrick.lasnevadas.database.model;

import org.bson.types.ObjectId;

public final class Guild {

    private ObjectId id;
    private long guildID;

    public Guild() {}

    public Guild(long guildID) {
        this.guildID = guildID;
    }

    public ObjectId getID() {
        return id;
    }

    public void setID(final ObjectId id) {
        this.id = id;
    }

    public long getGuildID() {
        return guildID;
    }

    public void setGuildID(final long guildID) {
        this.guildID = guildID;
    }

}
