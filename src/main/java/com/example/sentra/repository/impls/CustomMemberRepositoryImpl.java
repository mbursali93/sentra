package com.example.sentra.repository.impls;

import org.modelmapper.spi.ValueReader.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.example.sentra.model.MemberModel;

import java.util.Optional;

public class CustomMemberRepositoryImpl implements CustomMemberRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public boolean userIsAlreadyMember(String userId, String homeId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId).and("homeId").is(homeId));

        return mongoTemplate.exists(query, MemberModel.class);
    }

    public Optional<MemberModel> getMemberByUserId(String id) {
        Query query = new Query();

        query.addCriteria(Criteria.where("userId").is(id));

        MemberModel member = mongoTemplate.findOne(query, MemberModel.class);

        return Optional.ofNullable(member);
    }

    public MemberModel deleteMember(String memberId) {
        Query query = new Query();

        query.addCriteria(Criteria.where("userId").is(memberId));

        MemberModel member = mongoTemplate.findOne(query, MemberModel.class);
        mongoTemplate.remove(query, MemberModel.class);

        return member;
    }
    
    public MemberModel getMembershipWithUserIdAndHouseId(String homeId, String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId).and("homeId").is(homeId));

        return mongoTemplate.findOne(query, MemberModel.class);
    }

    public MemberModel updateRole(String id, byte roleLevel) {

        MemberModel member = mongoTemplate.findById(id, MemberModel.class);
        member.setRoleLevel(roleLevel);
        return mongoTemplate.save(member);

    }

}
