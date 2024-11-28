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

import geb.Browser
import geb.download.DefaultDownloadSupport
import groovy.transform.CompileStatic
import groovy.transform.PackageScope

import java.util.regex.Pattern

/**
 * @author Mattias Reichel
 * @since 5.0
 */
@PackageScope
@CompileStatic
class LocalhostDownloadSupport extends DefaultDownloadSupport {

    private final static Pattern urlPattern = ~/(https?:\/\/)([^\/:]+)(:\d+\/.*)/

    private final String hostNameFromHost
    private final Browser browser

    LocalhostDownloadSupport(Browser browser, String hostNameFromHost) {
        super(browser)
        this.browser = browser
        this.hostNameFromHost = hostNameFromHost
    }

    @Override
    HttpURLConnection download(Map options) {
        return super.download([*: options, base: resolveBase(options)])
    }

    private String resolveBase(Map options) {
        return options.base ?: browser.driver.currentUrl.replaceAll(urlPattern) { match, proto, host, rest ->
            "${proto}${hostNameFromHost}${rest}"
        }
    }
}
