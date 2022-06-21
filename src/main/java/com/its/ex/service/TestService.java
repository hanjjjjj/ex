package com.its.ex.service;

import com.its.ex.dto.TestDTO;
import com.its.ex.entity.TestEntity;
import com.its.ex.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;

    public Long save(TestDTO testDTO) {
        System.out.println("testDTO =" + testDTO);
        // TestEntity testEntity = new TestEntity();
        // TestDTO 객체에 담긴 값을 TestEntity 객체에 옮겨담기

        // testEntity.setColumn1(testDTO.getTestColumn());
        //  testEntity.setTestColumn(testDTO.getTestColumn1());
        TestEntity testEntity = TestEntity.toEntity(testDTO);
        Long id = testRepository.save(testEntity).getId();
        return id;
    }

    // isprsent 값이 둘어있느냐
    public TestDTO findById(Long id) {
        Optional<TestEntity> optionalTestEntity = testRepository.findById(id); //  Optional역할 : 내가 뭔가 다루고자 하는 객체를 한번더 포장
        if (optionalTestEntity.isPresent()) { // isPresent 물어봤을때 true 이면 값이 있다. false면 값이 없다 isEmpty()는 true 면 값이 없다. false면 값이 들어있다.
            //조회된 결과가 있다.
//            TestEntity testEntity = optionalTestEntity.get();
//            TestDTO testDTO = TestDTO.toDTO(testEntity);
//            return testDTO;
            return TestDTO.toDTO(optionalTestEntity.get());
        } else {
            // 조회된 결과가 없다.
            return null;
        }
    }

    public List<TestDTO> findAll() {
        List<TestEntity> entityList = testRepository.findAll();
        List<TestDTO> findList = new ArrayList<>();
        for (TestEntity testEntity: entityList){
            TestDTO testDTO = TestDTO.toDTO(testEntity);
            findList.add(testDTO);
        }
        return findList;
    }

    public void delete(Long id) {
        testRepository.deleteById(id);
    }

    public Long update(TestDTO testDTO) {
        // save 메서드 호출로 update 쿼리 가능(단, id가 같이 가야함.)
        TestEntity testEntity = TestEntity.toUpdateEntity(testDTO);
        Long id = testRepository.save(testEntity).getId();
        return id;

    }
}
