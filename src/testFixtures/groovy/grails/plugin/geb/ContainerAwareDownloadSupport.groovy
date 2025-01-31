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

import geb.download.DownloadSupport
import groovy.transform.CompileStatic
import groovy.transform.SelfType
import spock.lang.Shared

/**
 * A custom implementation of {@link geb.download.DownloadSupport} for enabling the use of its {@code download*()} methods
 * within {@code ContainerGebSpec} environments.
 *
 * <p>This implementation is based on {@code DefaultDownloadSupport} from Geb, with modifications to support
 * containerized environments. Specifically, it enables file downloads by resolving URLs relative to the host
 * rather than the internal hostname used by the browser within the container.</p>
 *
 * <p>These adaptations allow the download functionality to operate correctly when tests are executed in containerized
 * setups, ensuring the host network context is used for download requests.</p>
 *
 * @author Mattias Reichel
 * @since 4.1
 */
@CompileStatic
@SelfType(ContainerGebSpec)
trait ContainerAwareDownloadSupport implements DownloadSupport {

    @Delegate
    @Shared
    static DownloadSupport downloadSupport
}