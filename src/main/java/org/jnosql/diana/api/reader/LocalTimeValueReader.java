/*
 * Copyright 2017 Otavio Santana and others
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */

package org.jnosql.diana.api.reader;

import org.jnosql.diana.api.ValueReader;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * Class to read and convert to {@link LocalTime} type
 */
@SuppressWarnings("unchecked")
public class LocalTimeValueReader implements ValueReader {

    @Override
    public boolean isCompatible(Class clazz) {
        return LocalTime.class.equals(clazz);
    }

    @Override
    public <T> T read(Class<T> clazz, Object value) {
        if (LocalTime.class.isInstance(value)) {
            return (T) value;
        }

        if (Calendar.class.isInstance(value)) {
            return (T) ((Calendar) value).toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        }

        if (Date.class.isInstance(value)) {
            return (T) ((Date) value).toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        }

        if (Number.class.isInstance(value)) {
            return (T) new Date(((Number) value).longValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        }

        return (T) LocalTime.parse(value.toString());
    }
}
