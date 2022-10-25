package bookStore;

import java.util.ArrayList;
import java.util.List;

public class BookStoreService {
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

	public static int totalQuantityFromStock(int bookId, Stock[] stocks) {
		int totalQuantity = 0;
		for (Stock stock : stocks) {
			if (stock.getBookId() == bookId) {
				totalQuantity += stock.getQuantity();
			}
		}
		return totalQuantity;
	}

}
