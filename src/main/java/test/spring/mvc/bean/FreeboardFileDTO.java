package test.spring.mvc.bean;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import test.spring.mvc.entity.FreeboardFileEntity;

@Data
@NoArgsConstructor
public class FreeboardFileDTO {

	private int num;
	private int freeboardnum;
	private String filename;
	
	@Builder
	public FreeboardFileDTO(int num, int freeboardnum, String filename) {
		super();
		this.num = num;
		this.freeboardnum = freeboardnum;
		this.filename = filename;
	}
	public FreeboardFileEntity toFreeboardFileDTO() {
		return FreeboardFileEntity.builder()
				.num(this.num)
				.freeboardnum(this.freeboardnum)
				.filename(this.filename).build();
	}
	
	
}
