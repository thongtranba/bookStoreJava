package bookStore;

public class DataSource {
	public static Customer[] customers = {new Customer(1, "hoa", "1234"),new Customer(2, "lan", "1234")};
	public static Type[] types = {new Type(1, "Romantic"),new Type(2, "Honor"),new Type(3, "Novel"),new Type(4, "Life style"),new Type(5, "Criminal")};
	public static Warehouse[] warehouse = {new Warehouse(1, "Ho Chi Minh"),new Warehouse(2, "Ha Noi")};
	public static Book[] books = {new Book(1, "Sapien", "Juri", 2),new Book(2, "harry portter", "juri2", 5),new Book(3, "Mathematic 2a", "dfa", 10)};
	public static BookType[] booktypes = {new BookType(1, 1),new BookType(1, 2),new BookType(2, 3),new BookType(3, 4),new BookType(2, 5)};
	public static Stock[] stocks = {new Stock(1, 1, 6),new Stock(2, 2, 5),new Stock(3, 1, 7),new Stock(1, 2, 8)};



}
