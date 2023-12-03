package test.spring.mvc.repository;

import java.util.List;

import test.spring.mvc.bean.GuestBookDTO;

public interface GuestBook {
		public List<GuestBookDTO> getAllArticle();
		public void write(GuestBookDTO dto);
		public void delete(int num);
}
