package service;

import java.util.List;

import dao.MemberDAO;
import model.Member;
import util.ApplicationUtil;

public class MemberService {
    private MemberDAO memberDAO = new MemberDAO();

    // Add Member
    public void addMember(String input) {
       

        Member member = ApplicationUtil.parseMemberFromString(input);
        if (member != null) {
            memberDAO.addMember(member);
        }
    }

    // Update Member
    public void updateMember(int id,String field,String newValue) {
       

        memberDAO.updateMember(id, field, newValue);
    }

    // View Member By ID
    public Member viewMemberById(int id) {
 

        return memberDAO.getMemberById(id);
        
    }
    
    public void deactivateMember(int id) {
        
        memberDAO.deactivateMember(id);
    }

    // View All Members
    public List<Member> viewAllMembers() {
        List<Member> members = memberDAO.getAllMembers();
        return members;
    }
}