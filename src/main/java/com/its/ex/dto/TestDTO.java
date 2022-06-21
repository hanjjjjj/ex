package com.its.ex.dto;


import com.its.ex.entity.TestEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TestDTO {
    private Long id;
    private String testColumn;
    private String testColumn1;

    public TestDTO(String testColumn, String testColumn1) {
        this.testColumn = testColumn;
        this.testColumn1 = testColumn1;
    }

    public static TestDTO toDTO(TestEntity testEntity) {
        TestDTO testDTO = new TestDTO();
        testDTO.setId(testEntity.getId());
        testDTO.setTestColumn(testEntity.getTestColumn());
        testDTO.setTestColumn1(testEntity.getColumn1());
        return testDTO;
    }
}
