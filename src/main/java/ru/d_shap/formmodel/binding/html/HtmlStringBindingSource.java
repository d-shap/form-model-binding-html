///////////////////////////////////////////////////////////////////////////////////////////////////
// Form model html binding is a form model binding implementation for HTML.
// Copyright (C) 2018 Dmitry Shapovalov.
//
// This file is part of Form model html binding.
//
// Form model html binding is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// Form model html binding is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with this program. If not, see <http://www.gnu.org/licenses/>.
///////////////////////////////////////////////////////////////////////////////////////////////////
package ru.d_shap.formmodel.binding.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * The HTML binding source, obtained from the string.
 *
 * @author Dmitry Shapovalov
 */
public final class HtmlStringBindingSource extends HtmlBindingSource {

    private final String _html;

    private final String _baseUrl;

    /**
     * Create new object.
     *
     * @param html the source HTML string.
     */
    public HtmlStringBindingSource(final String html) {
        super();
        _html = html;
        _baseUrl = null;
    }

    /**
     * Create new object.
     *
     * @param html    the source HTML string.
     * @param baseUrl the base URL to resolve relative links.
     */
    public HtmlStringBindingSource(final String html, final String baseUrl) {
        super();
        _html = html;
        _baseUrl = baseUrl;
    }

    /**
     * Get the source HTML string.
     *
     * @return the source HTML string.
     */
    public String getHtml() {
        return _html;
    }

    /**
     * Get the base URL to resolve relative links.
     *
     * @return the base URL to resolve relative links.
     */
    public String getBaseUrl() {
        return _baseUrl;
    }

    @Override
    public Document getDocument() {
        if (_baseUrl == null) {
            return Jsoup.parse(_html);
        } else {
            return Jsoup.parse(_html, _baseUrl);
        }
    }

}
