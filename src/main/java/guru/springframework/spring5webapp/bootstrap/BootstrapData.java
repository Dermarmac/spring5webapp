package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started in Bootstrap");

        Author eric = new Author("Erric", "Evans");
        Book ddd = new Book("Domain Driven Design","3123123");
        Publisher publisher = new Publisher("McPublishing", "1600 Pen state ave", "Athens","GA", 12353 );
      //  publisherRepository.save(publisher);

        saveDataToDB(publisher, eric, ddd);

        Author rod = new Author("Rod","Johnson");
        Book noEJB = new Book("J2EE Development without EJB","2134352321");

        saveDataToDB(publisher, rod, noEJB);

        System.out.println("Number of Books:" + bookRepository.count());
        System.out.println("Publishers added: " +  publisherRepository.count());
        System.out.println("Publisher #books" + publisher.getBooks().size());

    }

    private void saveDataToDB(Publisher publisher, Author author, Book book) {
        publisherRepository.save(publisher);

        author.getBooks().add(book);
        book.getAuthors().add(author);
        book.setPublisher(publisher);
        publisher.getBooks().add(book);

        authorRepository.save(author);
        bookRepository.save(book);
    }
}
