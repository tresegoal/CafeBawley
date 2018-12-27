package cm.rst.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class USConstants {

    public final static String Cameroun = "CM";

    public final static Map<String, String> mapOfUSStates = new HashMap<String, String>() {
        {
            put("BJ", "Benin");
            put("CD", "RDC");
            put("CF", "Centrafrique");
            put("CM", "Cameroun");
            put("CI", "Côte d'Ivoire");
            put("NG", "Nigéria");
        }
    };

    public final static List<String> listOfUSStatesCode = new ArrayList<>(mapOfUSStates.keySet());
    public final static List<String> listOfUSStatesName = new ArrayList<>(mapOfUSStates.values());
}
