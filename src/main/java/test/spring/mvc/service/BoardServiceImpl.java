package test.spring.mvc.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import test.spring.mvc.bean.BoardDTO;
import test.spring.mvc.bean.FreeboardFileDTO;
import test.spring.mvc.bean.MemberDTO;
import test.spring.mvc.entity.BoardEntity;
import test.spring.mvc.entity.FreeboardFileEntity;
import test.spring.mvc.repository.BoardJPARepository;
import test.spring.mvc.repository.BoardMapper;
import test.spring.mvc.repository.FreeboardFileJPARepository;

@Service
public class BoardServiceImpl implements BoardService {

//-- Mybatis 작업내용--
	
//	@Autowired
//	private HashMap boardMap;
//
//	@Autowired
//	private BoardMapper mapper;
//
//	@Override
//	public int count() {
//		return mapper.count();
//	}
//
//	@Override
//	public void list(int pageNum, Model model) {
//		int pageSize = 10;
//		int startRow = (pageNum - 1) * pageSize + 1;
//		int endRow = pageNum * pageSize;
//		int count = mapper.count();
//		List<BoardDTO> list = Collections.EMPTY_LIST;
//
//		if (count > 0) {
//			boardMap.put("start", startRow);
//			boardMap.put("end", endRow);
//			list = mapper.list(boardMap);
//		}
//
//		model.addAttribute("list", list);
//		model.addAttribute("count", count);
//		model.addAttribute("pageNum", pageNum);
//		model.addAttribute("pageSize", pageSize);
//
//		// page
//		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
//		int startPage = (int) (pageNum / 10) * 10 + 1;
//		int pageBlock = 10;
//		int endPage = startPage + pageBlock - 1;
//		if (endPage > pageCount) {
//			endPage = pageCount;
//		}
//		model.addAttribute("pageCount", pageCount);
//		model.addAttribute("startPage", startPage);
//		model.addAttribute("pageBlock", pageBlock);
//		model.addAttribute("endPage", endPage);
//
//	}
//
//	@Override
//	public void create(BoardDTO dto) {
//		int number = mapper.maxNum() + 1;
//		if (dto.getNum() != 0) {
//			mapper.writeUpdate(dto);
//			dto.setRe_step(dto.getRe_step() + 1);
//			dto.setRe_level(dto.getRe_level() + 1);
//		} else {
//			dto.setRef(number);
//		}
//
//		mapper.writeInsert(dto);
//	}
//
//	@Override
//	public BoardDTO getArticle(int num) {
//
//		return mapper.getArticle(num);
//	}
//
//	@Override
//	public int updateArticle(BoardDTO dto) {
//
//		return mapper.updateArticle(dto);
//	}
//
//	@Override
//	public int fileUpload(ArrayList<MultipartFile> files, String filePath) {
//		int result = 0;
//		int freeboardNum = mapper.maxNum();
//		for (int i = 0; i < files.size(); i++) {
//			MultipartFile f = files.get(i);
//			String fileName = f.getOriginalFilename();
//			if (!fileName.equals("")) {
//				String ext = fileName.substring(fileName.lastIndexOf("."));
//				fileName = "file_" + freeboardNum + "_" + i + ext;
//				File copy = new File(filePath + fileName);
//				result += mapper.fileInsert(freeboardNum, fileName);
//				try {
//					f.transferTo(copy);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return result;
//	}
//
//	@Override
//	public List<String> getFileName(int freeboardnum) {
//		return mapper.getFileName(freeboardnum);
//	}
//
//	@Override
//	public String readPasswd(int num) {
//		return mapper.readPasswd(num);
//	}
//
//	@Override
//	public int deleteNum(HttpServletRequest request, int num, String passwd) {
//		String dbpw = readPasswd(num);
//		String path = request.getServletContext().getRealPath("/resources/file/board/");
//		int result = 0;
//		if (dbpw.equals(passwd)) {
//			List<String> filename = mapper.getFileName(num);
//			if (filename != null && filename.size() < 1) {
//				for (String s : filename) {
//					File f = new File(path + s);
//					if (f.exists()) {
//						f.delete();
//					}
//				}
//			}
//			mapper.fileDelete(num);
//			result = mapper.deleteNum(num);
//
//		}
//
//		return result;
//	}
//
//	@Override
//	public void fileDelete(int freeboardnum) {
//		mapper.fileDelete(freeboardnum);
//
//	}
	
	
	
	
	
//  --JPA 작업내용 --
	
	
	@Autowired 
	private BoardJPARepository boardJPA;
	
	@Autowired
	private FreeboardFileJPARepository fileJPA;


	 @Override
	   public int count() {
	      return (int)boardJPA.count();
	   }
	 
	   @Override
	   public void list(int pageNum, Model model) {
	      int pageSize = 10;//한화면에 보여줄 갯수
	      
	      int count = count(); //총 게시물수 
	      
	      List<BoardDTO> list = Collections.EMPTY_LIST ;
	      if(count>0) {
	         Sort sort = Sort.by(Sort.Order.desc("ref") , Sort.Order.asc("reStep"));         
	         Page<BoardEntity> page = boardJPA.findAll(PageRequest.of( pageNum-1,pageSize, sort));
	         List<BoardEntity> entityList =page.getContent();
	         list = new ArrayList<>();
	         for(BoardEntity be : entityList) {
	            BoardDTO dto= be.toBoardDTO();
	            list.add(dto);
	         }
	      }
	      
	      
	      model.addAttribute("list",list);
	      model.addAttribute("count",count);
	      model.addAttribute("pageNum",pageNum);
	      model.addAttribute("pageSize",pageSize);
	      
	      // 페이징처리
	      int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1); 
	      int startPage = (pageNum/10)*10+1; 
	      int pageBlock = 10;   //페이징(이전/다음)을 몇개단위로 끊을지
	      int endPage = startPage + pageBlock-1;
	      if (endPage > pageCount){
	         endPage = pageCount;
	      }
	      model.addAttribute("pageCount",pageCount);
	      model.addAttribute("startPage",startPage);
	      model.addAttribute("pageBlock",pageBlock);
	      model.addAttribute("endPage",endPage);
	   }

	   @Override
	   public void create(BoardDTO dto) {
	      int number = boardJPA.findMaxNum()+1;
	      if(dto.getNum() != 0) {
	         boardJPA.writeUpdate(dto.getRef(),dto.getRe_step());
	         dto.setRe_step(dto.getRe_step()+1);
	         dto.setRe_level(dto.getRe_level()+1);
	      }
	      else {
	         dto.setRef(number);
	      }
	      dto.setReg_date(new Timestamp(System.currentTimeMillis()));
	      boardJPA.save(dto.toBoardEntity());
	   }


	   @Override
	   public BoardDTO readContent(int num) {
	      boardJPA.readCount(num);
	      
	      return boardJPA.findById(num).get().toBoardDTO();
	   }

	   @Override
	   public BoardDTO update(int num) { //글가져오기 업데이트 부분
	      return boardJPA.findById(num).get().toBoardDTO();
	   }

	   @Override
	   public String readPasswd(int num) {
	      return null;
	   }

	   @Override
	   public int updateNum(BoardDTO dto) {  //글 수정하기
	      String dbpw = boardJPA.findById(dto.getNum()).get().toBoardDTO().getPasswd();
	      int result = 0;
	      if(dbpw.equals(dto.getPasswd())) {
	         boardJPA.updateNum(dto);
	         result++;
	      }
	      return result;
	   }

	   @Override
	   public int deleteNum(int num, String passwd,String path) {
	      //String path 추가
		   String dbpw = boardJPA.findById(num).get().toBoardDTO().getPasswd();
	      int result = 0;
	      if(dbpw.equals(passwd)) {
	         List<FreeboardFileEntity> fileList = fileJPA.findByFreeboardnum(num);
	         
	         if(fileList != null) {
	        	 
	            for(FreeboardFileEntity fileDTO : fileList) {
	               File f = new File(path+fileDTO.getFilename());
	               if(f.isFile()) {
	                  f.delete();
	               }
	            }    
	         }
	         fileJPA.deleteByFreeboardnum(num);   
	         boardJPA.deleteById(num);
	         result++;
	      }
	      return result;
	   }

	   @Override
	   public int fileUpload(ArrayList<MultipartFile> files, String path) {
	      int result = 0;
	      int freeboardNum = boardJPA.findMaxNum();
	      for(int i=0 ; i < files.size();i++) { //갯수만큼 반복
	         MultipartFile f = files.get(i);  // i번째부터 꺼내기
	         String fileName = f.getOriginalFilename();  //파일이름꺼내기
	         FreeboardFileDTO fileDTO = new FreeboardFileDTO();
	         if(!fileName.equals("")) { //파일이 빈공백이 아닐때
	            String ext = fileName.substring(fileName.lastIndexOf("."));
	            fileName = "file_"+freeboardNum+"_"+i+ext;  //파일이름
	            File copy = new File(path+fileName);
	            fileDTO.setFreeboardnum(freeboardNum);
	            fileDTO.setFilename(fileName);
	            fileJPA.save(fileDTO.toFreeboardFileDTO());
	            result ++;
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
	public List<String> fileget(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FreeboardFileDTO> fileList(int freeboardnum) {
		List<FreeboardFileEntity> entityList=null;
		List<FreeboardFileDTO> fileList=null;
		entityList = fileJPA.findByFreeboardnum(freeboardnum);
		if(entityList.size()>0) {
			fileList = new ArrayList();
			for(FreeboardFileEntity bfe : entityList) {
				FreeboardFileDTO dto = bfe.toFreeboardFileDTO();
				fileList.add(dto);
			}
		}
		return fileList;
	}

	   
	
	

}
