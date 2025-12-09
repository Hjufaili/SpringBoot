package com.codeline.demo.dto;

import com.codeline.demo.entity.Marks;
import com.codeline.demo.helper.Constants;
import com.codeline.demo.helper.HelperUtils;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarksCreateRequest {

    private String studentName;
    private Integer score;

    public static Marks convertToMarks(MarksCreateRequest request) {
        return Marks.builder()
                .studentName(request.getStudentName())
                .score(request.getScore())
                .build();

    }

    public static void validCreateMarksRequest(MarksCreateRequest request) throws Exception {
        if (HelperUtils.isNull(request.getStudentName()) || request.getStudentName().isBlank()) {
            throw new Exception(Constants.MARKS_CREATE_REQUEST_STUDENT_NAME_NOT_VALID);
        } else if (HelperUtils.isNull(request.getScore()) || request.getScore() > Constants.SCORE_UPPER_LIMIT
                || request.getScore() < Constants.SCORE_LOWER_LIMIT) {
            throw new Exception(Constants.MARKS_CREATE_REQUEST_SCORE_NOT_VALID);
        }
    }
}
