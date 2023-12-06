package test.spring.mvc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import test.spring.mvc.bean.FreeboardFileDTO;

@Data
@NoArgsConstructor
@SequenceGenerator(name="fileSeq",sequenceName = "freeboard_file_seq",allocationSize=1,initialValue=1)
@Entity
@Table(name="freeboard_file")
public class FreeboardFileEntity {
	
	@Id
	@GeneratedValue(generator="fileSeq",strategy=GenerationType.SEQUENCE)
	private int num;
	private int freeboardnum;
	private String filename;
	
	@Builder
	public FreeboardFileEntity(int num, int freeboardnum, String filename) {
		super();
		this.num = num;
		this.freeboardnum = freeboardnum;
		this.filename = filename;
	}
	public FreeboardFileDTO toFreeboardFileDTO() {
		return FreeboardFileDTO.builder()
				.num(this.num)
				.freeboardnum(this.freeboardnum)
				.filename(this.filename).build();
	}
}
