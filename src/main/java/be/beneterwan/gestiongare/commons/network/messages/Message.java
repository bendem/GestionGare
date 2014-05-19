package be.beneterwan.gestiongare.commons.network.messages;

import be.beneterwan.gestiongare.commons.trains.VehiculeRail;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
        GsonBuilder gsonBuilder = new GsonBuilder();
        // Register a deserializer to deserialize Wagon's and Locomotive's
        gsonBuilder.registerTypeAdapter(VehiculeRail.class, new VehiculeRailDeserializer());

        Gson gson = gsonBuilder.create();
        // Get JsonObject from String
        JsonObject json = (JsonObject) new JsonParser().parse(serialized);
        // Get Message type to be able to deserialize using the correct class
        Type type = Type.valueOf(json.get("type").getAsString());
        switch(type) {
            case Ack:
                return gson.fromJson(serialized, AckMessage.class);
            case Stored:
                return gson.fromJson(serialized, StoredMessage.class);
            case TrainTransited:
                return gson.fromJson(serialized, HoraireTrainTransitedMessage.class);
            case TrainComing:
                return gson.fromJson(serialized, HoraireTrainComingMessage.class);
            case CreateNewTrain:
                return gson.fromJson(serialized, CreateNewTrainMessage.class);
            case CreatedNewTrain:
                return gson.fromJson(serialized, CreatedNewTrainMessage.class);
            default:
                throw new AssertionError(type.name());
        }

    }

    public Type getType() {
        return type;
    }

    public enum Type {
        Ack,
        Stored,
        TrainTransited,
        TrainComing,
        CreateNewTrain,
        CreatedNewTrain
    }

}
