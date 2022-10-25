package bookStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		LocalDate date1 = LocalDate.now();
		// bookStore
		int currentCustomerId = 0;
		System.out.println("------Welcome to book store------");
		boolean isCustomer = false;
		while (!isCustomer) {
			System.out.print("Your name: ");
			String name = input.nextLine();
			System.out.print("Your password: ");
			String password = input.nextLine();
			currentCustomerId = BookStoreService.login(name, password, DataSource.customers);
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
			for (Book book : DataSource.books) {
				System.out.println(book.getId() + "/ " + book.getName() + ".");
			}
			System.out.println("-------------------------------");
			System.out.print("please select the book!");

			int bookSelected = input.nextInt();
			for (Book book : DataSource.books) {
				if (book.getId() == bookSelected) {
					System.out.println("------------" + book.getName() + "-----------");
					System.out.println("Author: " + book.getAuthor());
					System.out.print("Type: ");
					List<Type> typeIdList = BookStoreService.getTypeIdFromBookId(bookSelected, DataSource.booktypes, DataSource.types);
					for (Type type : typeIdList) {
						System.out.print(type.getTypeName() + " ");
						System.out.println();
					}

					int bookQuantity = BookStoreService.totalQuantityFromStock(bookSelected, DataSource.stocks);
					book.setQuantity(bookQuantity);
					System.out.println("quantity: " + book.getQuantity());
				}

			}
			System.out.println("-------------------------------");
			System.out.println("0. back to book list.");
			System.out.println("1. borrow this book.");
			System.out.println("2. return this book.");
			int addToCart = input.nextInt();
			int bookQuantity = BookStoreService.totalQuantityFromStock(bookSelected, DataSource.stocks);
			if (addToCart == 1) {
				System.out.println("borrow quantity: ");
				int borrowNumber = input.nextInt();
				if (borrowNumber <= bookQuantity) {
					BorrowItem item = new BorrowItem();
					item.setId(borrowItemId);
					item.setBookId(bookSelected);
					item.setQuanity(borrowNumber);
					item.setDay(date1);
					borrowItemList.add(item);
					borrowItemId++;
					System.out.println("borrow confirmed!");

				} else {
					System.out.println("we dont have enough books!");
				}

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
		for (Customer customer : DataSource.customers) {
			if (currentCustomerId == customer.getId()) {
				System.out.println(customer.getName());
			}
		}

		System.out.println("borrow List:");

		for (BorrowItem borrowItem : borrowItemList) {

			for (Book book : DataSource.books) {
				if (borrowItem.getBookId() == book.getId()) {
					System.out.println(borrowItem.getId() + "/ " + book.getName() + "| quantity: "
							+ borrowItem.getQuanity() + "| date: " + borrowItem.getDay());
				}
			}
		}
		System.out.println("--------------");
		System.out.println("return List: ");

		for (ReturnItem returnItem : returnItemList) {
			for (Book book : DataSource.books) {
				if (returnItem.getBookId() == book.getId()) {
					System.out.println(returnItem.getId() + "/ " + book.getName() + "| quantity: "
							+ returnItem.getQuanity() + "| date: " + returnItem.getDay());
				}

			}

		}

		input.close();

	}

}
