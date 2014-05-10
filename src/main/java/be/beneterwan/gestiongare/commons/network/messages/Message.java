package be.beneterwan.gestiongare.commons.network.messages;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import network.NetworkStringSender;

/**
 * @author bendem & Curlybear
 */
abstract public class Message {

    private final Type type;

    public Message(Type type) {
        this.type = type;
    }

    public void send(NetworkStringSender sender) {
        sender.sendString(serialize());
    }

    public String serialize() {
        return new Gson().toJson(this);
    }

    public static Message deserialize(String serialized) {
        Gson gson = new Gson();
        // Get JsonObject from String
        JsonObject json = (JsonObject) new JsonParser().parse(serialized);
        // Get Message type to be able to deserialize using the correct class
        Type type = Type.valueOf(json.get("type").getAsString());
        switch(type) {
            case Ack:
                return gson.fromJson(serialized, Ack.class);
            case TrainTransited:
                return gson.fromJson(serialized, TrainTransited.class);
            case TrainComing:
                return gson.fromJson(serialized, TrainComing.class);
            default:
                throw new AssertionError(type.name());
        }

    }

    public Type getType() {
        return type;
    }

    public enum Type {
        Ack,
        TrainTransited,
        TrainComing
    }

}
