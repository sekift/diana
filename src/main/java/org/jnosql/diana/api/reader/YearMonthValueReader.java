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

import java.time.YearMonth;

import org.jnosql.diana.api.ValueReader;

/**
 * Class to reads and converts to {@link YearMonth}, first it verify if is YearMonth if yes return itself
 * otherwise convert to {@link String} and then {@link YearMonth}
 */
@SuppressWarnings("unchecked")
public final class YearMonthValueReader implements ValueReader {

    @Override
    public <T> boolean isCompatible(Class<T> clazz) {
        return YearMonth.class.equals(clazz);
    }

    @Override
    public <T> T read(Class<T> clazz, Object value) {
        if (YearMonth.class.isInstance(value)) {
            return (T) value;
        }
        YearMonth yearMonth = YearMonth.parse(value.toString());
        return (T) yearMonth;
    }
}
