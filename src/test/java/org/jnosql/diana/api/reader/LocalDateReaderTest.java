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


import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LocalDateReaderTest {

    private LocalDateValueReader dateReader;

    @Before
    public void init() {
        dateReader = new LocalDateValueReader();
    }

    @Test
    public void shouldValidateCompatibility() {
        assertTrue(dateReader.isCompatible(LocalDate.class));
    }

    @Test
    public void shouldConvert() {
        final LocalDate now = LocalDate.now();
        final Date date = new Date();
        final Calendar calendar = Calendar.getInstance();
        assertEquals(now, dateReader.read(LocalDate.class, now));
        assertEquals(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), dateReader.read(LocalDate.class, date));
        assertEquals(calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), dateReader.read(LocalDate.class, calendar));
        assertEquals(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), dateReader.read(LocalDate.class, date.getTime()));
    }
}
