package com.project.schale.saki.database.product.converter;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 *　モデルクラスのObjectをEntityクラスに変換するメソッドの集まり
 */
public class EntityConverter {

    private EntityConverter(){}

    /**
     * DBのモデルクラスからエンティティクラスへ変換する
     * @param model model
     * @param clazz 変換したいエンティティクラス
     * @return Entityクラス(nullの場合は空のOptionalを返す)
     * @param <T> 変換したいエンティティクラス
     */
    public static <F, T> Optional<T> convert(F model, Class<T> clazz) {
        if (Objects.isNull(model)) {
            return Optional.empty();
        }

        try {
            T entity = clazz.newInstance();
            BeanUtils.copyProperties(model, entity);
            return Optional.of(entity);
        } catch (InstantiationException | IllegalAccessException e) {
            return Optional.empty();
        }

    }

    /**
     * DBのモデルクラスのリストからエンティティクラスのリストを変換する
     * @param models model
     * @param clazz 変換したいエンティティクラス
     * @return Entityクラスのリスト(nullの要素は取り除かれる)
     * @param <T> 変換したいエンティティクラス
     */
    public static <F,T> List<T> convert(List<F> models, Class<T> clazz) {
        List<T> entities = new ArrayList<>();

        for (F model : models) {
            convert(model, clazz).ifPresent(entities::add);
        }

        return entities;
    }

}
