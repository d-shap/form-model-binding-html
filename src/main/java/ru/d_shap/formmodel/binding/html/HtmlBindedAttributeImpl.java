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
 * The HTML binded attribute implementation.
 *
 * @author Dmitry Shapovalov
 */
final class HtmlBindedAttributeImpl implements HtmlBindedAttribute {

    private final Element _element;

    private final String _attributeName;

    HtmlBindedAttributeImpl(final Element element, final String attributeName) {
        super();
        _element = element;
        _attributeName = attributeName;
    }

    @Override
    public String getValue() {
        return _element.attr(_attributeName);
    }

    @Override
    public String getAbsoluteValue() {
        return _element.attr("abs:" + _attributeName);
    }

}
