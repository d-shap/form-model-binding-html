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

import ru.d_shap.formmodel.binding.model.BindedElement;

/**
 * The HTML binded element.
 *
 * @author Dmitry Shapovalov
 */
public interface HtmlBindedElement extends BindedElement {

    /**
     * Get the HTML element.
     *
     * @return the HTML element.
     */
    Element getElement();

    /**
     * Get the element's text.
     *
     * @return the element's text.
     */
    String getOwnText();

    /**
     * Get the element's text and the text of the element's children.
     *
     * @return Get the element's text and the text of the element's children.
     */
    String getText();

    /**
     * Get the element's attribute value.
     *
     * @param attributeName the element's attribute name.
     *
     * @return the element's attribute value.
     */
    String getAttributeValue(String attributeName);

    /**
     * Get the element's absolute attribute value.
     *
     * @param attributeName the element's attribute name.
     *
     * @return the element's absolute attribute value.
     */
    String getAbsoluteAttributeValue(String attributeName);

}
