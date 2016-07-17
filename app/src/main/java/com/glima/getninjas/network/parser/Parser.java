package com.glima.getninjas.network.parser;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

/**
 * Created by gustavo on 17/07/16.
 */
public interface Parser<T> {

    T parse(InputStream inputStream) throws IOException, ParseException, Exception;
}
