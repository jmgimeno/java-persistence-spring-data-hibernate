/*
 * ========================================================================
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ========================================================================
 */
package com.manning.javapersistence.ch08.sortedmapofstrings;

import com.manning.javapersistence.ch08.Constants;

import javax.persistence.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;

@Entity
public class Item {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private Long id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "IMAGE")
    @MapKeyColumn(name = "FILENAME")
    @Column(name = "IMAGENAME")
    @org.hibernate.annotations.SortComparator(ReverseStringComparator.class)
    private SortedMap<String, String> images = new TreeMap<>();

    public Item(String name) {
        this.name = name;
    }

    public Item() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SortedMap<String, String> getImages() {
        return Collections.unmodifiableSortedMap(images);
    }

    public void putImage(String key, String value) {
        images.put(key, value);
    }

    public static class ReverseStringComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return b.compareTo(a); // Inverse sorting
        }
    }

}
