import java.util.ArrayList;
import java.util.List;

interface IBook {
    String getBookId();
    String getTitle();
    String getAuthor();
    boolean isAvailable();
    void setAvailable(boolean available);
}

class Book implements IBook {
    private String bookId;
    private String title;
    private String author;
    private boolean available;

    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getBookId() {
        return bookId;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
}

interface IMember {
    String getMemberId();
    String getName();
    void borrowBook(IBook book);
    void returnBook(IBook book);
    void listBorrowedBooks();
}

class Member implements IMember {
    private String memberId;
    private String name;
    private List<IBook> borrowedBooks;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }
    public String getMemberId() {
        return memberId;
    }
    public String getName() {
        return name;
    }
    public void borrowBook(IBook book) {
        if (book.isAvailable()) {
            borrowedBooks.add(book);
            book.setAvailable(false);
            System.out.println(name + " borrowed \"" + book.getTitle() + "\"");
        } else {
            System.out.println("Book \"" + book.getTitle() + "\" is not available.");
        }
    }
    public void returnBook(IBook book) {
        if (borrowedBooks.remove(book)) {
            book.setAvailable(true);
            System.out.println(name + " returned \"" + book.getTitle() + "\"");
        } else {
            System.out.println("Book \"" + book.getTitle() + "\" was not borrowed by " + name);
        }
    }
    public void listBorrowedBooks() {
        System.out.println("Books borrowed by " + name + ":");
        for (IBook book : borrowedBooks) {
            System.out.println("- " + book.getTitle());
        }
    }
}

interface IBorrowingService {
    void borrowBook(IMember member, IBook book);
    void returnBook(IMember member, IBook book);
}

class BorrowingService implements IBorrowingService {
    public void borrowBook(IMember member, IBook book) {
        member.borrowBook(book);
    }
    public void returnBook(IMember member, IBook book) {
        member.returnBook(book);
    }
}

class Main {
    public static void main(String[] args) {
        IBook book1 = new Book("B001", "The Catcher in the Rye", "J.D. Salinger");

        IMember member1 = new Member("M001", "John Doe");
        IMember member2 = new Member("M002", "Jane Smith");

        IBorrowingService borrowingService = new BorrowingService();

        borrowingService.borrowBook(member1, book1);
        member1.listBorrowedBooks();

        borrowingService.borrowBook(member2, book1);

        borrowingService.returnBook(member1, book1);
        member1.listBorrowedBooks();

        borrowingService.borrowBook(member2, book1);
        member2.listBorrowedBooks();
    }
}