package com.luccas.buscaperto.utils;

import android.support.annotation.Nullable;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public final class NullOnEmptyConverterFactory extends Converter.Factory {
    @Override
    public @Nullable Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        final Converter<ResponseBody, ?> delegate = retrofit.nextResponseBodyConverter(this, type, annotations);
        return (Converter<ResponseBody, Object>) body -> {
            if (body.contentLength() == 0) {
                return null;
            }
            return delegate.convert(body);
        };
    }
}