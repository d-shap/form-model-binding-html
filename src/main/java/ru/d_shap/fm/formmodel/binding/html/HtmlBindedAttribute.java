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

import ru.d_shap.fm.formmodel.binding.model.BindedAttribute;

/**
 * The HTML binded attribute.
 *
 * @author Dmitry Shapovalov
 */
public interface HtmlBindedAttribute extends BindedAttribute {

    /**
     * Get the attribute name.
     *
     * @return the attribute name.
     */
    String getName();

    /**
     * Get the attribute value.
     *
     * @return the attribute value.
     */
    String getValue();

    /**
     * Get the absolute attribute value.
     *
     * @return the absolute attribute value.
     */
    String getAbsoluteValue();

}
