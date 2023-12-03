package test.spring.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import test.spring.mvc.bean.GuestBookDTO;
import test.spring.mvc.repository.GuestBook;

@Service
public class GuestBookServiceImpl implements GuestBookService{
	
	@Autowired
	private GuestBook mapper;

	@Override
	public List<GuestBookDTO> getAll(Model model) {
		List<GuestBookDTO> list = mapper.getAllArticle();
		model.addAttribute("list", list);
		return null;
	}

	@Override
	public void write(GuestBookDTO dto) {
		mapper.write(dto);;
	}

	@Override
	public void delete(int num) {

		mapper.delete(num);
	}
	
}
