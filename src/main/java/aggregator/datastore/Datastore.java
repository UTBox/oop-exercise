package aggregator.datastore;

import java.util.ArrayList;
import java.util.List;

public class Datastore {

    private static List<PaymentRecord> records = new ArrayList<>();

    public static void save(PaymentRecord record) {
        records.add(record);
    }

    public static void showAllRecords(){
        System.out.println("payment records:" + records);
    }

}
