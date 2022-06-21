package com.its.ex.test;

import com.its.ex.dto.TestDTO;
import com.its.ex.service.TestService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class TestClass {
    @Autowired
    private TestService testService;

    // testService의 save() 호출
    @Test
    @Transactional
    @Rollback(value = true)
    public void saveTest(){
        // Long testResult = testService.save();
        // System.out.println("testResult =" + testResult);
        /**
         * 1. 저장할 TestDTO 객체를 만들고 필드값을 저장.
         * 2. DTO 객체를 서비스의 save 메서드로 전달
         * 3. 전달 후 리턴 값을 받아서(Long)
         * 4. 그 리턴값으로 DB에서 findBuId를 하고
         * 5. DB에서 조회된 데이터와 1.번에서 저장한 데이터가 일치하는지를 판단하여
         * 6. 일치하면 테스트 통과, 일치하지 않으면 테스트 실패
         */
        // 1.
        TestDTO testDTO = new TestDTO("테스트 데이터1","테스트데이터2");
        // 2.
        Long saveID = testService.save(testDTO);
        // 4.
        TestDTO findDTO = testService.findById(saveID);
        // 5. 6.
        assertThat(testDTO.getTestColumn()).isEqualTo(findDTO.getTestColumn1());
    }
    @Test
    @DisplayName("findAll 테스트")
    public void findAllTest(){
        /**
         * 1. 3개의 테스트 데이터 저장
         * 2. findAll 호출
         * 3. 호출한 리스트의 크기가 3인지를 판단
         * 4. 3이면 테스트 통과, 아니면 테스트 실패
         */
        // 3개의 테스트 데이터를 저장해봅시다. 반복문을 써서
        TestDTO testDTO;
          for(int i=1; i<=3; i++){
            // testDTO= new TestDTO("테스트데이터" +i, "테스트데이터"+i);
            // testService.save(testDTO);
           // testService.save(new TestDTO("테스트데이터"+i, "테스트데이터"+i));
        }
        // 자바 람다식(화살표함수)
        IntStream.rangeClosed(1,3).forEach(i ->{
            testService.save(new TestDTO("테스트데이터"+i, "테스트데이터"+i));
        });
          // findAll 호출해서 라스트 크기가 3과 일치하는지 확인해봅시다.
        List<TestDTO>testDTOList = testService.findAll();
        assertThat(testDTOList.size()).isEqualTo(3);
    }
    @Test
    @DisplayName("삭제 리스트")
    public void deleteTest(){
        TestDTO testDTO = new TestDTO("테스트 데이터1","테스트데이터2");
        // 2.3
        Long saveID = testService.save(testDTO);
        // 4. 삭제 수행
        testService.delete(saveID);
        assertThat(testService.findById(saveID)).isNull();

    }
    @Test
    @DisplayName("수정 테스트")
    public void updateTest(){
        /**
         * 수정 테스트를 어떻게할지 시나리오 써보시고.
         * assertThat().isNotEqualTo() 쓰면 됩니다.
         * 1. 새로운 데이터 저장
         * 2. 저장한 객체를 조회
         * 3. test_column1의 값을 변경함하는 수정 처리
         * 4. 수정 후 다시 객체 조회 (findByID)
         * 5. 2번에서 조회한 값과 5번에서 조회한 값이 같은지를 비교하여
         * 6. 다르면 테스트 성공, 같다면 테스트 실패
         */
        TestDTO testDTO = new TestDTO("테스트 데이터1","테스트 데이터2");
        Long saveID = testService.save(testDTO);
        TestDTO updateDTO = testService.findById(saveID);
        updateDTO.setTestColumn("수정");
        testService.update(updateDTO);
        updateDTO = testService.findById(saveID);
    }
}
