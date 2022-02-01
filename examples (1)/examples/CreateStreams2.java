import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CreateStreams2 {
    public static void main(String[] args) {
        List<Integer> myList = new ArrayList<Integer>();
        myList.add(1);
        myList.add(5);
        myList.add(8);

        Stream<Integer> myStream = myList.stream();



        Integer[] myArray = {1, 5, 8};

// Convert it into a Stream
        Stream<Integer> myStream2 = Arrays.stream(myArray);
    }
}
