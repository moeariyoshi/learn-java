/* 
 * This uses a dataset from the GoodReads website. 
 * You can find the original data file at 
 *     https://www.kaggle.com/jealousleopard/goodreadsbooks 
 * I cut this down to 5 columns: isbn, authors, title, publisher, GoodReads rating.
 * I also cleaned up the data a bit; there were a few broken entries.
 * Note that the file is written with the UTF-8 character encoding. This is the default
 * for Python but needs to be specified for Java, as in 
 * 	new Scanner( new File(foo), "UTF-8" )
 */

package goodreads;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A BookFinder class holds mappings between ISBNs, titles, authors, and
 * publishers to books represented by a {@link BookData}.
 * 
 * @author Bob Geitz
 * @author Stephen Checkoway
 * @author Moe Ariyoshi
 * @version 2021-06-30
 * 
 * I have adhered to the Honor Code!
 * 
 */
public class BookFinder {
    private MyTreeMap<String, BookData> isbnToData;
    private MyTreeMap<String, ArrayList<BookData>> titleToData;
    private MyTreeMap<String, ArrayList<BookData>> authorToData;
    private MyTreeMap<String, ArrayList<BookData>> publisherToData;

    /**
     * Creates a BookFinder object by reading the data file at path.
     * 
     * This will be a comma-separated text file with 5 fields per line:
     * isbn,authors,title,publisher,rating
     * 
     * Multiple authors are separated by '/' characters: Frank Herbert/Domingo
     * Santos
     * 
     * @param path The file path for the input data file.
     * @throws FileNotFoundException 
     */
    public BookFinder(String path) throws FileNotFoundException {

        isbnToData = new MyTreeMap<String, BookData>();
        titleToData = new MyTreeMap<String, ArrayList<BookData>>();
        authorToData = new MyTreeMap<String, ArrayList<BookData>>();
        publisherToData = new MyTreeMap<String, ArrayList<BookData>>();
       
        /*
         * You need to open the data file with a "UTF-8" flag, as in
         * 
         * Scanner scan = new Scanner(new File(path), "UTF-8");
         *
         * For each line of the data file you should create a new BookData with the
         * relevant fields. Add the newly created BookData to isbnToData with the isbn
         * as the key.
         * 
         * For the other maps, add the BookData to the ArrayList stored in the map with
         * the appropriate key (title, author, or publisher). If a book has multiple
         * authors, then each author's list should contain the BookData.
         */
        
        
        
        Scanner scan = null;
        
        try {
            scan = new Scanner(new File(path), "UTF-8");
        } catch (FileNotFoundException e){
            System.err.println(e);
        } finally {
            
            while (scan.hasNextLine()) {
                Scanner line = new Scanner(scan.nextLine()).useDelimiter(",");
                
                String isbn = line.next();
                
                //Scanner author = new Scanner(authors).useDelimiter("/");
                
                String[] authors = line.next().split("/");
                String title = line.next();
                String publisher = line.next();
                float rating = Float.parseFloat(line.next());
                
                line.close();
         
                //isbn
                BookData book = new BookData(isbn, authors, title, publisher, rating);
                isbnToData.put(isbn, book);
                
                //title
                ArrayList<BookData> booksByTitle;
                
                //multiple books with the same title? 
                if (titleToData.get(title) == null) {
                    booksByTitle = new ArrayList<BookData>();
                } else {
                    booksByTitle = titleToData.get(title);
                }
     
                booksByTitle.add(book);
                titleToData.put(title, booksByTitle);
                
                //authors
               
                for (int i=0; i<authors.length; i++) {
                    String author = authors[i];
                    
                    ArrayList<BookData> booksByAuthor;
                    
                    if (authorToData.get(author) == null) {
                        booksByAuthor = new ArrayList<BookData>();
                    } else {
                        booksByAuthor = authorToData.get(author);
                    }
                    booksByAuthor.add(book);
                    authorToData.put(author, booksByAuthor);
                }
                
              //add to publishers
                ArrayList<BookData> booksByPublisher;
                if (publisherToData.get(publisher) == null) {
                    booksByPublisher = new ArrayList<BookData>();
                } else {
                    booksByPublisher = publisherToData.get(publisher);
                }
                booksByPublisher.add(book);
                publisherToData.put(title, booksByPublisher);
            }
            
            scan.close();
        }
    }

    /**
     * Returns a list of books written by the author.
     * 
     * @param author The author to search for.
     * @return A list of {@link BookData} objects written by author.
     */
    public List<BookData> searchByAuthor(String author) {
        return authorToData.get(author);
    }

    /**
     * Returns a list of books with the exact title.
     * 
     * @param title The title to search for.
     * @return A list of {@link BookData} objects with the given title.
     */
    public List<BookData> searchByTitle(String title) {
        return titleToData.get(title);
    }

    /**
     * Returns a list of books published by publisher.
     * 
     * @param publisher The publisher to search for.
     * @return A list of {@link BookData} published by the publisher.
     */
    public List<BookData> searchByPublisher(String publisher) {
        return publisherToData.get(publisher);
    }

    /**
     * Returns a book corresponding to an ISBN, or null if no such book is in the
     * database.
     * 
     * @param isbn The ISBN to search for.
     * @return A {@link BookData} corresponding to the isbn, or null.
     */
    public BookData searchByIsbn(String isbn) {
        return isbnToData.get(isbn);
    }
}
