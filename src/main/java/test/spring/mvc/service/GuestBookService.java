package test.spring.mvc.service;

import java.util.List;

import org.springframework.ui.Model;

import test.spring.mvc.bean.GuestBookDTO;

public interface GuestBookService {
	public List<GuestBookDTO> getAll(Model model);
	public void write(GuestBookDTO dto);
	public void delete(int num);
		
}
