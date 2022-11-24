import java.util.*;

public class HostelDelTime{
public static Map<String,Integer> hostelDelTime = new HashMap<String,Integer>(){{
    put("Gandhi",12);
    put("Krishna",12);
    put("Ram",13);
    put("Budh",13);
    put("Shankar",14);
    put("Vyas",14);
    put("VK",15);
    put("Bhagirath",15);
    put("SR",16);
    put("CVR",16);
    put("Malviya",17);
    put("Meera",17);
    put("Rana Pratap",18);
    put("Ashok",18);
}};

public static Map<String, String> hostelDropDay = new HashMap<>() {{
    put("Gandhi","Sunday");
    put("Krishna","Sunday");
    put("Ram","Monday");
    put("Budh","Monday");
    put("Shankar","Tuesday");
    put("Vyas","Tuesday");
    put("VK","Wednesday");
    put("Bhagirath","Wednesday");
    put("SR","Thursday");
    put("CVR","Friday");
    put("Malviya","Thursday");
    put("Meera","Saturday");
    put("Rana Pratap","Friday");
    put("Ashok","Friday");
}};
}