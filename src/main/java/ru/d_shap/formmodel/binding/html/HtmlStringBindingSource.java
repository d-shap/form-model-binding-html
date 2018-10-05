///////////////////////////////////////////////////////////////////////////////////////////////////
// Form model html binding is a form model implementation for HTML.
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
 * The HTML binding source, obtained from String.
 *
 * @author Dmitry Shapovalov
 */
public final class HtmlStringBindingSource extends HtmlBindingSource {

    private final String _html;

    /**
     * Create new object.
     *
     * @param html the source HTML string.
     */
    public HtmlStringBindingSource(final String html) {
        super();
        _html = html;
    }

    @Override
    public Document getDocument() {
        return Jsoup.parse(_html);
    }

}
