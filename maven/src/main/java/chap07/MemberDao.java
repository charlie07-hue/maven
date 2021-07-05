package chap07;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository // Dao가 Annotation 넣는 법
public class MemberDao {
	
	@Autowired // Annotation으로 Bean에 넣어놨기 때문에, type이 SqlSessionTemplate과 같은 객체를 찾아 주입함
	SqlSessionTemplate sqlSessionTemplate;
	
	public List<MemberVo> selectList() {
		return sqlSessionTemplate.selectList("member.selectList"); // selectList, selectOne 무조건 암기 ☆☆☆
	}
	
}
