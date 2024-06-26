package com.tripor.article.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticlePostDto {
	private ArticleDto articleDto;
    private List<FileInfoDto> fileInfos;
}
