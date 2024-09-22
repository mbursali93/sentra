package com.example.sentra.service;

import org.modelmapper.spi.ValueReader.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sentra.base.BaseService;
import com.example.sentra.dto.CreateMemberDto;
import com.example.sentra.dto.UpdateMemberRoleDto;
import com.example.sentra.expections.ApiException;
import com.example.sentra.expections.CustomException;
import com.example.sentra.model.HomeModel;
import com.example.sentra.model.MemberModel;
import com.example.sentra.model.UserModel;
import com.example.sentra.repository.HomeRepository;
import com.example.sentra.repository.MemberRepository;
import com.example.sentra.repository.UserRepository;

import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private HomeRepository homeRepository;

    @Autowired
    private UserRepository userRepository;

    
    public MemberModel create(CreateMemberDto data, String userId) {
        String homeId = data.getHomeId();

        Optional<HomeModel> homeOptional = homeRepository.findById(homeId);
        if (!homeOptional.isPresent())
            throw new ApiException("No home found with this id", "HOME_NOT_FOUND", 404);
        
        HomeModel home = homeOptional.get();

        String homeOwnerId = home.getOwnerId();

        if (!homeOwnerId.equals(userId))
            throw new CustomException("", "");

        boolean userExists = userRepository.existsById(data.getMemberId());

        if (!userExists)
            throw new CustomException("", "");

        boolean userIsAlreadyMember = memberRepository.userIsAlreadyMember(data.getMemberId(), homeId);
        if (userIsAlreadyMember)
            throw new CustomException("", "");

        MemberModel member = new MemberModel();

        member.setHomeId(homeId);
        member.setUserId(data.getMemberId());
        member.setRoleLevel(data.getRoleLevel());

        return memberRepository.save(member);
    }

    
    public MemberModel deleteMember(String memberId, String userId) {
        // TODO Auto-generated method stub

        Optional<MemberModel> memberOptional = memberRepository.getMemberByUserId(memberId);
        if (!memberOptional.isPresent())
            throw new CustomException("", "");
        
        MemberModel member = memberOptional.get();

        String homeId = member.getHomeId();

        boolean userIsOwner = this.checkHomeOwner(homeId, userId);
        if (!userIsOwner)
            throw new CustomException("", "");

        

        return memberRepository.deleteMember(memberId);
    }

    public MemberModel deleteMembership(String userId, String homeId) {
        boolean userIsMember = memberRepository.userIsAlreadyMember(userId, homeId);
        if (!userIsMember)
            throw new CustomException("", "");
        
        return memberRepository.deleteMember(userId);
    }

    
    public MemberModel findAll(String userId) {
        // TODO Auto-generated method stub
        return null;
    }

    
    public MemberModel findOne(String homeId, String userId) {
        // TODO Auto-generated method stub

        MemberModel membership = memberRepository.getMembershipWithUserIdAndHouseId(homeId, userId);

        return membership;
    }

    
    public MemberModel updateRole(String id, UpdateMemberRoleDto data, String userId) {
        // TODO Auto-generated method stub

        Optional<MemberModel> memberOptional = memberRepository.findById(id);

        if (!memberOptional.isPresent())
            throw new CustomException("", "");

        MemberModel member = memberOptional.get();
        String homeId = member.getHomeId();
        
        boolean userIsOwner = this.checkHomeOwner(homeId, userId);

        if (!userIsOwner)
            throw new CustomException("", "");

        return memberRepository.updateRole(id, data.getRoleLevel());

    }
    
    private boolean checkHomeOwner(String homeId, String ownerId) {
        HomeModel home = homeRepository.findById(homeId).get();

        if (!home.getOwnerId().equals(ownerId))
            return false;

        return true;

    }
    

}
