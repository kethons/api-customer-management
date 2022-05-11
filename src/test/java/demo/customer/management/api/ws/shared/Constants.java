package demo.customer.management.api.ws.shared;

import java.time.LocalDate;
import java.time.Month;

public final class Constants {

	public static final String INVALID_CUSTOMER_ID = "WVz1RLUxtfx80FFcHvAPnXxbTUElSe";
	public static final long ID = 1L;
	public static final String VALID_CUSTOMER_ID = "NboIw5UFWOQzYCHzilURC1xLxUnr474";
	public static final LocalDate DATE_OF_BIRTH = LocalDate.of(1992, Month.OCTOBER, 13);
	public static final String LAST_NAME = "Kankapure";
	public static final String FIRST_NAME = "Ketan";

	private Constants() {
		throw new AssertionError();
	}

}
