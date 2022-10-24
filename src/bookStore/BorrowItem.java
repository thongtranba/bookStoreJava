package bookStore;

import java.time.LocalDate;

public class BorrowItem {
	private int Id;
	private int bookId;
	private int orderId;
	private LocalDate day;
	private int quanity;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	
	public LocalDate getDay() {
		return day;
	}

	public void setDay(LocalDate day) {
		this.day = day;
	}

	public int getQuanity() {
		return quanity;
	}

	public void setQuanity(int quanity) {
		this.quanity = quanity;
	}

	public BorrowItem() {
		super();
	}

	public BorrowItem(int id, int bookId, int orderId, LocalDate day, int quanity) {
		super();
		Id = id;
		this.bookId = bookId;
		this.orderId = orderId;
		this.day = day;
		this.quanity = quanity;
	}

	
}
