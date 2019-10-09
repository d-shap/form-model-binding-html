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

import org.junit.Test;

import ru.d_shap.assertions.Assertions;

/**
 * Tests for {@link HtmlBindedAttributeImpl}.
 *
 * @author Dmitry Shapovalov
 */
public final class HtmlBindedAttributeImplTest {

    /**
     * Test class constructor.
     */
    public HtmlBindedAttributeImplTest() {
        super();
    }

    /**
     * {@link HtmlBindedAttributeImpl} class test.
     */
    @Test
    public void getNameTest() {
        Assertions.assertThat(new HtmlBindedAttributeImpl(null, null, null).getName()).isNull();
        Assertions.assertThat(new HtmlBindedAttributeImpl("", null, null).getName()).isEqualTo("");
        Assertions.assertThat(new HtmlBindedAttributeImpl(" ", null, null).getName()).isEqualTo(" ");
        Assertions.assertThat(new HtmlBindedAttributeImpl("name", null, null).getName()).isEqualTo("name");
    }

    /**
     * {@link HtmlBindedAttributeImpl} class test.
     */
    @Test
    public void getValueTest() {
        Assertions.assertThat(new HtmlBindedAttributeImpl(null, null, null).getValue()).isNull();
        Assertions.assertThat(new HtmlBindedAttributeImpl(null, "", null).getValue()).isEqualTo("");
        Assertions.assertThat(new HtmlBindedAttributeImpl(null, " ", null).getValue()).isEqualTo(" ");
        Assertions.assertThat(new HtmlBindedAttributeImpl(null, "value", null).getValue()).isEqualTo("value");
    }

    /**
     * {@link HtmlBindedAttributeImpl} class test.
     */
    @Test
    public void getAbsoluteValueTest() {
        Assertions.assertThat(new HtmlBindedAttributeImpl(null, null, null).getAbsoluteValue()).isNull();
        Assertions.assertThat(new HtmlBindedAttributeImpl(null, null, "").getAbsoluteValue()).isEqualTo("");
        Assertions.assertThat(new HtmlBindedAttributeImpl(null, null, " ").getAbsoluteValue()).isEqualTo(" ");
        Assertions.assertThat(new HtmlBindedAttributeImpl(null, null, "absolute value").getAbsoluteValue()).isEqualTo("absolute value");
    }

}
