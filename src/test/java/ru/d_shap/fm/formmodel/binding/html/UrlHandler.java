///////////////////////////////////////////////////////////////////////////////////////////////////
// Form model HTML binding is a form model binding implementation for HTML.
// Copyright (C) 2018 Dmitry Shapovalov.
//
// This file is part of form model HTML binding.
//
// Form model HTML binding is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// Form model HTML binding is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with this program. If not, see <http://www.gnu.org/licenses/>.
///////////////////////////////////////////////////////////////////////////////////////////////////
package ru.d_shap.fm.formmodel.binding.html;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * URL Handler to mock server response.
 *
 * @author Dmitry Shapovalov
 */
final class UrlHandler {

    public static final String URL_PREFIX = "https://localhost:8080/";

    public static final String URL_HTML_1 = URL_PREFIX + "1/";

    public static final String URL_HTML_2 = URL_PREFIX + "2/";

    public static final String URL_HTML_3 = URL_PREFIX + "3/";

    private static final Map<String, String> HTML;

    static {
        Map<String, String> map = new HashMap<>();
        map.put(URL_HTML_1, createHtml1());
        map.put(URL_HTML_2, createHtml2());
        map.put(URL_HTML_3, createHtml3());

        HTML = Collections.unmodifiableMap(map);
    }

    private static boolean factorySet = false;

    private UrlHandler() {
        super();
    }

    private static String createHtml1() {
        String html = "";
        html += "<html>";
        html += "<head>";
        html += "<title>Test page title</title>";
        html += "</head>";
        html += "<body>";
        html += "<div id='divid' class='divclass' style='display: none;'>";
        html += "</div>";
        html += "<a href='path/to/resource'>link</a>";
        html += "</body>";
        html += "</html>";
        return html;
    }

    private static String createHtml2() {
        String html = "";
        html += "<html>";
        html += "<head>";
        html += "<title>Test page title</title>";
        html += "</head>";
        html += "<body>Test page body</body>";
        html += "</html>";
        return html;
    }

    private static String createHtml3() {
        String html = "";
        html += "<html>";
        html += "<head>";
        html += "<title>Test page</title>";
        html += "</head>";
        html += "<body>";
        html += "<p class='pclass xx' style='padding: 0px; display: none;'>";
        html += "This is a ";
        html += "<a href='linkref/id' style='color: blue;'>link</a>";
        html += " to somewhere.";
        html += "</p>";
        html += "<p class='pclass xx'>";
        html += "<div class='blockclass xx'>";
        html += "<span id='someid'>Span in Div 1.</span>";
        html += "</div>";
        html += "<div>";
        html += "<span class='blockclass xx'>Span in Div 2.</span>";
        html += "</div>";
        html += "</p>";
        html += "</body>";
        html += "</html>";
        return html;
    }

    static void setURLStreamHandlerFactory() {
        synchronized (URL.class) {
            if (!factorySet) {
                URL.setURLStreamHandlerFactory(new URLStreamHandlerFactoryImpl());
                factorySet = true;
            }
        }
    }

    static String getHtml1() {
        return HTML.get(URL_HTML_1);
    }

    private static class URLStreamHandlerFactoryImpl implements URLStreamHandlerFactory {

        URLStreamHandlerFactoryImpl() {
            super();
        }

        @Override
        public URLStreamHandler createURLStreamHandler(final String protocol) {
            if ("https".equalsIgnoreCase(protocol)) {
                return new URLStreamHandlerImpl();
            } else {
                return null;
            }
        }

    }

    private static class URLStreamHandlerImpl extends URLStreamHandler {

        URLStreamHandlerImpl() {
            super();
        }

        @Override
        protected URLConnection openConnection(final URL url) throws IOException {
            return new URLConnectionImpl(url);
        }

    }

    private static class URLConnectionImpl extends HttpURLConnection {

        URLConnectionImpl(final URL url) {
            super(url);
        }

        @Override
        public boolean usingProxy() {
            return false;
        }

        @Override
        public void connect() throws IOException {
            // Ignore
        }

        @Override
        public void disconnect() {
            // Ignore
        }

        @Override
        public int getResponseCode() throws IOException {
            String html = getHtml();
            if (html == null) {
                return 404;
            } else {
                return 200;
            }
        }

        @Override
        public InputStream getInputStream() throws IOException {
            String html = getHtml();
            byte[] bytes = html.getBytes(StandardCharsets.UTF_8);
            return new ByteArrayInputStream(bytes);
        }

        private String getHtml() {
            URL url = getURL();
            String urlStr = url.toString();
            return HTML.get(urlStr);
        }

    }

}
