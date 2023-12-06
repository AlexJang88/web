package test.spring.mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import test.spring.mvc.entity.FreeboardFileEntity;

public interface FreeboardFileJPARepository extends JpaRepository<FreeboardFileEntity, Integer>{
	
	public List<FreeboardFileEntity> findByFreeboardnum(@Param("freeboardnum")int freeboardnum);
	
	@Transactional
	public void deleteByFreeboardnum(@Param("freeboardnum")int freeboardnum);
}
