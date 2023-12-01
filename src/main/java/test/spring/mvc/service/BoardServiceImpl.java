package test.spring.mvc.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import test.spring.mvc.bean.BoardDTO;
import test.spring.mvc.bean.FreeboardFileDTO;
import test.spring.mvc.bean.MemberDTO;
import test.spring.mvc.repository.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private HashMap boardMap;

	@Autowired
	private BoardMapper mapper;

	@Override
	public int count() {
		return mapper.count();
	}

	@Override
	public void list(int pageNum, Model model) {
		int pageSize = 10;
		int startRow = (pageNum - 1) * pageSize + 1;
		int endRow = pageNum * pageSize;
		int count = mapper.count();
		List<BoardDTO> list = Collections.EMPTY_LIST;

		if (count > 0) {
			boardMap.put("start", startRow);
			boardMap.put("end", endRow);
			list = mapper.list(boardMap);
		}

		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pageSize", pageSize);

		// page
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = (int) (pageNum / 10) * 10 + 1;
		int pageBlock = 10;
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("startPage", startPage);
		model.addAttribute("pageBlock", pageBlock);
		model.addAttribute("endPage", endPage);

	}

	@Override
	public void create(BoardDTO dto) {
		int number = mapper.maxNum() + 1;
		if (dto.getNum() != 0) {
			mapper.writeUpdate(dto);
			dto.setRe_step(dto.getRe_step() + 1);
			dto.setRe_level(dto.getRe_level() + 1);
		} else {
			dto.setRef(number);
		}

		mapper.writeInsert(dto);
	}

	@Override
	public BoardDTO getArticle(int num) {

		return mapper.getArticle(num);
	}

	@Override
	public int updateArticle(BoardDTO dto) {

		return mapper.updateArticle(dto);
	}

	@Override
	public int fileUpload(ArrayList<MultipartFile> files, String filePath) {
		int result = 0;
		int freeboardNum = mapper.maxNum();
		for (int i = 0; i < files.size(); i++) {
			MultipartFile f = files.get(i);
			String fileName = f.getOriginalFilename();
			if (!fileName.equals("")) {
				String ext = fileName.substring(fileName.lastIndexOf("."));
				fileName = "file_" + freeboardNum + "_" + i + ext;
				File copy = new File(filePath + fileName);
				result += mapper.fileInsert(freeboardNum, fileName);
				try {
					f.transferTo(copy);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	@Override
	public List<String> getFileName(int freeboardnum) {
		return mapper.getFileName(freeboardnum);
	}

	@Override
	public String readPasswd(int num) {
		return mapper.readPasswd(num);
	}

	@Override
	public int deleteNum(HttpServletRequest request, int num, String passwd) {
		String dbpw = readPasswd(num);
		String path = request.getServletContext().getRealPath("/resources/file/board/");
		int result = 0;
		if (dbpw.equals(passwd)) {
			List<String> filename = mapper.getFileName(num);
			if (filename != null && filename.size() < 1) {
				for (String s : filename) {
					File f = new File(path + s);
					if (f.exists()) {
						f.delete();
					}
				}
			}
			mapper.fileDelete(num);
			result = mapper.deleteNum(num);

		}

		return result;
	}

	@Override
	public void fileDelete(int freeboardnum) {
		mapper.fileDelete(freeboardnum);

	}

}
