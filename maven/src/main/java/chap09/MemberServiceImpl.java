package chap09;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

// interface로 구현한 서비스들은 이런식으로 이름짓는다.

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao dao;
	
	@Override
	public List<MemberVo> selectList() {
		
		return dao.selectList();
	}

	@Override
	public MemberVo login(MemberVo vo) {
		return dao.login(vo);
	}

	@Override
	public MemberVo mypage(int mno) {
//		MemberVo vo = (MemberVo)sess.getAttribute("memberInfo")
//		MemberVo m = dao.selecOne(vo.getMno());
//		model.Attribute("vo", m)
//		return "member/mypage";
		return dao.selectOne(mno);
	}

	@Override
	public int update(MemberVo vo) {
		return dao.update(vo);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor= {Throwable.class}) // 전파기능을 사용하겠다 | 이 Exception이 발생하면 rollback을 시켜라 는 뜻
	public int insert(MemberVo vo, HttpServletRequest req) { // school 3개랑 year 3개는 vo에 없으니까 꺼내와야함 (email, mname, pwd만 들어있는 객체)
		int r = 0;
//		try {
			dao.insert(vo); // members table에는 등록이 됨. 
			// vo객체에 mno가 set이 되어있는 상태. dao.insert(vo)를 실행하고 나서 set이 된것. last_insert_id()를 실행했을때.
			
			String[] school = req.getParameterValues("school"); // 길이가 3인 Spring 배열
			String[] year = req.getParameterValues("year"); // int로 입력해도 어차피 String으로 넘어오게 되어있다. why?
			Map map = new HashMap(); // map에다 put 하면됨
			map.put("members_mno", vo.getMno()); // 위에다 빼서 한번만 put 해줘도 아래 for문을 통해 put이 반복되어 동일한 결과.
			
			for(int i=0; i<school.length; i++) {
				if(!"".equals(school[i]) && !"".equals(year[i])) {
					map.put("school", school[i]);
					map.put("year", year[i]);
					dao.insertSchool(map); // school과 year안쓰면 등록 x 하나만 썼을땐 등록이 가능하도록 -> &&를 ||로 변경
				}
			}
//			r = 1;
//		} catch (Exception e) {
//			
//			r = 0;
//		}
		return r;
	}

}
