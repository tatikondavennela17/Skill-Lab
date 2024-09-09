class Book {
    private String title;
    private boolean isBorrowed;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrowBook() {
        this.isBorrowed = true;
    }

    public void returnBook() {
        this.isBorrowed = false;
    }
}
class Member {
    private String name;
    private int memberId;

    public Member(String name, int memberId) {
        this.name = name;
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public int getMemberId() {
        return memberId;
    }
}
interface Borrowable {
    void borrow(Book book);
    void returnBook(Book book);
}
class Library implements Borrowable {
    public void borrow(Book book) {
        if (!book.isBorrowed()) {
            book.borrowBook();
            System.out.println(book.getTitle() + " has been borrowed.");
        } else {
            System.out.println(book.getTitle() + " is already borrowed.");
        }
    }
    public void returnBook(Book book) {
        if (book.isBorrowed()) {
            book.returnBook();
            System.out.println(book.getTitle() + " has been returned.");
        } else {
            System.out.println(book.getTitle() + " was not borrowed.");
        }
    }
}
class FictionBook extends Book {
    public FictionBook(String title, String author, String isbn) {
        super(title, author, isbn);
    }
}
class NonFictionBook extends Book {
    public NonFictionBook(String title, String author, String isbn) {
        super(title, author, isbn);
    }
}
class LibrarySystem {
    private Borrowable library;

    public LibrarySystem(Borrowable library) {
        this.library = library;
    }

    public void borrowBook(Book book, Member member) {
        System.out.println(member.getName() + " is borrowing " + book.getTitle());
        library.borrow(book);
    }

    public void returnBook(Book book, Member member) {
        System.out.println(member.getName() + " is returning " + book.getTitle());
        library.returnBook(book);
    }
}
 class Main {
    public static void main(String[] args) {
        Book book1 = new FictionBook("The Hobbit", "J.R.R. Tolkien", "12345");
        Book book2 = new NonFictionBook("Sapiens", "Yuval Noah Harari", "67890");

        Member member1 = new Member("Alice", 1);
        Member member2 = new Member("Bob", 2);

        Borrowable library = new Library();
        LibrarySystem librarySystem = new LibrarySystem(library);

        librarySystem.borrowBook(book1, member1);
        librarySystem.borrowBook(book2, member2);
        librarySystem.borrowBook(book1, member2);
        librarySystem.returnBook(book1, member1);
        librarySystem.returnBook(book2, member2);
    }
}