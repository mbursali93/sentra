package com.example.sentra.repository.impls;

import java.util.Optional;

import com.example.sentra.model.MemberModel;
public interface CustomMemberRepository {
    public boolean userIsAlreadyMember(String userId, String homeId);

    public Optional<MemberModel> getMemberByUserId(String userId);

    public MemberModel deleteMember(String memberId);

    public MemberModel getMembershipWithUserIdAndHouseId(String homeId, String userId);

    public MemberModel updateRole(String id, byte roleLevel);
}
