import java.util.*;

public class HostelDelTime{
public static Map<String,Integer> hostelDelTime = new HashMap<String,Integer>(){{
    put("GANDHI",12);
    put("KRISHNA",12);
    put("RAM",13);
    put("BUDH",13);
    put("SHANKAR",14);
    put("VYAS",14);
    put("VK",15);
    put("BHAGIRATH",15);
    put("SR",16);
    put("CVR",16);
    put("MALVIYA",17);
    put("MEERA",17);
    put("RANA PRATAP",18);
    put("ASHOK",18);
}};

public static Map<String, String> hostelDropDay = new HashMap<>() {{
    put("GANDHI","SUNDAY");
    put("KRISHNA","SUNDAY");
    put("RAM","MONDAY");
    put("BUDH","MONDAY");
    put("SHANKAR","TUESDAY");
    put("VYAS","TUESDAY");
    put("VK","WEDNESDAY");
    put("BHAGIRATH","WEDNESDAY");
    put("SR","THURSDAY");
    put("CVR","FRIDAY");
    put("MALVIYA","THURSDAY");
    put("MEERA","SATURDAY");
    put("RANA PRATAP","FRIDAY");
    put("ASHOK","FRIDAY");
}};
}