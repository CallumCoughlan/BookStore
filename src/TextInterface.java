public class TextInterface {

    public TextInterface() {

    }
    public void userOption() {
        System.out.println("To login as Customer type \"Customer\", To login as Employee type \"Employee\"");
    }

    public void customerMenu() {
        System.out.println("These are the actions you can preform:" +
                "\n\"DisplayBooks\": Display all books" +
                "\n\"SearchBooks\": Display books that match search" +
                "\n\"AddBook\": Add book to cart" +
                "\n\"RemoveBook\": Remove book from cart" +
                "\n\"Checkout\": Creates order" +
                "\n\"Exit\": Ends Program");
    }

    public void employeeMenu() {
        System.out.println("These are the actions you can preform:" +
                "\n\"AddBook\": Add book to catalog" +
                "\n\"RemoveBook\": Remove book from catalog" +
                "\n\"AddPublisher\": Add publisher to database" +
                "\n\"RemovePublisher\": Remove publisher from database" +
                "\n\"AddAuthor\": Add author to database" +
                "\n\"RemoveAuthor\": Remove author from database" +
                "\n\"Exit\": Ends Program");
    }

    public void invalidCommand() {
        System.out.println("The entered command is not valid");
    }
}
