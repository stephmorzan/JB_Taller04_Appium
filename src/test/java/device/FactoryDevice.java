package device;

import java.util.HashMap;
import java.util.Map;

public class FactoryDevice {

    public static IDevice make (String type){
        Map<String,IDevice> deviceMap = new HashMap<>();
        deviceMap.put("android", new Android());
        deviceMap.put("ios", new IoS());

        return deviceMap.containsKey(type.toLowerCase())?
                deviceMap.get(type.toLowerCase()):
                new Android();
    }
}
