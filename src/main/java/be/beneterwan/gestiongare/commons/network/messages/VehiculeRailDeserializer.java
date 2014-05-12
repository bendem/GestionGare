package be.beneterwan.gestiongare.commons.network.messages;

import be.beneterwan.gestiongare.commons.trains.Locomotive;
import be.beneterwan.gestiongare.commons.trains.VehiculeRail;
import be.beneterwan.gestiongare.commons.trains.Wagon;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

/**
 * @author bendem & Curlybear
 */
public class VehiculeRailDeserializer implements JsonDeserializer<VehiculeRail> {

    @Override
    public VehiculeRail deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        JsonObject serialized = je.getAsJsonObject();
        VehiculeRail result;
        if(serialized.has("puissance")) {
            result = new Locomotive(
                    serialized.get("puissance").getAsFloat(),
                    serialized.get("numero").getAsInt(),
                    serialized.get("anneeMiseEnService").getAsInt());
        } else {
            result = new Wagon(
                    serialized.get("longueur").getAsFloat(),
                    Wagon.Type.valueOf(serialized.get("type").getAsString()),
                    serialized.get("numero").getAsInt(),
                    serialized.get("anneeMiseEnService").getAsInt());
        }
        return result;
    }

}
