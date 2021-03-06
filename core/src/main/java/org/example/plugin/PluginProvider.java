/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.example.plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

public class PluginProvider {
   private static PluginProvider instance;

   public static PluginProvider getInstance() {
      if (instance == null) {
         instance = new PluginProvider();
      }
      return instance;
   }

   private final Map<String, PluginFactory> factories = new HashMap<>();

   private PluginProvider() {
      ServiceLoader<PluginFactory> serviceLoader = ServiceLoader.load(
         PluginFactory.class, PluginProvider.class.getClassLoader());

      for (PluginFactory factory : serviceLoader) {
         factories.put(factory.getName(), factory);
      }
   }

   public <T extends Plugin> T create(String name) {
      ServiceLoader<PluginFactory> serviceLoader = ServiceLoader.load(
         PluginFactory.class, PluginProvider.class.getClassLoader());

      for (PluginFactory factory : factories.values()) {
         if (factory.getName().equals(name)) {
            return ((PluginFactory<T>)factory).create();
         }
      }

      return null;
   }
}
