package com.moa.dto.response;

import com.moa.domain.Board;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardListResponseDto {
    private Long id;
    private String title;
    private String name;
    private LocalDateTime postDate;

    //construactor
    public BoardListResponseDto(Long id, String title, String name, LocalDateTime postDate) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.postDate = postDate;
    }
    //make toString method with StringBuilder
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("BoardListResponseDto{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", postDate=").append(postDate);
        sb.append('}');
        return sb.toString();
    }
}