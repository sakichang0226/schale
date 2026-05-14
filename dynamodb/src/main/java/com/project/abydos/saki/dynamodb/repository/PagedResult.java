package com.project.abydos.saki.dynamodb.repository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * ページング付きクエリ結果.
 * DynamoDBのLastEvaluatedKeyの概念を隠蔽し、ソートキーの値として提供する.
 *
 * @param <T> エンティティの型
 */
@Getter
@RequiredArgsConstructor
public class PagedResult<T> {
    /** 取得結果リスト */
    private final List<T> items;
    /** 次ページがある場合の最後のソートキー値（ない場合はnull） */
    private final Long lastEvaluatedSortKey;
}
