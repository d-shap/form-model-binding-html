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

import org.jsoup.nodes.Element;

import ru.d_shap.formmodel.binding.model.BindedAttribute;

/**
 * The HTML binded attribute.
 *
 * @author Dmitry Shapovalov
 */
public final class HtmlBindedAttribute implements BindedAttribute {

    private final Element _element;

    /**
     * Create new object.
     *
     * @param element the HTML element.
     */
    public HtmlBindedAttribute(final Element element) {
        super();
        _element = element;
    }

    /**
     * Get the HTML element.
     *
     * @return the HTML element.
     */
    public Element getElement() {
        return _element;
    }

}
