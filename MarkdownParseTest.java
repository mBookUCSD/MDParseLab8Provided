import static org.junit.Assert.*;
import org.junit.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.nio.file.NoSuchFileException;


public class MarkdownParseTest {
    @Test
    public void testFile1() throws IOException {
        String contents= Files.readString(Path.of("./test-file.md"));
        List<String> expect = List.of("https://something.com", "some-page.html");
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }
    @Test
    public void vimTest(){
	assertTrue(1+1==2);
    }
    @Test
    public void testFile2() throws IOException {
        String contents= Files.readString(Path.of("./test-file2.md"));
        List<String> expect = List.of("https://something.com", "some-page.html");
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }

    @Test
    public void testMissingCloseParen() {
        String contents= "[link title](a.com";
        List<String> expect = List.of();
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }

    @Test
    public void testSpaceAroundLink() {
        String contents= "[link title](   a.com   )";
        List<String> expect = List.of("a.com");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }
    @Test
    public void testSnip1() throws IOException, NoSuchFileException {

        ArrayList<String> correctOutput = new ArrayList<>();
        correctOutput.addAll(Arrays.asList("`google.com","google.com","ucsd.edu"));
        Path fileName= Path.of("lab8-snip1.md");
        // read the file contents into a string
	    String contents = Files.readString(fileName);
        // run getLinks on the contents of the file
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(correctOutput,links);
    }
    @Test
    public void testSnip2() throws IOException, NoSuchFileException {

        ArrayList<String> correctOutput = new ArrayList<>();
        correctOutput.addAll(Arrays.asList("a.com(())","example.com"));
        Path fileName= Path.of("lab8-snip2.md");
        // read the file contents into a string
	    String contents = Files.readString(fileName);
        // run getLinks on the contents of the file
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(correctOutput,links);
    }
    @Test
    public void testSnip3() throws IOException, NoSuchFileException {

        ArrayList<String> correctOutput = new ArrayList<>();
        correctOutput.addAll(Arrays.asList("https://ucsd-cse15l-w22.github.io/"));
        Path fileName= Path.of("lab8-snip3.md");
        // read the file contents into a string
	    String contents = Files.readString(fileName);
        // run getLinks on the contents of the file
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(correctOutput,links);
    }

}
