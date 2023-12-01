package test.spring.mvc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file/*")
public class FileController {
	
	
	@RequestMapping("uploadForm")
	public String uploadForm() {
		return "upload/uploadForm";
	}
	
	@RequestMapping("uploadPro")
	public String uploadPro(HttpServletRequest request,String writer,MultipartFile upload) {
		String filePath = request.getServletContext().getRealPath("/resources/file/"); //������ ���
		String contentType=upload.getContentType(); // mime type ���� Ȯ��
		
		
		if(contentType.split("/")[0].equals("image")) {
			String orgName = upload.getOriginalFilename(); //�����̸�                
			String ext = orgName.substring(orgName.lastIndexOf("."));
			File copy = new File(filePath+writer+ext); //img    new File("�����ּ� / ���ϸ�.Ȯ����")
			
			try {
				upload.transferTo(copy);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		
		
		
		
		
		return "upload/uploadPro";
	}
	
	@RequestMapping("uploadPro2")
	public String uploadPro2(String writer,ArrayList<MultipartFile> upload) {
		String filePath="d://file//";
		//���ٽ�
		upload.forEach(file->{
			File copy = new File(filePath+file.getOriginalFilename());
			try {
				file.transferTo(copy);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		});
		//for��
//		for(MultipartFile f : upload) {
//			System.out.println(f.getOriginalFilename());
//		}
		
		return "upload/uploadPro";
	}
}
