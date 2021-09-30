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

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import org.jsoup.nodes.Element;
import org.w3c.css.sac.InputSource;

import ru.d_shap.fm.formmodel.InputSourceException;

/**
 * The HTML binded element implementation.
 *
 * @author Dmitry Shapovalov
 */
final class HtmlBindedElementImpl implements HtmlBindedElement {

    private static final String ATTRIBUTE_NAME_STYLE = "style";

    private static final String ATTRIBUTE_NAME_CLASS = "class";

    private final Element _element;

    private final String _cssSelector;

    private final HtmlCssParser _htmlCssParser;

    HtmlBindedElementImpl(final Element element) {
        this(element, new HtmlCssParserImpl());
    }

    HtmlBindedElementImpl(final Element element, final HtmlCssParser htmlCssParser) {
        super();
        _element = element;
        _cssSelector = _element.cssSelector();
        _htmlCssParser = htmlCssParser;
    }

    @Override
    public Element getElement() {
        return _element;
    }

    @Override
    public String cssSelector() {
        return _cssSelector;
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
        String[] splittedName = splitName(name);
        if (isStyleAttribute(splittedName)) {
            String value = getStyleAttribute(splittedName);
            return value != null;
        }
        if (isClassAttribute(splittedName)) {
            String value = getClassAttribute(splittedName);
            return value != null;
        }
        return _element.attributes().hasKey(name);
    }

    @Override
    public String getAttribute(final String name) {
        String[] splittedName = splitName(name);
        if (isStyleAttribute(splittedName)) {
            String value = getStyleAttribute(splittedName);
            if (value == null) {
                return "";
            } else {
                return value;
            }
        }
        if (isClassAttribute(splittedName)) {
            String value = getClassAttribute(splittedName);
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
        String[] splittedName = splitName(name);
        if (isStyleAttribute(splittedName)) {
            return "";
        }
        if (isClassAttribute(splittedName)) {
            return "";
        }
        return _element.attr("abs:" + name);
    }

    private String[] splitName(final String name) {
        int idx = name.indexOf('.');
        if (idx >= 0) {
            String str1 = name.substring(0, idx);
            String str2 = name.substring(idx + 1);
            return new String[]{str1, str2};
        } else {
            return new String[]{name};
        }
    }

    private boolean isStyleAttribute(final String[] splittedName) {
        return splittedName.length == 2 && ATTRIBUTE_NAME_STYLE.equalsIgnoreCase(splittedName[0]);
    }

    private String getStyleAttribute(final String[] splittedName) {
        String attributeValue = _element.attr(ATTRIBUTE_NAME_STYLE);
        StringReader attributeValueReader = new StringReader(attributeValue);
        InputSource attributeValueSource = new InputSource(attributeValueReader);
        try {
            Map<String, String> cssProperties = _htmlCssParser.getCssProperties(attributeValueSource);
            return cssProperties.get(splittedName[1]);
        } catch (IOException ex) {
            throw new InputSourceException(ex);
        }
    }

    private boolean isClassAttribute(final String[] splittedName) {
        return splittedName.length == 2 && ATTRIBUTE_NAME_CLASS.equalsIgnoreCase(splittedName[0]);
    }

    private String getClassAttribute(final String[] splittedName) {
        String attributeValue = _element.attr(ATTRIBUTE_NAME_CLASS);
        String[] attributeValueClasses = attributeValue.split("\\s+");
        for (String attributeValueClass : attributeValueClasses) {
            if (attributeValueClass.equals(splittedName[1])) {
                return attributeValueClass;
            }
        }
        return null;
    }

}
