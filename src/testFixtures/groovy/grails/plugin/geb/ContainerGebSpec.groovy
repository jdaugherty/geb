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

import geb.report.CompositeReporter
import geb.report.PageSourceReporter
import geb.report.Reporter
import geb.test.GebTestManager
import geb.transform.DynamicallyDispatchesToBrowser
import org.testcontainers.containers.BrowserWebDriverContainer
import spock.lang.Shared
import spock.lang.Specification

/**
 * A {@link geb.spock.GebSpec GebSpec} that leverages Testcontainers to run the browser inside a container.
 *
 * <p>Prerequisites:
 * <ul>
 *   <li>
 *       The test class must be annotated with {@link grails.testing.mixin.integration.Integration @Integration}.
 *   </li>
 *   <li>
 *       A <a href="https://java.testcontainers.org/supported_docker_environment/">compatible container runtime</a>
 *       (e.g., Docker) must be available for Testcontainers to utilize.
 *   </li>
 * </ul>
 *
 * @see grails.plugin.geb.ContainerGebConfiguration for how to customize the container's connection information
 *
 * @author Søren Berg Glasius
 * @author Mattias Reichel
 * @author James Daugherty
 * @since 4.0
 */
@DynamicallyDispatchesToBrowser
abstract class ContainerGebSpec extends Specification implements ContainerAwareDownloadSupport {

    @Shared
    @Delegate(includes = ['getBrowser', 'report'])
    @SuppressWarnings('unused')
    static GebTestManager testManager

    /**
     * Get access to container running the web-driver, for convenience to execInContainer, copyFileToContainer etc.
     *
     * @see org.testcontainers.containers.ContainerState#execInContainer(java.lang.String ...)
     * @see org.testcontainers.containers.ContainerState#copyFileToContainer(org.testcontainers.utility.MountableFile, java.lang.String)
     * @see org.testcontainers.containers.ContainerState#copyFileFromContainer(java.lang.String, java.lang.String)
     * @see org.testcontainers.containers.ContainerState
     */
    @Shared
    static BrowserWebDriverContainer container

    /**
     * The reporter that GebShould use when reporting is enabled.
     */
    Reporter createReporter() {
        new CompositeReporter(new PageSourceReporter())
    }
}