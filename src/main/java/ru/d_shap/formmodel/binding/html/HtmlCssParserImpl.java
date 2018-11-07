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
import java.util.HashMap;
import java.util.Map;

import com.steadystate.css.parser.CSSOMParser;

import org.w3c.css.sac.CSSParseException;
import org.w3c.css.sac.ErrorHandler;
import org.w3c.css.sac.InputSource;
import org.w3c.dom.css.CSSStyleDeclaration;

/**
 * The HTML CSS parser implementation.
 *
 * @author Dmitry Shapovalov
 */
final class HtmlCssParserImpl implements HtmlCssParser {

    HtmlCssParserImpl() {
        super();
    }

    @Override
    public Map<String, String> getCssProperties(final InputSource inputSource) throws IOException {
        CSSOMParser parser = new CSSOMParser();
        parser.setErrorHandler(new IgnoreCssExceptionErrorHandler());
        CSSStyleDeclaration styleDeclaration = parser.parseStyleDeclaration(inputSource);
        Map<String, String> result = new HashMap<>();
        for (int i = 0; i < styleDeclaration.getLength(); i++) {
            String propertyName = styleDeclaration.item(i);
            String propertyValue = styleDeclaration.getPropertyValue(propertyName);
            result.put(propertyName, propertyValue);
        }
        return result;
    }

    /**
     * The HTML CSS parser error handler.
     *
     * @author Dmitry Shapovalov
     */
    private static final class IgnoreCssExceptionErrorHandler implements ErrorHandler {

        IgnoreCssExceptionErrorHandler() {
            super();
        }

        @Override
        public void warning(final CSSParseException exception) {
            // Ignore
        }

        @Override
        public void error(final CSSParseException exception) {
            // Ignore
        }

        @Override
        public void fatalError(final CSSParseException exception) {
            // Ignore
        }

    }

}
