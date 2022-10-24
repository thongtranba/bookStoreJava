package bookStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		LocalDate date1 = LocalDate.now();

		Customer customer1 = new Customer(1, "hoa", "1234");
		Customer customer2 = new Customer(2, "lan", "1234");

		Type type1 = new Type(1, "Romantic");
		Type type2 = new Type(2, "Honor");
		Type type3 = new Type(3, "Novel");
		Type type4 = new Type(4, "Life style");
		Type type5 = new Type(5, "Criminal");

		Warehouse warehouse1 = new Warehouse(1, "Ho Chi Minh");
		Warehouse warehouse2 = new Warehouse(2, "Ha Noi");

		Book book1 = new Book(1, "Sapien", "Juri", 2);
		Book book2 = new Book(2, "harry portter", "juri2", 5);
		Book book3 = new Book(3, "Mathematic 2a", "dfa", 10);

		BookType bookType1 = new BookType(1, 1);
		BookType bookType2 = new BookType(1, 2);
		BookType bookType3 = new BookType(2, 3);
		BookType bookType4 = new BookType(3, 4);
		BookType bookType5 = new BookType(2, 5);

		Stock stock1 = new Stock(1, 1, 6);
		Stock stock2 = new Stock(2, 2, 5);
		Stock stock3 = new Stock(3, 1, 7);
		Stock stock4 = new Stock(1, 2, 8);

		Customer[] customers = { customer1, customer2 };
		Type[] types = { type1, type2, type3, type4, type5 };
		Warehouse[] warehouses = { warehouse1, warehouse2 };
		Book[] books = { book1, book2, book3 };
		BookType[] bookTypes = { bookType1, bookType2, bookType3, bookType4, bookType5 };
		Stock[] stocks = { stock1, stock2, stock3, stock4 };

		// bookStore
		int currentCustomerId = 0;
		System.out.println("------Welcome to book store------");
		boolean isCustomer = false;
		while (!isCustomer) {
			System.out.print("Your name: ");
			String name = input.nextLine();
			System.out.print("Your password: ");
			String password = input.nextLine();
			currentCustomerId = login(name, password, customers);
			if (currentCustomerId > 0) {
				System.out.println("hi, " + name);
				isCustomer = true;
			}
		}
		List<BorrowItem> borrowItemList = new ArrayList<BorrowItem>();
		List<ReturnItem> returnItemList = new ArrayList<ReturnItem>();
		int borrowItemId = 1;
		int returnItemId = 1;
		boolean isSelected = false;
		while (!isSelected) {
			System.out.println("---------Book List-------------");
			for (Book book : books) {
				System.out.println(book.getId() + "/ " + book.getName() + ".");
			}
			System.out.println("-------------------------------");
			System.out.print("please select the book!");
			int bookSelected = input.nextInt();
			for (Book book : books) {
				if (book.getId() == bookSelected) {
					System.out.println("------------" + book.getName() + "-----------");
					System.out.println("Author: " + book.getAuthor());
					System.out.print("Type: ");
					List<Type> typeIdList = getTypeIdFromBookId(bookSelected, bookTypes, types);
					for (Type type : typeIdList) {
						System.out.print(type.getTypeName() + " ");
						System.out.println();
					}
				}

			}
			System.out.println("-------------------------------");
			System.out.println("0. back to book list.");
			System.out.println("1. borrow this book.");
			System.out.println("2. return this book.");

			int addToCart = input.nextInt();
			if (addToCart == 1) {
				System.out.println("borrow quantity: ");
				int borrowNumber = input.nextInt();
				BorrowItem item = new BorrowItem();
				item.setId(borrowItemId);
				item.setBookId(bookSelected);
				item.setQuanity(borrowNumber);
				item.setDay(date1);
				borrowItemList.add(item);
				borrowItemId++;
				System.out.println("borrow confirmed!");
			} else if (addToCart == 2) {
				System.out.println("reutrn quantity: ");
				int returnNumber = input.nextInt();
				ReturnItem item = new ReturnItem();
				item.setId(returnItemId);
				item.setBookId(bookSelected);
				item.setQuanity(returnNumber);
				item.setDay(date1);
				returnItemList.add(item);
				returnItemId++;
				System.out.println("return confirmed!");
			}
			System.out.println("-------------------------------------");
			System.out.println("0. keep shopping.");
			System.out.println("1. check out.");
			int checkOut = input.nextInt();
			if (checkOut == 1) {
				isSelected = true;
			}

		}

		// order
		System.out.println("---------Your Oder-------------");
		System.out.print("customer: ");
		for (Customer customer : customers) {
			if (currentCustomerId == customer.getId()) {
				System.out.println(customer.getName());
			}
		}

		System.out.println("borrow List:");

		for (BorrowItem borrowItem : borrowItemList) {

			for (Book book : books) {
				if (borrowItem.getBookId() == book.getId()) {
					System.out.println(borrowItem.getId() + "/ " + book.getName() + "| quantity: "
							+ borrowItem.getQuanity() + "| date: " + borrowItem.getDay());
				}
			}
		}
		System.out.println("--------------");
		System.out.println("return List: ");

		for (ReturnItem returnItem : returnItemList) {
			for (Book book : books) {
				if (returnItem.getBookId() == book.getId()) {
					System.out.println(returnItem.getId() + "/ " + book.getName() + "| quantity: "
							+ returnItem.getQuanity() + "| date: " + returnItem.getDay());
				}

			}

		}

		input.close();

	}

	public static int login(String name, String password, Customer[] customers) {
		int currentId = 0;
		for (Customer customer : customers) {
			if (customer.getName().equals(name) && customer.getPassword().equals(password)) {
				currentId = customer.getId();
			}
		}

		return currentId;
	}

	public static List<Type> getTypeIdFromBookId(int bookId, BookType[] bookTypes, Type[] types) {
		List<BookType> bookTypeId = getBookTypeId(bookId, bookTypes);
		List<Type> list = new ArrayList<Type>();

		for (BookType bookType : bookTypeId) {
			for (Type type : types) {
				if (bookType.getTypeId() == type.getId()) {
					list.add(type);
				}
			}

		}

		return list;
	}

	public static List<BookType> getBookTypeId(int bookId, BookType[] bookTypes) {
		List<BookType> list = new ArrayList<BookType>();
		for (BookType bookType : bookTypes) {
			if (bookType.getBookId() == bookId) {
				list.add(bookType);
			}
		}

		return list;
	}

}
