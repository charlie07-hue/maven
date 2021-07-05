package chap07;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// interface로 구현한 서비스들은 이런식으로 이름짓는다.

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao dao;
	
	@Override
	public List<MemberVo> selectList() {
		
		return dao.selectList();
	}

}
