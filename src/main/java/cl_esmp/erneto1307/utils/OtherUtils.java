package cl_esmp.erneto1307.utils;

import java.util.ArrayList;

public class OtherUtils {

    public static void addRangeToList(int min, int max, ArrayList<Integer> list) {
        for (int i = min; i <= max; i++) {
            list.add(i);
        }

    }
}
