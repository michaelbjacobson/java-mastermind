import java.util.Arrays;
import java.util.Random;

class CodeCreator {

    String newCode() {
        int[] codeArray = new Random().ints(0, 6).limit(4).toArray();
        return Arrays.toString(codeArray)
                .replaceAll("[, \\[\\]]", "");
    }

}
