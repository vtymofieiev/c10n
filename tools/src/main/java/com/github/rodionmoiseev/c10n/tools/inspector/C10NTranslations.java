/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.github.rodionmoiseev.c10n.tools.inspector;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.lang.annotation.Annotation;
import java.util.Enumeration;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * @author rodion
 * @since 1.1
 */
public final class C10NTranslations {
    private String value = null;
    private final Set<ResourceBundle> bundles = Sets.newHashSet();
    private final Set<Annotation> annotations = Sets.newHashSet();

    /**
     * <p>Get the actual translated value.
     * <p>Note that value may not be available (be <code>null</code>) if
     * it corresponds to a parameterised method with argument types other
     * than one of {@link String}, {@link CharSequence} or one of primitive
     * types. However, the behaviour can be customised by providing a custom
     * {@link DefaultDummyInstanceProvider} (see {@link com.github.rodionmoiseev.c10n.tools.C10NTools}.
     *
     *
     * @return the actual translated value, or <code>null</code> if not available
     * @see DummyInstanceProvider
     * @see com.github.rodionmoiseev.c10n.tools.C10NTools
     */
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Set<ResourceBundle> getBundles() {
        return bundles;
    }

    public Set<Annotation> getAnnotations() {
        return annotations;
    }

    @Override
    public String toString() {
        return "C10NTranslations{" +
                "bundles=" + bundlesToMaps(bundles) +
                ", annotations=" + annotations +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        C10NTranslations that = (C10NTranslations) o;

        if (!annotations.equals(that.annotations)) return false;
        if (!bundles.equals(that.bundles)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bundles.hashCode();
        result = 31 * result + annotations.hashCode();
        return result;
    }

    private static Set<Map<String, String>> bundlesToMaps(Set<ResourceBundle> bundles) {
        Set<Map<String, String>> res = Sets.newHashSet();
        for (ResourceBundle bundle : bundles) {
            res.add(bundleToMap(bundle));
        }
        return res;
    }

    private static Map<String, String> bundleToMap(ResourceBundle bundle) {
        Map<String, String> res = Maps.newHashMap();
        Enumeration<String> keys = bundle.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            res.put(key, bundle.getString(key));
        }
        return res;
    }
}
