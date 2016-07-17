package com.glima.getninjas.network.converter;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by gustavo on 17/07/16.
 */
public class StringConverterFactory extends Converter.Factory {
    private StringConverterFactory() {
    }

    public static StringConverterFactory create() {
        return new StringConverterFactory();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new ResponseToStringConverter();
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new StringToRequestConverter();
    }

    @Override
    public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        Class<?> cls = (Class<?>) type;
        if (String.class.isAssignableFrom(cls)) {
            return new ResponseToStringConverter();
        }
        return null;
    }

    private class ResponseToStringConverter implements Converter<ResponseBody, String> {

        @Override
        public String convert(ResponseBody value) throws IOException {
            return value.string();
        }
    }

    private class StringToRequestConverter implements Converter<String, RequestBody> {

        @Override
        public RequestBody convert(String value) throws IOException {
            return RequestBody.create(MediaType.parse("application/json; charset=UTF-8"), value.getBytes());
        }
    }
}