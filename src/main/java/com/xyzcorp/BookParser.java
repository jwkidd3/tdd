package com.xyzcorp;


import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by danno on 9/30/16.
 */
public class BookParser {
    public static Book parseLine(String data, String delimeter) {
        if (data == null) throw new IllegalArgumentException("Null String");
        if ("".equals(data.trim())) throw new IllegalArgumentException("Empty String");
        String[] items  = data.split("~");
        if (items.length != 3) throw new IllegalArgumentException("Bad Data");
        if ("".equals(items[0])) throw new IllegalArgumentException("Bad Data");
        return new Book(items[0], items[1], LocalDate.parse(items[2]));
    }

    public static List<Book> parseBufferedReader(BufferedReader bufferedReader) throws IOException {
        List<Book> result = new ArrayList<>();
        String next = "";
        while((next = bufferedReader.readLine()) != null) {
            result.add(parseLine(next, "~"));
        }
        return result;
    }
}
