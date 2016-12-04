package com.xyzcorp;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.*;
import java.net.URL;
import java.nio.Buffer;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BookParserTest {

    @Test
    public void testParsePerfectLine() {
        String data = "Mimansha Bhargav~Lost Symbol~2015-01-05";
        Book book = BookParser.parseLine(data, "~");
        assertThat(book.getName()).isEqualTo("Mimansha Bhargav");
        assertThat(book.getTitle()).isEqualTo("Lost Symbol");
        assertThat(book.getCheckoutDate()).isEqualTo(LocalDate.of(2015, 1, 5));
    }

    @Test
    public void testParsePerfectLineDifferentData() {
        String data = "Vijay Kumar~Digital Fortress~2016-03-01";
        Book book = BookParser.parseLine(data, "~");
        assertThat(book.getName()).isEqualTo("Vijay Kumar");
        assertThat(book.getTitle()).isEqualTo("Digital Fortress");
        assertThat(book.getCheckoutDate()).isEqualTo(LocalDate.of(2016, 3, 1));
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testEmptyString() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Empty String");
        BookParser.parseLine("", "~");
    }

    @Test
    public void testEmptyStringWithSpaces() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Empty String");
        BookParser.parseLine("        ", "~");
    }

    @Test
    public void testNull(){
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Null String");
        BookParser.parseLine(null, "~");
    }

    @Test
    public void testBadDataTwoItemsInsteadOf3() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Bad Data");

        String data = "Vijay Kumar~Digital Fortress";
        BookParser.parseLine(data, "~");
    }

    @Test
    public void testBadDataNoDelimeter() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Bad Data");

        String data = "Vijay Kumar~~";
        BookParser.parseLine(data, "~");
    }

    @Test
    public void testBadDataBadDate() {
        thrown.expect(DateTimeParseException.class);
        thrown.expectMessage("Text 'Digital Fortress' could not be parsed at index 0");

        String data = "Vijay Kumar~~Digital Fortress";
        BookParser.parseLine(data, "~");
    }

    @Test
    public void testBadDataBadDateBadMonth() {
        thrown.expect(DateTimeParseException.class);
        thrown.expectMessage("Invalid value for MonthOfYear (valid values 1 - 12): 13");

        String data = "Vijay Kumar~Digital Fortress~2016-13-30";
        BookParser.parseLine(data, "~");
    }

    @Test
    public void testBadDataBadDateBadDay() {
        thrown.expect(DateTimeParseException.class);
        thrown.expectMessage("Invalid value for DayOfMonth (valid values 1 - 28/31): 32");

        String data = "Vijay Kumar~Digital Fortress~2016-11-32";
        BookParser.parseLine(data, "~");
    }

    @Test
    public void testBadDataRaysTheory() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Bad Data");

        String data = "~Digital Fortress~2015-03-19";
        BookParser.parseLine(data, "~");
    }

    //You probably done yet....

    @Test
    public void testParseInputStream() throws IOException {
        String data = "Daniel Hinojosa~Beautiful Flowers~2013-10-20\n" +
                "Venkat Subramaniam~Function Programming~2015-03-10\n" +
                "Brian Sletten~A Supposedly Fun Thing I Will Never Do Again~2015-02-12\n" +
                "Beth Brown~The Leftovers~2013-03-31";
        Reader reader = new StringReader(data);
        BufferedReader bufferedReader = new BufferedReader(reader);
        List<Book> bookList = BookParser.parseBufferedReader(bufferedReader);
        assertThat(bookList).hasSize(4);
    }

    @Test
    public void testParseInputStreamFromClasspath() throws IOException {
        InputStream is = getClass().getResourceAsStream("/library.txt");
        Reader reader = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(reader);
        List<Book> bookList = BookParser.parseBufferedReader(bufferedReader);
        assertThat(bookList).hasSize(16);
    }

    @Test
    public void testParseInputStreamFromURL() throws IOException {
        URL url = new URL("https://raw.githubusercontent.com/dhinojosa/tdd20160929/master/src/main/resources/library.txt");

        Reader reader = new InputStreamReader(url.openConnection().getInputStream());
        BufferedReader bufferedReader = new BufferedReader(reader);
        List<Book> bookList = BookParser.parseBufferedReader(bufferedReader);
        assertThat(bookList).hasSize(16);
    }
}
