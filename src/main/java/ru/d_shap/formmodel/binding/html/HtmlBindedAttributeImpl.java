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

/**
 * The HTML binded attribute implementation.
 *
 * @author Dmitry Shapovalov
 */
final class HtmlBindedAttributeImpl implements HtmlBindedAttribute {

    private final String _name;

    private final String _value;

    private final String _absoluteValue;

    HtmlBindedAttributeImpl(final String name, final String value, final String absoluteValue) {
        super();
        _name = name;
        _value = value;
        _absoluteValue = absoluteValue;
    }

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public String getValue() {
        return _value;
    }

    @Override
    public String getAbsoluteValue() {
        return _absoluteValue;
    }

}
