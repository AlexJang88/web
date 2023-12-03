package test.spring.mvc.bean;

import java.util.Date;

import lombok.Data;

@Data
public class GuestBookDTO {

	private int num;
	private String id;
	private String title;
	private String content;
	private int readcount;
	private Date reg_date;
	
}
