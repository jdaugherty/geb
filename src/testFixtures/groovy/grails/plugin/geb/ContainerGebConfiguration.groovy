/*
 * Copyright 2024 original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package grails.plugin.geb

import org.testcontainers.containers.GenericContainer

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 * Can be used to configure the protocol and hostname that the container's browser will use
 *
 * @author James Daugherty
 * @since 4.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface ContainerGebConfiguration {

    static final String DEFAULT_HOSTNAME_FROM_CONTAINER = GenericContainer.INTERNAL_HOST_HOSTNAME
    static final String DEFAULT_PROTOCOL = 'http'

    /**
     * The protocol that the container's browser will use to access the server under test.
     * <p>Defaults to {@code http}.
     */
    String protocol() default DEFAULT_PROTOCOL

    /**
     * The hostname that the container's browser will use to access the server under test.
     * <p>Defaults to {@code host.testcontainers.internal}.
     * <p>This is useful when the server under test needs to be accessed with a certain hostname.
     */
    String hostName() default DEFAULT_HOSTNAME_FROM_CONTAINER

    /**
     * Whether reporting should be enabled for this test. Add a `GebConfig.groovy` to customize the reporter configuration.
     */
    boolean reporting() default false
}