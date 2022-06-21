package com.its.ex.service;

import com.its.ex.dto.TestDTO;
import com.its.ex.entity.TestEntity;
import com.its.ex.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;

    public Long save(TestDTO testDTO){
        System.out.println("testDTO =" + testDTO);
        // TestEntity testEntity = new TestEntity();
        // TestDTO 객체에 담긴 값을 TestEntity 객체에 옮겨담기

       // testEntity.setColumn1(testDTO.getTestColumn());
       //  testEntity.setTestColumn(testDTO.getTestColumn1());
        TestEntity testEntity = TestEntity.toEntity(testDTO);
        Long id = testRepository.save(testEntity).getId();
        return id;
    }
}
