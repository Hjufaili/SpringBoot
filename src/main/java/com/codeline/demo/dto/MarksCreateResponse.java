package com.codeline.demo.dto;

import com.codeline.demo.entity.Marks;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarksCreateResponse {

    private Integer id;
    private String studentName;
    private Integer score;

    public static MarksCreateResponse convertToMarks(Marks entity){
        return MarksCreateResponse.builder()
                .id(entity.getId())
                .studentName(entity.getStudentName())
                .score(entity.getScore())
                .build();
    }
}
