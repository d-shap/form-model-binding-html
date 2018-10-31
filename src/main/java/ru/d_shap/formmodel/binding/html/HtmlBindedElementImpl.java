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

import java.io.IOException;
import java.io.StringReader;

import com.steadystate.css.parser.CSSOMParser;

import org.jsoup.nodes.Element;
import org.w3c.css.sac.InputSource;
import org.w3c.dom.css.CSSStyleDeclaration;
import org.w3c.dom.css.CSSValue;

import ru.d_shap.formmodel.InputSourceException;

/**
 * The HTML binded element implementation.
 *
 * @author Dmitry Shapovalov
 */
final class HtmlBindedElementImpl implements HtmlBindedElement {

    private static final String ATTRIBUTE_NAME_STYLE = "style";

    private static final String ATTRIBUTE_NAME_CLASS = "class";

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
        String[] names = name.split("[.]");
        if (isStyleAttribute(names)) {
            String value = getStyleAttribute(names);
            return value != null;
        }
        if (isClassAttribute(names)) {
            String value = getClassAttribute(names);
            return value != null;
        }
        return _element.attributes().hasKey(name);
    }

    @Override
    public String getAttribute(final String name) {
        String[] names = name.split("[.]");
        if (isStyleAttribute(names)) {
            String value = getStyleAttribute(names);
            if (value == null) {
                return "";
            } else {
                return value;
            }
        }
        if (isClassAttribute(names)) {
            String value = getClassAttribute(names);
            if (value == null) {
                return "";
            } else {
                return value;
            }
        }
        return _element.attr(name);
    }

    @Override
    public String getAbsoluteAttribute(final String name) {
        String[] names = name.split("[.]");
        if (isStyleAttribute(names)) {
            return "";
        }
        if (isClassAttribute(names)) {
            return "";
        }
        return _element.attr("abs:" + name);
    }

    private boolean isStyleAttribute(final String[] names) {
        return names.length == 2 && ATTRIBUTE_NAME_STYLE.equalsIgnoreCase(names[0]);
    }

    private String getStyleAttribute(final String[] names) {
        try {
            String attributeValue = _element.attr(ATTRIBUTE_NAME_STYLE);
            CSSOMParser parser = new CSSOMParser();
            StringReader reader = new StringReader(attributeValue);
            InputSource inputSource = new InputSource(reader);
            CSSStyleDeclaration styleDeclaration = parser.parseStyleDeclaration(inputSource);
            CSSValue cssValue = styleDeclaration.getPropertyCSSValue(names[1]);
            if (cssValue == null) {
                return null;
            } else {
                return cssValue.getCssText();
            }
        } catch (IOException ex) {
            throw new InputSourceException(ex);
        }
    }

    private boolean isClassAttribute(final String[] names) {
        return names.length == 2 && ATTRIBUTE_NAME_CLASS.equalsIgnoreCase(names[0]);
    }

    private String getClassAttribute(final String[] names) {
        String classValue = _element.attr(ATTRIBUTE_NAME_STYLE);
        String[] classes = classValue.split("\\s+");
        for (String clazz : classes) {
            if (clazz.equals(names[1])) {
                return names[1];
            }
        }
        return null;
    }

}
