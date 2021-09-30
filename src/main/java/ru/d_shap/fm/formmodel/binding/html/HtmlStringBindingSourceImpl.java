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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * The HTML binding source implementation, obtained from the string.
 *
 * @author Dmitry Shapovalov
 */
final class HtmlStringBindingSourceImpl implements HtmlBindingSource {

    private final String _html;

    private final String _baseUrl;

    HtmlStringBindingSourceImpl(final String html) {
        super();
        _html = html;
        _baseUrl = null;
    }

    HtmlStringBindingSourceImpl(final String html, final String baseUrl) {
        super();
        _html = html;
        _baseUrl = baseUrl;
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
