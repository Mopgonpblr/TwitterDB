package logic;

import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

public class FileParse {
    public static ArrayList<String> createArrayList(Resource res) throws IOException {
        ArrayList<String> list = new ArrayList<>();

        Scanner scanner = new Scanner(res.getContentAsString(Charset.defaultCharset()));
        while (scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }

        scanner.close();
        return list;
    }
}
