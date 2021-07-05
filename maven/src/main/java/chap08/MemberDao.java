package chap08;

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
	
	public MemberVo login(MemberVo vo) {
		return sqlSessionTemplate.selectOne("member.login", vo);
	}
	
	public MemberVo selectOne(int mno) {
		return sqlSessionTemplate.selectOne("member.selectOne", mno);
	}
	
	public int update(MemberVo vo) {
		return sqlSessionTemplate.update("member.update", vo);
	}
}
