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

import org.jsoup.nodes.Element;

/**
 * The HTML binded element implementation.
 *
 * @author Dmitry Shapovalov
 */
final class HtmlBindedElementImpl implements HtmlBindedElement {

    private final Element _element;

    HtmlBindedElementImpl(final Element element) {
        super();
        _element = element;
    }

    @Override
    public Element getElement() {
        return _element;
    }

    @Override
    public String getOwnText() {
        return _element.ownText();
    }

    @Override
    public String getText() {
        return _element.text();
    }

    @Override
    public boolean hasAttribute(final String name) {
        return _element.attributes().hasKey(name);
    }

    @Override
    public String getAttribute(final String name) {
        return _element.attr(name);
    }

    @Override
    public String getAbsoluteAttribute(final String name) {
        return _element.attr("abs:" + name);
    }

}
